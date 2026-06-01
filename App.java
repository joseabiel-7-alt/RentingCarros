import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GestionClientes gc = new GestionClientes();
        GestionVehiculos gv = new GestionVehiculos();
        GestionRenting gr = new GestionRenting();

        int op = 0;

        while (op != 5) {

            System.out.println("\n===== SISTEMA DE RENTING =====");
            System.out.println("1. Gestion de Clientes");
            System.out.println("2. Gestion de Vehiculos");
            System.out.println("3. Gestion de Contratos");
            System.out.println("4. Informe General");
            System.out.println("5. Salir");

            op = sc.nextInt();

            switch (op) {

                case 1:
                    gc.menuClientes();
                    break;

                case 2:
                    gv.menuVehiculos();
                    break;

                case 3:
                    gr.menuContratos();
                    break;

                case 4:
                    System.out.println("Informe General");
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}