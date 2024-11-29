import java.util.ArrayList;
import java.util.List;

// Clase para representar un cluster
public class Cluster {
    public Punto centroide;
    public List<Punto> puntos;

    public Cluster(Punto centroide) {
        this.centroide = centroide;
        this.puntos = new ArrayList<>();
    }

    public Punto getCentroide() {
        return centroide;
    }

    public List<Punto> getPuntos() {
        return puntos;
    }

    public void addPunto(Punto punto) {
        puntos.add(punto);
    }

    public void limpiarPuntos() {
        puntos.clear();
    }

    public void actualizarCentroide() {
        int dimension = centroide.getCoordenadas().length;
        double[] nuevoCentroide = new double[dimension];
        if (puntos.isEmpty()) return; // Evitar división por cero
        for (Punto punto : puntos) {
            for (int i = 0; i < dimension; i++) {
                nuevoCentroide[i] += punto.getCoordenadas()[i];
            }
        }
        for (int i = 0; i < dimension; i++) {
            nuevoCentroide[i] /= puntos.size();
        }
        centroide = new Punto(nuevoCentroide);
    }
    
}