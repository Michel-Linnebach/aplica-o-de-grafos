import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    private T dado;
    private List<Aresta<T>> arestasSaida = new ArrayList<>();

    public Vertice(T dado) { this.dado = dado; }
    public T getDado() { return dado; }
    public List<Aresta<T>> getArestasSaida() { return arestasSaida; }
    public void adicionaArestaSaida(Aresta<T> a) { arestasSaida.add(a); }
}
