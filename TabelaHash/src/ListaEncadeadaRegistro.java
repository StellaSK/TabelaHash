public class ListaEncadeadaRegistro {
    class No {
        Registro dado;
        No proximo;

        No(Registro dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No inicio;

    public ListaEncadeadaRegistro() {
        this.inicio = null;
    }

    public void inserir(Registro dado) {
        No novo = new No(dado);
        if (inicio == null) {
            inicio = novo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public int buscar(Registro procurado) {
        No atual = inicio;
        int comparacoes = 0;
        while (atual != null) {
            comparacoes++;
            if (atual.dado.getCodigo().equals(procurado.getCodigo())) {
                return comparacoes;
            }
            atual = atual.proximo;
        }
        return comparacoes;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
