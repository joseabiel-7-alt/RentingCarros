import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema();
        Validaciones v = new Validaciones();

        GestionClientes gc = new GestionClientes(sistema);
        GestionVehiculos gv = new GestionVehiculos(sistema);
        GestionRenting gr = new GestionRenting(sistema);

        int op = 0;

        while (op != 5) {

            System.out.println("\n===== SISTEMA DE RENTING =====");
            System.out.println("1. Gestion de Clientes");
            System.out.println("2. Gestion de Vehiculos");
            System.out.println("3. Gestion de Contratos");
            System.out.println("4. Informe General");
            System.out.println("5. Salir");

            op = v.validarEntero("Seleccione una opcion: ", sc);
            op = v.validarRango(1, 5, op, sc, "Seleccione una opcion: ");

            switch (op) {

                case 1:
                    gc.menuClientes(sc);
                    break;

                case 2:
                    gv.menuVehiculos(sc);
                    break;

                case 3:
                    gr.menuContratos(sc);
                    break;

                case 4:
                    System.out.println(sistema.informeGeneral());
                    System.out.println(sistema.exportarTodo());
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

            }
        }
    }
}
