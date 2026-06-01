import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Sistema {

    private LinkedList<Cliente> clientes;
    private LinkedList<Vehiculo> vehiculos;
    private LinkedList<ContratoRenting> contratos;

    private Queue<ContratoRenting> colaContratos;
    private Stack<ContratoRenting> historialContratos;

    public Sistema() {

        clientes = new LinkedList<>();
        vehiculos = new LinkedList<>();
        contratos = new LinkedList<>();

        colaContratos = new LinkedList<>();
        historialContratos = new Stack<>();

        ImportarArchivoTxt importador = new ImportarArchivoTxt();
        importador.leerClientes("Clientes", clientes);
        importador.leerVehiculos("Vehiculos", vehiculos);
        importador.leerContratos("Contratos", contratos, historialContratos);
    }

    public LinkedList<Cliente> getClientes() {
        return clientes;
    }

    public LinkedList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public LinkedList<ContratoRenting> getContratos() {
        return contratos;
    }

    public Queue<ContratoRenting> getColaContratos() {
        return colaContratos;
    }

    public Stack<ContratoRenting> getHistorialContratos() {
        return historialContratos;
    }

    public String informeGeneral() {

        String info = "\n===== INFORME GENERAL =====\n";

        float totalIngresos = 0;

        info += "\n===== CLIENTES =====\n";

        for (Cliente c : clientes) {

            info += "Cedula: " + c.getCedula()
                    + " | Nombre: " + c.getNombre()
                    + " " + c.getApellido()
                    + " | Telefono: " + c.getTelefono()
                    + "\n";
        }

        info += "\n===== VEHICULOS =====\n";

        for (Vehiculo v : vehiculos) {

            info += "Placa: " + v.getPlaca()
                    + " | Marca: " + v.getMarca()
                    + " | Modelo: " + v.getModelo()
                    + " | Estado: " + v.getEstado()
                    + "\n";
        }

        info += "\n===== CONTRATOS ACTIVOS =====\n";

        for (ContratoRenting c : contratos) {

            if (c.isActivo()) {

                info += "Contrato: " + c.getIdContrato()
                        + " | Cliente: " + c.getCedulaCliente()
                        + " | Vehiculo: " + c.getPlacaVehiculo()
                        + " | Valor: " + c.getValorTotal()
                        + "\n";

                totalIngresos += c.getValorTotal();
            }
        }

        info += "\n===== CONTRATOS FINALIZADOS =====\n";

        for (ContratoRenting c : historialContratos) {

            info += "Contrato: " + c.getIdContrato()
                    + " | Cliente: " + c.getCedulaCliente()
                    + " | Vehiculo: " + c.getPlacaVehiculo()
                    + " | Valor: " + c.getValorTotal()
                    + "\n";

            totalIngresos += c.getValorTotal();
        }

        info += "\nTOTAL INGRESOS GENERADOS: " + totalIngresos;

        return info;
    }

    public String exportarTodo() {

        Exportar e = new Exportar();
        return e.exportar(informeGeneral(), null);
    }
}
