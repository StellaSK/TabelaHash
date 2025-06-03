public class Registro {
    private final String codigo;

    public Registro(String codigoOriginal) {
        // Etapa 1: remover tudo que não for dígito
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codigoOriginal.length(); i++) {
            char c = codigoOriginal.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
        }

        // Etapa 2: garantir que tenha no máximo 9 dígitos
        String limpo = sb.toString();
        if (limpo.length() > 9) {
            limpo = limpo.substring(0, 9);
        }

        // Etapa 3: completar com zeros à esquerda
        StringBuilder completo = new StringBuilder();
        for (int i = 0; i < 9 - limpo.length(); i++) {
            completo.append('0');
        }
        completo.append(limpo);

        // Etapa 4: verificar se tem exatamente 9 dígitos
        String finalCodigo = completo.toString();
        if (finalCodigo.length() != 9) {
            System.out.println("Código inválido: " + codigoOriginal);
            System.exit(1); // tentei usar return, break mas ficou dando erro na linha 2 isso
        }

        this.codigo = finalCodigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
