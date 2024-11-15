package reto.modelos;

public record Intermediario(String conversion_result) {
    @Override
    public String toString() {
        return conversion_result;
    }
}
