import java.util.ArrayList;
import java.util.List;

public class MetodoDelCodo {
    public static int ejecutarMetodoDelCodo(String filePath, int maxClusters, int maxIteraciones) {
        double[][] datos = DataSet.loadData(filePath);
        DataSet dataset = new DataSet(datos);

        List<Double> sseList = new ArrayList<>();
        
        // Calcula la SSE para cada valor de k desde 1 hasta maxClusters
        for (int k = 1; k <= maxClusters; k++) {
            KMeans kmeans = new KMeans(k);
            kmeans.ejecutarKMeans(dataset, maxIteraciones);

            // Calcula la suma de las distancias cuadradas para los clusters actuales
            double sse = calcularSSE(kmeans, dataset);
            sseList.add(sse);
            System.out.println("K = " + k + ", SSE = " + sse);
        }

        // Determinar el mejor K usando el método del codo
        return encontrarCodo(sseList);
    }

    private static double calcularSSE(KMeans kmeans, DataSet dataset) {
        double sse = 0.0;
        for (Cluster cluster : kmeans.clusters) {
            Punto centroide = cluster.getCentroide();
            for (Punto punto : cluster.getPuntos()) {
                double distancia = Punto.calcularDistancia(punto, centroide);
                sse += distancia * distancia; // Cuadrado de la distancia
            }
        }
        return sse;
    }

    private static int encontrarCodo(List<Double> sseList) {
        int mejorK = 1;
        double maxCurvatura = 0;

        for (int i = 1; i < sseList.size() - 1; i++) {
            double sseAnterior = sseList.get(i - 1);
            double sseActual = sseList.get(i);
            double sseSiguiente = sseList.get(i + 1);

            // Calcular la "curvatura" aproximada
            double curvatura = (sseAnterior - sseActual) - (sseActual - sseSiguiente);

            if (curvatura > maxCurvatura) {
                maxCurvatura = curvatura;
                mejorK = i + 1; // Ajuste porque los índices comienzan en 0
            }
        }
        System.out.println("El mejor valor de K encontrado es: " + mejorK);
        return mejorK;
    }
}
