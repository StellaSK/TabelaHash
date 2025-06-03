public class Registro {
    private final String codigo;

    public Registro(String codigo) {
        // Normaliza o código: remove espaços e completa com zeros à esquerda
        String normalized = codigo.trim().replaceAll("\\D", "");

        if (normalized.length() > 9) {
            normalized = normalized.substring(0, 9);
        } else if (normalized.length() < 9) {
            normalized = String.format("%09d", Long.parseLong(normalized));
        }

        if (!normalized.matches("\\d{9}")) {
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }

        this.codigo = normalized;
    }
    public String getCodigo() {
        return codigo;
    }
}