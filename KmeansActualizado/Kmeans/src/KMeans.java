import java.util.ArrayList;
import java.util.List;


public class KMeans {
    public int k;
    public List<Cluster> clusters;

    public KMeans(int k) {
        this.k = k;
        this.clusters = new ArrayList<>();
    }
    

    /*
    public void inicializarCentroides(DataSet dataset) {
        for (int i = 0; i < k; i++) {
            double[] coordenadasCentroide = dataset.getRandomPoint();
            clusters.add(new Cluster(new Punto(coordenadasCentroide)));
        }
    }
    */

    public void inicializarCentroides(DataSet dataset) {
        double[] limitesMinimos = dataset.getLimitesMinimos();
        double[] limitesMaximos = dataset.getLimitesMaximos();
        
        for (int i = 0; i < k; i++) {
            double[] coordenadasCentroide = new double[limitesMinimos.length];
            for (int j = 0; j < coordenadasCentroide.length; j++) {
                // Generar un punto aleatorio en el rango de los límites
                coordenadasCentroide[j] = limitesMinimos[j] + Math.random() * (limitesMaximos[j] - limitesMinimos[j]);
            }
            
            clusters.add(new Cluster(new Punto(coordenadasCentroide)));
        }
    }
    
    

    public void asignarPuntosAClusters(double[][] datos) {
        for (double[] punto : datos) {
            Punto p = new Punto(punto);
            Cluster clusterMasCercano = null;
            double distanciaMinima = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double distancia = Punto.calcularDistancia(p, cluster.getCentroide());
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    clusterMasCercano = cluster;
                }
            }
            clusterMasCercano.addPunto(p);
        }
    }

    public void actualizarCentroides() {
        for (Cluster cluster : clusters) {
            cluster.actualizarCentroide();
        }
    }

    public void ejecutarKMeans(DataSet dataset, int maxIteraciones) {
        inicializarCentroides(dataset);
        for (int i = 0; i < maxIteraciones; i++) {
            
            for (Cluster cluster : clusters) {
                cluster.limpiarPuntos();
            }
            asignarPuntosAClusters(dataset.getDatos());
            
            // Almacena los centroides anteriores para comparar después
            List<Punto> centroidesAnteriores = new ArrayList<>();
            for (Cluster cluster : clusters) {
                centroidesAnteriores.add(cluster.getCentroide());
            }
            
            actualizarCentroides();
    
            // Verifica si los centroides han cambiado
            boolean centroidesHanCambiado = false;
            for (int j = 0; j < clusters.size(); j++) {
                if (!centroidesAnteriores.get(j).equals(clusters.get(j).getCentroide())) {
                    centroidesHanCambiado = true;
                    
                    break;
                }
            }        
            
        }     
        
    }
    

    public void imprimirClusters() {
        int i = 1;
        for (Cluster cluster : clusters) {
            System.out.println("Cluster " + i + ":");
            for (Punto punto : cluster.getPuntos()) {
                for (double coord : punto.getCoordenadas()) {
                    System.out.print(coord + " ");
                }
                System.out.println();
            }
            System.out.println();
            i++;
        }
    }

     public int asignarClusterAUnPunto(Punto nuevoPunto) {
        Cluster clusterMasCercano = null;
        double distanciaMinima = Double.MAX_VALUE;
        int indiceClusterMasCercano = -1;

        for (int i = 0; i < clusters.size(); i++) {
            Cluster cluster = clusters.get(i);
            double distancia = Punto.calcularDistancia(nuevoPunto, cluster.getCentroide());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                clusterMasCercano = cluster;
                indiceClusterMasCercano = i;
            }
        }
        System.out.println("El nuevo punto se asigna al clúster " + (indiceClusterMasCercano + 1));
        return indiceClusterMasCercano; // Retorna el índice del clúster más cercano
    }
}