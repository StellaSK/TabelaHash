public class TabelaHash {
    private ListaEncadeadaRegistro[] tabela;
    private int colisoes = 0;
    private String tipoFuncaoHash; // "resto", "multiplicacao" ou "dobramento"

    public TabelaHash(int tamanho, String tipoFuncaoHash) {
        tabela = new ListaEncadeadaRegistro[tamanho];
        this.tipoFuncaoHash = tipoFuncaoHash;
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeadaRegistro();
        }
    }

    public void inserir(Registro registro) {
        String codigo = registro.getCodigo();
        int indice = calcularIndice(codigo);
        if (!tabela[indice].estaVazia()) {
            colisoes++;
        }
        tabela[indice].inserir(registro);
    }

    public int buscar(Registro registro) {
        String codigo = registro.getCodigo();
        int indice = calcularIndice(codigo);
        return tabela[indice].buscar(registro);
    }

    private int calcularIndice(String codigo) {
        int tamanho = tabela.length;
        if (tipoFuncaoHash.equals("resto")) {
            return FuncaoHash.hashRestoDivisao(codigo, tamanho);
        } else if (tipoFuncaoHash.equals("multiplicacao")) {
            return FuncaoHash.hashMultiplicacao(codigo, tamanho);
        } else {
            return FuncaoHash.hashDobramento(codigo, tamanho);
        }
    }

    public int getColisoes() {
        return colisoes;
    }
}
