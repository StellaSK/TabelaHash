public class RestoDivisao implements FuncaoHash {

    @Override
    public int hash(Registro registro, int tamanhoTabela) {
        String codigo = registro.getCodigo();
        long valor = 0;

        for (int i = 0; i < codigo.length(); i++) {
            char c = codigo.charAt(i);
            int digito = c - '0'; // conversão manual
            valor = valor * 10 + digito;
        }

        return (int) (valor % tamanhoTabela);
    }
}
