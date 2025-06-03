public class RestoDivisao implements FuncaoHash {
    @Override
    public int hash(Registro registro, int tamanhoTabela) {
        long codigo = Long.parseLong(registro.getCodigo());
        return (int) (codigo % tamanhoTabela);
    }
}