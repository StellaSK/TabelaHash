public class Dobramento implements FuncaoHash {
    @Override
    public int hash(Registro registro, int tamanhoTabela) {
        String codigo = registro.getCodigo();
        int soma = 0;
        for (int i = 0; i < 9; i += 3) {
            soma += Integer.parseInt(codigo.substring(i, i + 3));
        }
        return soma % tamanhoTabela;
    }
}