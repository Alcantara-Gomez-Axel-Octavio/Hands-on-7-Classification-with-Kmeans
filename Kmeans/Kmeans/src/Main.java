public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\catss\\Desktop\\7mo\\Avina\\Kmeans\\Kmeans\\iris-dataset.csv";
        double[][] datos = DataSet.loadData(filePath);
        DataSet dataset = new DataSet(datos);

        int k = 5; 
        int maxIteraciones = 1000; 

        KMeans kmeans = new KMeans(k);
        kmeans.ejecutarKMeans(dataset, maxIteraciones);
        kmeans.imprimirClusters();
       
    }
}