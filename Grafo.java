import java.util.*;

public class Grafo<T> {
    private Map<T, Vertice<T>> vertices = new LinkedHashMap<>();

    public void adicionaVertice(T dado) {
        vertices.putIfAbsent(dado, new Vertice<>(dado));
    }

    public void adicionaAresta(double peso, T inicio, T fim) {
        Vertice<T> i = vertices.get(inicio), f = vertices.get(fim);
        if (i == null || f == null) throw new IllegalArgumentException("Vértice inexistente");
        i.adicionaArestaSaida(new Aresta<>(peso, i, f));
    }

    public boolean existe(T dado) { return vertices.containsKey(dado); }

    // Dijkstra ignorando arestas bloqueadas (formato "X-Y")
    private Caminho<T> dijkstra(T origem, T destino, Set<String> bloqueadas) {
        Map<T, Double> dist = new HashMap<>();
        Map<T, T> anterior = new HashMap<>();
        for (T v : vertices.keySet()) dist.put(v, Double.MAX_VALUE);
        dist.put(origem, 0.0);

        PriorityQueue<T> fila = new PriorityQueue<>(Comparator.comparingDouble(dist::get));
        fila.add(origem);

        while (!fila.isEmpty()) {
            T atual = fila.poll();
            if (atual.equals(destino)) break;
            for (Aresta<T> a : vertices.get(atual).getArestasSaida()) {
                T viz = a.getFim().getDado();
                if (bloqueadas.contains(atual + "-" + viz)) continue;
                double nova = dist.get(atual) + a.getPeso();
                if (nova < dist.get(viz)) {
                    dist.put(viz, nova);
                    anterior.put(viz, atual);
                    fila.remove(viz);
                    fila.add(viz);
                }
            }
        }
        if (dist.get(destino) == Double.MAX_VALUE) return null;

        LinkedList<T> pontos = new LinkedList<>();
        for (T v = destino; v != null; v = anterior.get(v)) pontos.addFirst(v);
        return new Caminho<>(pontos, dist.get(destino));
    }

    // Retorna até 2 caminhos, ordenados pelo menor
    public List<Caminho<T>> doisMenoresCaminhos(T origem, T destino) {
        List<Caminho<T>> resultado = new ArrayList<>();
        Caminho<T> melhor = dijkstra(origem, destino, new HashSet<>());
        if (melhor == null) return resultado;
        resultado.add(melhor);

        Caminho<T> segundo = null;
        List<T> p = melhor.getPontos();
        for (int i = 0; i < p.size() - 1; i++) {
            Set<String> bloq = new HashSet<>();
            bloq.add(p.get(i) + "-" + p.get(i + 1));
            bloq.add(p.get(i + 1) + "-" + p.get(i));
            Caminho<T> alt = dijkstra(origem, destino, bloq);
            if (alt != null && !alt.getPontos().equals(p)
                    && (segundo == null || alt.getDistancia() < segundo.getDistancia())) {
                segundo = alt;
            }
        }
        if (segundo != null) resultado.add(segundo);
        return resultado;
    }
}
