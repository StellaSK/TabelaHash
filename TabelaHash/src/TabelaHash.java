import java.util.LinkedList;

public class TabelaHash {
    private final int tamanho;
    private final LinkedList<Registro>[] tabela;
    private int colisoes;
    private final FuncaoHash funcaoHash;

    @SuppressWarnings("unchecked")
    public TabelaHash(int tamanho, FuncaoHash funcaoHash) {
        this.tamanho = tamanho;
        this.tabela = new LinkedList[tamanho];
        this.funcaoHash = funcaoHash;
        this.colisoes = 0;

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public void inserir(Registro registro) {
        int indice = funcaoHash.hash(registro, tamanho);
        if (!tabela[indice].isEmpty()) {
            colisoes++;
        }
        tabela[indice].add(registro);
    }

    public int buscar(Registro registro) {
        int indice = funcaoHash.hash(registro, tamanho);
        int comparacoes = 0;

        for (Registro r : tabela[indice]) {
            comparacoes++;
            if (r.getCodigo().equals(registro.getCodigo())) {
                return comparacoes;
            }
        }
        return comparacoes;
    }

    public int getColisoes() {
        return colisoes;
    }
}