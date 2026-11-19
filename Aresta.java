public class Aresta<T> {
    private double peso;
    private Vertice<T> inicio;
    private Vertice<T> fim;

    public Aresta(double peso, Vertice<T> inicio, Vertice<T> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public double getPeso() { return peso; }
    public Vertice<T> getInicio() { return inicio; }
    public Vertice<T> getFim() { return fim; }
}
