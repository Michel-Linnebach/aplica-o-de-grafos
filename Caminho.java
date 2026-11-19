import java.util.List;

public class Caminho<T> {
    private List<T> pontos;
    private double distancia;

    public Caminho(List<T> pontos, double distancia) {
        this.pontos = pontos; this.distancia = distancia;
    }
    public List<T> getPontos() { return pontos; }
    public double getDistancia() { return distancia; }
}
