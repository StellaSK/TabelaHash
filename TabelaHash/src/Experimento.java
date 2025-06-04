import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experimento {
    private static final int[] TAMANHOS_TABELA = {1000, 10_000, 100_000};
    private static final int[] TAMANHOS_DADOS = {1_000_000, 5_000_000, 20_000_000};
    private static final String[] ARQUIVOS_DADOS = {
            "dados_1M.txt", "dados_5M.txt", "dados_20M.txt"
    };

    public static void main(String[] args) {
        // Gerar dados (executar uma vez)
        //GeradorDados.gerarDados(1_000_000, "dados_1M.txt", 12345);
        //GeradorDados.gerarDados(5_000_000, "dados_5M.txt", 12345);
        //GeradorDados.gerarDados(20_000_000, "dados_20M.txt", 12345);

        for (String arquivo : ARQUIVOS_DADOS) {
            List<Registro> dados = carregarDados(arquivo);
            for (int tamanhoTabela : TAMANHOS_TABELA) {
                testarFuncao("resto", dados, tamanhoTabela, arquivo);
                testarFuncao("multiplicacao", dados, tamanhoTabela, arquivo);
                testarFuncao("dobramento", dados, tamanhoTabela, arquivo);

            }
        }
    }

    private static List<Registro> carregarDados(String arquivo) {
        List<Registro> dados = new ArrayList<>();
        String caminho = "resources/" + arquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            int lineNum = 1;
            while ((linha = br.readLine()) != null) {
                try {
                    dados.add(new Registro(linha));
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro na linha " + lineNum + ": " + e.getMessage());
                }
                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dados;
    }

    private static void testarFuncao(String tipoFuncao, List<Registro> dados, int tamanhoTabela, String arquivo) {
        TabelaHash tabela = new TabelaHash(tamanhoTabela, tipoFuncao);
        String nomeFuncao = tipoFuncao;

        // Teste de inserção
        long inicio = System.currentTimeMillis();
        for (Registro reg : dados) {
            tabela.inserir(reg);
        }
        long tempoInsercao = System.currentTimeMillis() - inicio;
        int colisoes = tabela.getColisoes();

        // Teste de busca (5 amostras)
        long tempoBuscaTotal = 0;
        int comparacoesTotal = 0;
        Random rand = new Random(12345);

        for (int i = 0; i < 5; i++) {
            Registro alvo = dados.get(rand.nextInt(dados.size()));
            long inicioBusca = System.nanoTime();
            int comparacoes = tabela.buscar(alvo);
            tempoBuscaTotal += System.nanoTime() - inicioBusca;
            comparacoesTotal += comparacoes;
        }

        // Exportar resultados para CSV
        exportarResultados(arquivo, nomeFuncao, tamanhoTabela,
                tempoInsercao, colisoes,
                tempoBuscaTotal / 5, comparacoesTotal / 5);
    }

    private static void exportarResultados(String arquivoDados, String funcaoHash, int tamanhoTabela,
                                           long tempoInsercao, int colisoes,
                                           long tempoBuscaMedio, int comparacoesMedias) {
        String insercaoPath = "results/insercao.csv";
        String buscaPath = "results/busca.csv";

        try {
            // Escrever cabeçalhos se o arquivo for novo
            if (!new File(insercaoPath).exists()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(insercaoPath, true))) {
                    writer.println("ConjuntoDados,TamanhoTabela,FuncaoHash,Tempo(ms),Colisoes");
                }
            }

            if (!new File(buscaPath).exists()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(buscaPath, true))) {
                    writer.println("ConjuntoDados,TamanhoTabela,FuncaoHash,TempoMedio(ns),ComparacoesMedias");
                }
            }

            // Adicionar dados de inserção
            try (PrintWriter writer = new PrintWriter(new FileWriter(insercaoPath, true))) {
                writer.printf("%s,%d,%s,%d,%d%n",
                        arquivoDados, tamanhoTabela, funcaoHash, tempoInsercao, colisoes);
            }

            // Adicionar dados de busca
            try (PrintWriter writer = new PrintWriter(new FileWriter(buscaPath, true))) {
                writer.printf("%s,%d,%s,%d,%d%n",
                        arquivoDados, tamanhoTabela, funcaoHash, tempoBuscaMedio, comparacoesMedias);
            }

        } catch (Exception e) {
            System.err.println("Erro ao exportar resultados: " + e.getMessage());
        }
    }
}