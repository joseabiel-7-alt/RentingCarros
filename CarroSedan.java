public class CarroSedan extends Vehiculo {

    private String tipoCombustible;
    private String transmision;

    public CarroSedan(String placa, String marca, int modelo,
            float precioDiario, String tipoCombustible,
            String transmision) {

        super(placa, marca, modelo, precioDiario);

        this.tipoCombustible = tipoCombustible;
        this.transmision = transmision;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public void mostrar() {

        System.out.println(
                mostrarDatos() +
                        "\nTipo Combustible: " + tipoCombustible +
                        "\nTransmision: " + transmision);
    }
}