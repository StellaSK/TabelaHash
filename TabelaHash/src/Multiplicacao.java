public class Multiplicacao implements FuncaoHash {
    private static final double A = (2.2360679775 - 1) / 2; // Aproximação de (sqrt(5) - 1)/2

    private long parseLongManual(String codigo) {
        long resultado = 0;
        for (int i = 0; i < codigo.length(); i++) {
            int digito = codigo.charAt(i) - '0';
            resultado = resultado * 10 + digito;
        }
        return resultado;
    }

    @Override
    public int hash(Registro registro, int tamanhoTabela) {
        long codigo = parseLongManual(registro.getCodigo());
        double valor = codigo * A;

        int parteInteira = (int) valor; // floor para valores positivos
        double parteFracionaria = valor - parteInteira;

        return (int) (tamanhoTabela * parteFracionaria);
    }
}
