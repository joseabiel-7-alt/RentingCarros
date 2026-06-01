
import java.util.Scanner;

public class GestionClientes {

    private Scanner sc = new Scanner(System.in);
    private Validaciones v = new Validaciones();

    public int menuClientes() {

        int op = 0;

        while (op != 6) {

            System.out.println("\n===== CLIENTES =====");
            System.out.println("1. Registrar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Buscar");
            System.out.println("5. Mostrar");
            System.out.println("6. Volver");

            op = sc.nextInt();

            switch (op) {

                case 1:
                    System.out.println("Registrar cliente");
                    break;

                case 2:
                    System.out.println("Modificar cliente");
                    break;

                case 3:
                    System.out.println("Eliminar cliente");
                    break;

                case 4:
                    System.out.println("Buscar cliente");
                    break;

                case 5:
                    System.out.println("Mostrar clientes");
                    break;

                case 6:
                    System.out.println("Volviendo al menu principal...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        }

        return op;
    }
}