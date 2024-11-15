package reto.modelos;

public class Conversor{
    public Conversor(Intermediario miconversiorIntermediario){
        this.conversion = Double.valueOf(miconversiorIntermediario.conversion_result());
    }

    private double conversion;

    @Override
    public String toString() {
        return "Conversion: " + conversion;
    }
}
