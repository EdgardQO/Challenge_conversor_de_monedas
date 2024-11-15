package reto.principal;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.Main;
import reto.modelos.Conversor;
import reto.modelos.Intermediario;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
public class principalConAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        String decision = "si";
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //Para poner cada campo que empiece con mayúscula
        Scanner lectura = new Scanner(System.in);
        while (decision.equals("si")) {
            System.out.println("############### BIENVENIDO AL CONVERSOR DE MONEDAS DEL CHALLENGE ###############");
            System.out.println("1) Convertir de soles (PEN) a dólares (USD)");
            System.out.println("2) Convertir de dólares (USD) a soles (PEN)");
            System.out.println("3) Convertir de soles(PEN) a peso argentino (ARS)");
            System.out.println("4) Convertir de peso argentino (ARS) a soles (PEN)");
            System.out.println("5) Convertir de soles (PEN) a euros (EUR)");
            System.out.println("6) Convertir de euros (EUR) a soles (PEN)");
            System.out.println("7) Convertir de soles (PEN) a peso colombiano (COP)");
            System.out.println("8) Convertir de peso colombiano (COP) a soles (PEN)");
            System.out.println("9) Salir");
            System.out.print("Elige una opción: ");
            int opcion = lectura.nextInt();

            String busqueda1 = "";
            String busqueda2 = "";
            double cantidad = 0;

            switch (opcion) {
                case 1:
                    busqueda1 = "PEN";
                    busqueda2 = "USD";
                    break;
                case 2:
                    busqueda1 = "USD";
                    busqueda2 = "PEN";
                    break;
                case 3:
                    busqueda1 = "PEN";
                    busqueda2 = "ARS";
                    break;
                case 4:
                    busqueda1 = "ARS";
                    busqueda2 = "PEN";
                    break;
                case 5:
                    busqueda1 = "PEN";
                    busqueda2 = "EUR";
                    break;
                case 6:
                    busqueda1 = "EUR";
                    busqueda2 = "PEN";
                    break;
                case 7:
                    busqueda1 = "PEN";
                    busqueda2 = "COP";
                    break;
                case 8:
                    busqueda1 = "COP";
                    busqueda2 = "PEN";
                    break;
                case 9:
                    System.out.println("############### GRACIAS POR USAR EL CONVERSOR DE MONEDAS ###############");
                    return;
                default:
                    System.out.println("Opción inválida.");
                    continue;
            }
            // Solicitar la cantidad de dinero
            System.out.print("Ingresa la cantidad: ");
            cantidad = lectura.nextDouble();

            // Llamamos a la función que obtiene la conversión
            Intermediario resultado = obtenerConversion(busqueda1, busqueda2, cantidad, gson);
            if (resultado != null) {
                System.out.println("El resultado de convertir " + cantidad + " " + busqueda1 + " a " + busqueda2 + " es " + resultado);
            } else {
                System.out.println("Hubo un error al obtener la conversión.");
            }
            // Preguntar si el usuario quiere realizar otra conversión
            System.out.print("¿Quieres realizar otra conversión? (si/no): ");
            decision = lectura.next();
        }
        if (decision.equals("no")) {
            System.out.println("############### GRACIAS POR USAR EL CONVERSOR DE MONEDAS ###############");
            System.out.println("By: Quijano Ochoa Edgard Dabert");
        }
    }

    // Método para realizar la solicitud HTTP y devuelve el objeto Intermediario
    public static Intermediario obtenerConversion(String busqueda1, String busqueda2, double cantidad, Gson gson) throws IOException, InterruptedException {
        String direccion = "https://v6.exchangerate-api.com/v6/69c3bfc534a19f54189047e9/pair/" + busqueda1 + "/" + busqueda2 + "/" + cantidad;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build(); // Construimos un request con el URI
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
//        System.out.println("Respuesta de la API: ");
//        System.out.println(json);
        return gson.fromJson(json, Intermediario.class);
    }
}
