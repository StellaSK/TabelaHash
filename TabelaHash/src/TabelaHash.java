public class TabelaHash {
    private final int tamanho;
    private final ListaEncadeadaRegistro[] tabela;
    private int colisoes;
    private final FuncaoHash funcaoHash;


    public TabelaHash(int tamanho, FuncaoHash funcaoHash) {
        this.tamanho = tamanho;
        this.funcaoHash = funcaoHash;
        this.colisoes = 0;
        this.tabela = new ListaEncadeadaRegistro[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeadaRegistro();
        }
    }

    public void inserir(Registro registro) {
        int indice = funcaoHash.hash(registro, tamanho);
        if (!tabela[indice].estaVazia()) {
            colisoes++;
        }
        tabela[indice].inserir(registro);
    }

    public int buscar(Registro registro) {
        int indice = funcaoHash.hash(registro, tamanho);
        return tabela[indice].buscar(registro);
    }

    public int getColisoes() {
        return colisoes;
    }
}
