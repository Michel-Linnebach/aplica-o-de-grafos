import java.util.List;
import java.util.Scanner;

public class AppGrafo {

    private static void ligar(Grafo<String> g, double peso, String a, String b) {
        g.adicionaAresta(peso, a, b);
        g.adicionaAresta(peso, b, a);
    }

    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();
        for (String v : "A B C D E F G H I J K L M N O P Q R S T U V X".split(" "))
            grafo.adicionaVertice(v);

        ligar(grafo, 300, "A", "B");
        ligar(grafo, 47,  "B", "C");
        ligar(grafo, 62,  "C", "D");
        ligar(grafo, 8,   "D", "E");
        ligar(grafo, 13,  "E", "F");
        ligar(grafo, 230, "E", "G");
        ligar(grafo, 141, "C", "H");
        ligar(grafo, 138, "H", "I");
        ligar(grafo, 153, "I", "J");
        ligar(grafo, 512, "J", "K");
        ligar(grafo, 135, "K", "L");
        ligar(grafo, 50,  "L", "M");
        ligar(grafo, 187, "L", "N");
        ligar(grafo, 108, "N", "O");
        ligar(grafo, 82,  "O", "P");
        ligar(grafo, 215, "P", "Q");
        ligar(grafo, 97,  "Q", "R");
        ligar(grafo, 33,  "R", "S");
        ligar(grafo, 38,  "S", "V");
        ligar(grafo, 370, "V", "A");
        ligar(grafo, 243, "R", "T");
        ligar(grafo, 207, "S", "T");
        ligar(grafo, 22,  "T", "U");
        ligar(grafo, 210, "V", "U");
        ligar(grafo, 107, "U", "X");
        ligar(grafo, 317, "X", "A");

        Scanner sc = new Scanner(System.in);
        System.out.println("=== Jardim Taquaral - Santo Amaro - São Paulo/SP ===");

        while (true) {
            System.out.print("\nPonto de partida (ou SAIR): ");
            String partida = sc.nextLine().trim().toUpperCase();
            if (partida.equals("SAIR")) break;
            System.out.print("Ponto de chegada: ");
            String chegada = sc.nextLine().trim().toUpperCase();

            if (!grafo.existe(partida) || !grafo.existe(chegada)) {
                System.out.println("Ponto inválido!");
                continue;
            }

            List<Caminho<String>> caminhos = grafo.doisMenoresCaminhos(partida, chegada);
            if (caminhos.isEmpty()) {
                System.out.println("Não há caminho entre os pontos.");
                continue;
            }
            for (int i = 0; i < caminhos.size(); i++) {
                Caminho<String> c = caminhos.get(i);
                System.out.println("\nOpção " + (i + 1) + ": " + String.join(" -> ", c.getPontos()));
                System.out.printf("Distância total: %.0f metros%n", c.getDistancia());
            }
        }
        sc.close();
    }
}
