public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\catss\\Desktop\\7mo\\Avina\\Codigos\\Kmeans\\Kmeans\\iris-dataset.csv";
        int maxClusters = 10; // Máximo número de clústeres para evaluar en el método del codo
        int maxIteraciones = 1000;

        // Ejecutar el método del codo
        MetodoDelCodo.ejecutarMetodoDelCodo(filePath, maxClusters, maxIteraciones);

        int k = 5; 
        double[][] datos = DataSet.loadData(filePath);
        DataSet dataset = new DataSet(datos);

        // Ejecutar KMeans con el número de clústeres óptimo
        KMeans kmeans = new KMeans(k);
        kmeans.ejecutarKMeans(dataset, maxIteraciones);
        kmeans.imprimirClusters();

         // Crear un nuevo punto y clasificarlo
         double[] coordenadasNuevoPunto = {5.1, 3.5, 1.4, 0.2}; // Ejemplo de un nuevo punto
         Punto nuevoPunto = new Punto(coordenadasNuevoPunto);
         int clusterAsignado = kmeans.asignarClusterAUnPunto(nuevoPunto);

    }
}
