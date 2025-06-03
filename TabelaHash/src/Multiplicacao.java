public class Multiplicacao implements FuncaoHash {
    private static final double A = (Math.sqrt(5) - 1) / 2; // Constante áurea

    @Override
    public int hash(Registro registro, int tamanhoTabela) {
        long codigo = Long.parseLong(registro.getCodigo());
        double valor = codigo * A;
        return (int) (tamanhoTabela * (valor - Math.floor(valor)));
    }
}