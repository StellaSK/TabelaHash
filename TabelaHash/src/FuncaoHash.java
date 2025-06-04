public class FuncaoHash {

    // Função de hash por resto da divisão
    public static int hashRestoDivisao(String codigo, int tamanhoTabela) {
        long valor = converteParaLong(codigo);
        return (int) (valor % tamanhoTabela);
    }

    // Função de hash por multiplicação
    public static int hashMultiplicacao(String codigo, int tamanhoTabela) {
        long valor = converteParaLong(codigo);
        double A = 0.6180339887; // constante sugerida por Knuth
        double prod = valor * A;
        double parteFracionaria = prod - (int) prod;
        return (int) (tamanhoTabela * parteFracionaria);
    }

    // Função de hash por dobramento
    public static int hashDobramento(String codigo, int tamanhoTabela) {
        int soma = 0;
        int bloco = 4;
        for (int i = 0; i < codigo.length(); i += bloco) {
            String parte = "";
            for (int j = 0; j < bloco && i + j < codigo.length(); j++) {
                parte += codigo.charAt(i + j);
            }
            soma += converteParaInt(parte);
        }
        return soma % tamanhoTabela;
    }

    // Utilitário para converter String para long
    private static long converteParaLong(String str) {
        long valor = 0;
        for (int i = 0; i < str.length(); i++) {
            valor = valor * 10 + (str.charAt(i) - '0');
        }
        return valor;
    }

    // Utilitário para converter String para int (usado no dobramento)
    private static int converteParaInt(String str) {
        int valor = 0;
        for (int i = 0; i < str.length(); i++) {
            valor = valor * 10 + (str.charAt(i) - '0');
        }
        return valor;
    }
}
