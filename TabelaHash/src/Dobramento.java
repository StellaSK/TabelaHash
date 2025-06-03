public class Dobramento implements FuncaoHash {
    public int hash(Registro registro, int tamanhoTabela) {
        String codigo = registro.getCodigo();
        int soma = 0;

        for (int i = 0; i < 9; i += 3) {
            int grupo = 0;
            for (int j = 0; j < 3; j++) {
                char c = codigo.charAt(i + j);
                int digito = c - '0'; // Conversão manual de char para int
                grupo = grupo * 10 + digito;
            }
            soma += grupo;
        }

        return soma % tamanhoTabela;
        }
    }
