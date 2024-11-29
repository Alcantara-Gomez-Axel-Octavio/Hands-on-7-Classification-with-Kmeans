import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DataSet {
    public double[][] datos;

    public DataSet(double[][] datos) {
        this.datos = datos;
    }

    public double[][] getDatos() {
        return datos;
    }

    public static double[][] loadData(String filePath) {
        List<double[]> elementos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                double[] punto = new double[valores.length];
                for (int i = 0; i < valores.length; i++) {
                    punto[i] = Double.parseDouble(valores[i]);
                }
                elementos.add(punto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementos.toArray(new double[0][]);
    }


    public double[] getLimitesMinimos() {
        double[] limitesMinimos = new double[datos[0].length];
        for (int j = 0; j < limitesMinimos.length; j++) {
            limitesMinimos[j] = Double.MAX_VALUE; // Inicializa con el máximo valor posible
            for (double[] punto : datos) {
                if (punto[j] < limitesMinimos[j]) {
                    limitesMinimos[j] = punto[j]; // Encuentra el mínimo
                }
            }
        }
        return limitesMinimos;
    }

    public double[] getLimitesMaximos() {
        double[] limitesMaximos = new double[datos[0].length];
        for (int j = 0; j < limitesMaximos.length; j++) {
            limitesMaximos[j] = Double.MIN_VALUE; // Inicializa con el mínimo valor posible
            for (double[] punto : datos) {
                if (punto[j] > limitesMaximos[j]) {
                    limitesMaximos[j] = punto[j]; // Encuentra el máximo
                }
            }
        }
        return limitesMaximos;
    }


    public double[] getRandomPoint() {
        Random random = new Random();
        return datos[random.nextInt(datos.length)];
    }

    public static void printData(double[][] datos) {
        for (double[] fila : datos) {
            for (double valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
}