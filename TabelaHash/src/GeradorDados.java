import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class GeradorDados {
    public static void gerarDados(int quantidade, String arquivo, long seed) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/" + arquivo))) {
            Random rand = new Random(seed);
            for (int i = 0; i < quantidade; i++) {
                // Garante sempre 9 dígitos com zeros à esquerda
                String codigo = String.format("%09d", rand.nextInt(1_000_000_000));
                writer.write(codigo + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}