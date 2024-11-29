class Centroide {
    public double[] detalles;
    public String nombre;

    public Centroide(double[] detalles, String nombre) {
        this.detalles = detalles;
        this.nombre = nombre;
    }

    public double[] getDetalles() {
        return detalles;
    }

    public void setDetalles(double[] detalles) {
        this.detalles = detalles;
    }

    public String getNombre() {
        return nombre;
    }
}