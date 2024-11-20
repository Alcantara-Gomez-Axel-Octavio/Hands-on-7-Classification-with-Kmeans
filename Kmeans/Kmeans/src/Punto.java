// Clase para representar un punto de datos
class Punto {
    public double[] coordenadas;

    public Punto(double[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public double[] getCoordenadas() {
        return coordenadas;
    }

    public static double calcularDistancia(Punto p1, Punto p2) {
        double suma = 0;
        for (int i = 0; i < p1.coordenadas.length; i++) {
            suma += Math.pow(p1.coordenadas[i] - p2.coordenadas[i], 2);
        }
        return Math.sqrt(suma);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (!(obj instanceof Punto)) return false; 

        Punto otroPunto = (Punto) obj;
        if (this.coordenadas.length != otroPunto.coordenadas.length) return false; 

        for (int i = 0; i < this.coordenadas.length; i++) {
            if (this.coordenadas[i] != otroPunto.coordenadas[i]) {
                return false; 
            }
        }
        return true; 
    }

    @Override
    public int hashCode() {
        // Sobrescribir hashCode cuando se sobrescribe equals
        int result = 17;
        for (double coord : coordenadas) {
            long bits = Double.doubleToLongBits(coord);
            result = 31 * result + (int) (bits ^ (bits >>> 32));
        }
        return result;
    }
}