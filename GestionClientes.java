import java.util.LinkedList;
import java.util.Scanner;

public class GestionClientes {

    private Sistema sistema;
    private Validaciones v;

    public GestionClientes(Sistema sistema) {
        this.sistema = sistema;
        v = new Validaciones();
    }

    public LinkedList<Cliente> menuClientes(Scanner sc) {
        int op = 0;

        while (op != 6) {
            System.out.println("\n===== CLIENTES =====");
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
                    registrarCliente(sc);
                    break;
                case 2:
                    modificarCliente(sc);
                    break;
                case 3:
                    eliminarCliente(sc);
                    break;
                case 4:
                    buscarCliente(sc);
                    break;
                case 5:
                    mostrarClientes(); // void permitido
                    break;
                case 6:
                    StringBuilder sb = new StringBuilder();
                    for (Cliente c : sistema.getClientes()) {
                        sb.append("Cedula: ").append(c.getCedula()).append("\n");
                        sb.append("Nombre: ").append(c.getNombre()).append("\n");
                        sb.append("Apellido: ").append(c.getApellido()).append("\n");
                        sb.append("Telefono: ").append(c.getTelefono()).append("\n");
                        sb.append("Direccion: ").append(c.getDireccion()).append("\n");
                        sb.append("Licencia: ").append(c.getLicenciaConduccion()).append("\n");
                        sb.append("---\n"); // Separador de registro
                    }

                    
            }
        }
        return sistema.getClientes();
    }

    public LinkedList<Cliente> registrarCliente(Scanner sc) {
        String cedula = v.validarCedula("Cedula: ", sc);

        for (Cliente c : sistema.getClientes()) {
            if (c.getCedula().equalsIgnoreCase(cedula)) {
                System.out.println("La cedula ya existe.");
                return sistema.getClientes();
            }
        }

        Cliente c = new Cliente(
                cedula,
                v.validarNombre("Nombre: ", sc),
                v.validarNombre("Apellido: ", sc),
                v.validarTelefono("Telefono: ", sc),
                v.validarTexto("Direccion: ", sc),
                v.validarAlfanumerico("Licencia: ", sc));

        sistema.getClientes().add(c);
        System.out.println("Cliente registrado correctamente.");
        return sistema.getClientes();
    }

    public LinkedList<Cliente> modificarCliente(Scanner sc) {
        String cedula = v.validarCedula("Cedula: ", sc);

        for (Cliente c : sistema.getClientes()) {
            if (c.getCedula().equalsIgnoreCase(cedula)) {
                c.setNombre(v.validarNombre("Nuevo nombre: ", sc));
                c.setApellido(v.validarNombre("Nuevo apellido: ", sc));
                c.setTelefono(v.validarTelefono("Nuevo telefono: ", sc));
                c.setDireccion(v.validarTexto("Nueva direccion: ", sc));
                System.out.println("Cliente modificado.");
                return sistema.getClientes();
            }
        }
        System.out.println("Cliente no encontrado.");
        return sistema.getClientes();
    }

    public LinkedList<Cliente> eliminarCliente(Scanner sc) {
        String cedula = v.validarCedula("Cedula: ", sc);
        sistema.getClientes().removeIf(c -> c.getCedula().equalsIgnoreCase(cedula));
        sistema.getContratos().removeIf(c -> c.getCedulaCliente().equalsIgnoreCase(cedula));
        System.out.println("Cliente eliminado.");
        return sistema.getClientes();
    }

    public LinkedList<Cliente> buscarCliente(Scanner sc) {
        int continuar = 1;
        while (continuar == 1) {
            String cedula = v.validarCedula("Cedula: ", sc);
            boolean encontrado = false;

            for (Cliente c : sistema.getClientes()) {
                if (c.getCedula().equalsIgnoreCase(cedula)) {
                    System.out.println("\nCedula: " + c.getCedula() + "\nNombre: " + c.getNombre() + "\nApellido: "
                            + c.getApellido() + "\nTelefono: " + c.getTelefono() + "\nDireccion: " + c.getDireccion()
                            + "\nLicencia: " + c.getLicenciaConduccion());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Cliente no encontrado.");
            }
            System.out.println("\n1. Buscar otro");
            System.out.println("2. Volver");
            continuar = v.validarRango(1, 2, v.validarEntero("Opcion: ", sc), sc, "opcion: ");
            if (continuar == 2)
                break;
        }
        return sistema.getClientes();
    }

    public void mostrarClientes() {
        for (Cliente c : sistema.getClientes()) {
            System.out.println("\nCedula: " + c.getCedula() + "\nNombre: " + c.getNombre() + "\nApellido: "
                    + c.getApellido() + "\nTelefono: " + c.getTelefono() + "\nDireccion: " + c.getDireccion()
                    + "\nLicencia: " + c.getLicenciaConduccion());
        }
    }
}