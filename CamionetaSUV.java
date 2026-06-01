public class CamionetaSUV extends Vehiculo {

    private String traccion;
    private float capacidadMaletero;

    public CamionetaSUV(String placa, String marca, int modelo,
            float precioDiario, String traccion,
            float capacidadMaletero) {

        super(placa, marca, modelo, precioDiario);

        this.traccion = traccion;
        this.capacidadMaletero = capacidadMaletero;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public float getCapacidadMaletero() {
        return capacidadMaletero;
    }

    public void setCapacidadMaletero(float capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }

    public void mostrar() {

        System.out.println(
                mostrarDatos() +
                        "\nTraccion: " + traccion +
                        "\nCapacidad Maletero: "
                        + capacidadMaletero + " litros");
    }
}