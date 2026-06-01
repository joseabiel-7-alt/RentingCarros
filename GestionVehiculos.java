import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class GestionVehiculos {

    private Sistema sistema;
    private Validaciones v;

    public GestionVehiculos(Sistema sistema) {

        this.sistema = sistema;
        v = new Validaciones();
    }

    public LinkedList<Vehiculo> menuVehiculos(Scanner sc) {

        int op = 0;

        while (op != 6) {

            System.out.println("===== VEHICULOS =====");
            System.out.println("1. Registrar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Buscar");
            System.out.println("5. Mostrar");
            System.out.println("6. Volver");

            op = v.validarEntero("Opcion: ", sc);
            op = v.validarRango(1, 6, op, sc, "Opcion: ");

            switch (op) {

                case 1:
                    registrarVehiculo(sc);
                    break;

                case 2:
                    modificarVehiculo(sc);
                    break;

                case 3:
                    eliminarVehiculo(sc);
                    break;

                case 4:
                    buscarVehiculo(sc);
                    break;

                case 5:
                    mostrarVehiculos();
                    break;
                case 6:
                    StringBuilder sb = new StringBuilder();
                    for (Vehiculo v : sistema.getVehiculos()) {
                        // Escribir datos comunes
                        sb.append("Placa: ").append(v.getPlaca()).append("\n");
                        sb.append("Marca: ").append(v.getMarca()).append("\n");
                        sb.append("Modelo: ").append(v.getModelo()).append("\n");
                        sb.append("Precio: ").append(String.format(Locale.US, "%.2f", v.getPrecioDiario()))
                                .append("\n");
                        sb.append("Estado: ").append(v.getEstado()).append("\n");

                        if (v instanceof CarroSedan) {
                            CarroSedan c = (CarroSedan) v;
                            sb.append("Tipo: SEDAN\n");
                            sb.append("Combustible: ").append(c.getTipoCombustible()).append("\n");
                            sb.append("Transmision: ").append(c.getTransmision()).append("\n");
                        } else if (v instanceof CamionetaSUV) {
                            CamionetaSUV s = (CamionetaSUV) v;
                            sb.append("Tipo: SUV\n");
                            sb.append("Traccion: ").append(s.getTraccion()).append("\n");
                            sb.append("Capacidad: ").append(String.format(Locale.US, "%.2f", s.getCapacidadMaletero()))
                                    .append("\n");
                        }

                        sb.append("---\n"); // Separador de registro
                    }

                  
            }
        }
        return null;
    }

    public LinkedList<Vehiculo> registrarVehiculo(Scanner sc) {

        int tipo = v.validarEntero("1. Sedan  2. SUV: ", sc);
        tipo = v.validarRango(1, 2, tipo, sc, "Tipo: ");

        String placa = v.validarPlaca("Placa: ", sc);

        for (Vehiculo ve : sistema.getVehiculos()) {

            if (ve.getPlaca().equalsIgnoreCase(placa)) {

                System.out.println("La placa ya existe.");
                return sistema.getVehiculos();
            }
        }

        String marca = v.validarNombre("Marca: ", sc);
        int modelo = v.validarModelo("Modelo: ", sc);
        float precio = v.validarFloat("Precio diario: ", sc);

        if (tipo == 1) {

            System.out.println("===== TIPO COMBUSTIBLE =====");
            System.out.println("1. Gasolina");
            System.out.println("2. Diesel");
            System.out.println("3. Electrico");

            int opCombustible = v.validarRango(
                    1,
                    3,
                    v.validarEntero("Seleccione opcion: ", sc),
                    sc,
                    "Seleccione opcion: ");

            String combustible = "";

            switch (opCombustible) {

                case 1:
                    combustible = "Gasolina";
                    break;

                case 2:
                    combustible = "Diesel";
                    break;

                case 3:
                    combustible = "Electrico";
                    break;
            }

            System.out.println("===== TRANSMISION =====");
            System.out.println("1. Manual");
            System.out.println("2. Automatica");

            int opTransmision = v.validarRango(
                    1,
                    2,
                    v.validarEntero("Seleccione opcion: ", sc),
                    sc,
                    "Seleccione opcion: ");

            String transmision = "";

            switch (opTransmision) {

                case 1:
                    transmision = "Manual";
                    break;

                case 2:
                    transmision = "Automatica";
                    break;
            }

            sistema.getVehiculos().add(new CarroSedan(
                    placa,
                    marca,
                    modelo,
                    precio,
                    combustible,
                    transmision));

        } else {

            System.out.println("===== TRACCION =====");
            System.out.println("1. 4x2");
            System.out.println("2. 4x4");

            int opTraccion = v.validarRango(
                    1,
                    2,
                    v.validarEntero("Seleccione opcion: ", sc),
                    sc,
                    "Seleccione opcion: ");

            String traccion = "";

            switch (opTraccion) {

                case 1:
                    traccion = "4x2";
                    break;

                case 2:
                    traccion = "4x4";
                    break;
            }

            float capacidad = v.validarFloat(
                    "Capacidad maletero en litros: ",
                    sc);

            sistema.getVehiculos().add(new CamionetaSUV(
                    placa,
                    marca,
                    modelo,
                    precio,
                    traccion,
                    capacidad));
        }

        System.out.println("Vehiculo registrado correctamente.");
        return null;
    }

    public LinkedList<Vehiculo> modificarVehiculo(Scanner sc) {

        String placa = v.validarPlaca("Placa: ", sc);

        for (Vehiculo ve : sistema.getVehiculos()) {

            if (ve.getPlaca().equalsIgnoreCase(placa)) {

                ve.setMarca(v.validarNombre("Nueva marca: ", sc));
                ve.setModelo(v.validarEntero("Nuevo modelo: ", sc));
                ve.setPrecioDiario(v.validarFloat("Nuevo precio: ", sc));

                System.out.println("Vehiculo modificado.");
                return sistema.getVehiculos();
            }
        }

        System.out.println("Vehiculo no encontrado.");

        return null;
    }

    public LinkedList<Vehiculo> eliminarVehiculo(Scanner sc) {

        String placa = v.validarPlaca("Placa: ", sc);

        sistema.getVehiculos().removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
        sistema.getContratos().removeIf(c -> c.getPlacaVehiculo().equalsIgnoreCase(placa));

        System.out.println("Vehiculo eliminado.");
        return null;
    }

    public LinkedList<Vehiculo> buscarVehiculo(Scanner sc) {

        int continuar = 1;

        while (continuar == 1) {

            String placa = v.validarPlaca("Placa: ", sc);

            boolean encontrado = false;

            for (Vehiculo ve : sistema.getVehiculos()) {

                if (ve.getPlaca().equalsIgnoreCase(placa)) {

                    ve.mostrar();

                    encontrado = true;
                }
            }

            if (!encontrado) {

                System.out.println("Vehiculo no encontrado.");
            }

            System.out.println("\n1. Buscar otro");
            System.out.println("2. Volver");

            continuar = v.validarRango(
                    1,
                    2,
                    v.validarEntero("Opcion: ", sc),
                    sc,
                    "Opcion: ");
        }

        return sistema.getVehiculos();
    }

    public void mostrarVehiculos() {

        for (Vehiculo ve : sistema.getVehiculos()) {

            ve.mostrar();
        }
    }
}
