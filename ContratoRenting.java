public class ContratoRenting {

    private String idContrato;
    private String cedulaCliente;
    private String placaVehiculo;
    private String fechaInicio;
    private String fechaFin;
    private int totalDias;
    private float valorTotal;
    private boolean activo;

    public ContratoRenting(String idContrato, String cedulaCliente,
            String placaVehiculo, String fechaInicio,
            String fechaFin, int totalDias,
            float valorTotal) {

        this.idContrato = idContrato;
        this.cedulaCliente = cedulaCliente;
        this.placaVehiculo = placaVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalDias = totalDias;
        this.valorTotal = valorTotal;
        this.activo = true;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void finalizarContrato() {
        activo = false;
    }

    public void mostrar() {

        System.out.println("Contrato: " + idContrato);
        System.out.println("Cliente: " + cedulaCliente);
        System.out.println("Vehiculo: " + placaVehiculo);
        System.out.println("Fecha inicio: " + fechaInicio);
        System.out.println("Fecha fin: " + fechaFin);
        System.out.println("Dias: " + totalDias);
        System.out.println("Valor: " + valorTotal);
        System.out.println("Activo: " + activo);
    }
}