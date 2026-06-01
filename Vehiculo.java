public abstract class Vehiculo {

    protected String placa;
    protected String marca;
    protected int modelo;
    protected float precioDiario;
    protected String estado;

    public Vehiculo(String placa, String marca, int modelo, float precioDiario) {

        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precioDiario = precioDiario;
        this.estado = "disponible";
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public int getModelo() {
        return modelo;
    }

    public float getPrecioDiario() {
        return precioDiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setPrecioDiario(float precioDiario) {
        this.precioDiario = precioDiario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String mostrarDatos() {

        return "\nPlaca: " + placa +
                "\nMarca: " + marca +
                "\nModelo: " + modelo +
                "\nPrecio Diario: " + precioDiario +
                "\nEstado: " + estado;
    }

    public abstract void mostrar();
}