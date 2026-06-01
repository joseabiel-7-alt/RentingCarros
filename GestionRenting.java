import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class GestionRenting {

    private Sistema sistema;
    private Validaciones v;

    public GestionRenting(Sistema sistema) {

        this.sistema = sistema;
        v = new Validaciones();
    }

    public LinkedList<ContratoRenting> menuContratos(Scanner sc) {

        int op = 0;

        while (op != 6) {

            System.out.println("===== CONTRATOS =====");
            System.out.println("1. Registrar contrato");
            System.out.println("2. Modificar contrato");
            System.out.println("3. Finalizar contrato");
            System.out.println("4. Buscar contrato");
            System.out.println("5. Mostrar contratos");
            System.out.println("6. Volver");

            op = v.validarEntero("Opcion: ", sc);
            op = v.validarRango(1, 6, op, sc, "Opcion: ");

            switch (op) {

                case 1:
                    registrarContrato(sc);
                    break;

                case 2:
                    modificarContrato(sc);
                    break;

                case 3:
                    finalizarContrato(sc);
                    break;

                case 4:
                    buscarContrato(sc);
                    break;

                case 5:
                    mostrarContratos();
                    break;
                case 6:

                    StringBuilder sb = new StringBuilder();

                    for (ContratoRenting c : sistema.getContratos()) {
                        if (c.isActivo()) {
                            sb.append("ID: ").append(c.getIdContrato()).append("\n");
                            sb.append("Cliente: ").append(c.getCedulaCliente()).append("\n");
                            sb.append("Vehiculo: ").append(c.getPlacaVehiculo()).append("\n");
                            sb.append("Inicio: ").append(c.getFechaInicio()).append("\n");
                            sb.append("Fin: ").append(c.getFechaFin()).append("\n");
                            sb.append("Dias: ").append(c.getTotalDias()).append("\n");
                            // CORRECCIÓN AQUÍ: Agregar Locale.US
                            sb.append("Valor: ").append(String.format(Locale.US, "%.2f", c.getValorTotal()))
                                    .append("\n");
                            sb.append("Estado: Activo\n");
                            sb.append("---\n");
                        }
                    }

                    for (ContratoRenting c : sistema.getHistorialContratos()) {
                        sb.append("ID: ").append(c.getIdContrato()).append("\n");
                        sb.append("Cliente: ").append(c.getCedulaCliente()).append("\n");
                        sb.append("Vehiculo: ").append(c.getPlacaVehiculo()).append("\n");
                        sb.append("Inicio: ").append(c.getFechaInicio()).append("\n");
                        sb.append("Fin: ").append(c.getFechaFin()).append("\n");
                        sb.append("Dias: ").append(c.getTotalDias()).append("\n");

                        sb.append("Valor: ").append(String.format(Locale.US, "%.2f", c.getValorTotal())).append("\n");
                        sb.append("Estado: Finalizado\n");
                        sb.append("---\n");
                    }

                    
            }
        }
        return null;
    }

    public LinkedList<ContratoRenting> registrarContrato(Scanner sc) {

        String id = v.validarPlaca("Id contrato: ", sc);

        for (ContratoRenting c : sistema.getContratos()) {

            if (c.getIdContrato().equalsIgnoreCase(id)) {

                System.out.println("El contrato ya existe.");
                return sistema.getContratos();
            }
        }

        String cedula = v.validarCedula("Cedula cliente: ", sc);

        boolean clienteExiste = false;

        for (Cliente c : sistema.getClientes()) {

            if (c.getCedula().equalsIgnoreCase(cedula)) {

                clienteExiste = true;
            }
        }

        if (!clienteExiste) {

            System.out.println("Cliente no encontrado.");
            return null;
        }

        for (ContratoRenting c : sistema.getContratos()) {

            if (c.getCedulaCliente().equalsIgnoreCase(cedula) && c.isActivo()) {

                System.out.println("El cliente ya tiene contrato activo.");
                return null;
            }
        }

        String placa = v.validarPlaca("Placa vehiculo: ", sc);
        Vehiculo vehiculo = null;

        for (Vehiculo ve : sistema.getVehiculos()) {

            if (ve.getPlaca().equalsIgnoreCase(placa)) {

                vehiculo = ve;
            }
        }

        if (vehiculo == null) {

            System.out.println("Vehiculo no encontrado.");
            return null;
        }

        if (vehiculo.getEstado().equalsIgnoreCase("alquilado")) {

            System.out.println("Vehiculo alquilado.");
            return null;
        }

        String fechaInicio = v.validarFecha("Fecha inicio: ", sc);
        String fechaFin = v.validarFecha("Fecha fin: ", sc);

        while (!v.fechaPosterior(fechaInicio, fechaFin)) {

            System.out.println("La fecha final debe ser posterior.");

            fechaFin = v.validarFecha("Fecha fin: ", sc);
        }

        LocalDate inicio = LocalDate.parse(fechaInicio,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalDate fin = LocalDate.parse(fechaFin,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int dias = (int) ChronoUnit.DAYS.between(inicio, fin);

        dias = dias + 1;

        float total = dias * vehiculo.getPrecioDiario();

        ContratoRenting contrato = new ContratoRenting(
                id,
                cedula,
                placa,
                fechaInicio,
                fechaFin,
                dias,
                total);

        sistema.getContratos().add(contrato);
        sistema.getColaContratos().add(contrato);

        vehiculo.setEstado("alquilado");

        System.out.println("Contrato registrado correctamente.");
        return null;
    }

    public LinkedList<ContratoRenting> modificarContrato(Scanner sc) {

        String id = v.validarPlaca("Id contrato: ", sc);

        for (ContratoRenting c : sistema.getContratos()) {

            if (c.getIdContrato().equalsIgnoreCase(id)) {

                c.setFechaInicio(v.validarFecha("Nueva fecha inicio: ", sc));
                c.setFechaFin(v.validarFecha("Nueva fecha fin: ", sc));

                int dias = v.validarEntero("Nuevos dias: ", sc);
                c.setTotalDias(dias);

                for (Vehiculo ve : sistema.getVehiculos()) {

                    if (ve.getPlaca().equalsIgnoreCase(c.getPlacaVehiculo())) {

                        c.setValorTotal(dias * ve.getPrecioDiario());
                    }
                }

                System.out.println("Contrato modificado.");
                return sistema.getContratos();
            }
        }

        System.out.println("Contrato no encontrado.");
        return null;
    }

    public LinkedList<ContratoRenting> finalizarContrato(Scanner sc) {

        String id = v.validarPlaca("Id contrato: ", sc);

        for (ContratoRenting c : sistema.getContratos()) {

            if (c.getIdContrato().equalsIgnoreCase(id) && c.isActivo()) {

                c.finalizarContrato();

                sistema.getHistorialContratos().push(c);

                for (Vehiculo ve : sistema.getVehiculos()) {

                    if (ve.getPlaca().equalsIgnoreCase(c.getPlacaVehiculo())) {

                        ve.setEstado("disponible");
                    }
                }

                System.out.println("Contrato finalizado.");
                return sistema.getContratos();
            }
        }

        System.out.println("Contrato no encontrado.");
        return null;
    }

    public LinkedList<ContratoRenting> buscarContrato(Scanner sc) {

        int continuar = 1;

        while (continuar == 1) {

            String id = v.validarPlaca("Id contrato: ", sc);

            boolean encontrado = false;
            for (ContratoRenting c : sistema.getContratos()) {
                if (c.getIdContrato().equalsIgnoreCase(id)) {
                    c.mostrar();
                    encontrado = true;

                }

            }
            if (!encontrado) {
                System.out.println("Contrato no encontrado.");
            }

            System.out.println("\n1. Buscar otro");
            System.out.println("2. Volver");

            continuar = v.validarRango(1, 2, v.validarEntero("Opcion: ", sc), sc, "opcion: ");

        }

        return sistema.getContratos();
    }

    public void mostrarContratos() {

        for (ContratoRenting c : sistema.getContratos()) {

            c.mostrar();
        }
    }
}
