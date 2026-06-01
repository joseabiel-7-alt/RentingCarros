import java.util.Scanner;

public class GestionRenting {

    private Scanner sc = new Scanner(System.in);
    private Validaciones v = new Validaciones();

    public int menuContratos() {

        int op = 0;

        while (op != 6) {

            System.out.println("\n===== CONTRATOS =====");
            System.out.println("1. Registrar contrato");
            System.out.println("2. Modificar contrato");
            System.out.println("3. Finalizar contrato");
            System.out.println("4. Buscar contrato");
            System.out.println("5. Mostrar contratos");
            System.out.println("6. Volver");

            op = sc.nextInt();

            switch (op) {

                case 1:
                    System.out.println("Registrar contrato");
                    break;

                case 2:
                    System.out.println("Modificar contrato");
                    break;

                case 3:
                    System.out.println("Finalizar contrato");
                    break;

                case 4:
                    System.out.println("Buscar contrato");
                    break;

                case 5:
                    System.out.println("Mostrar contratos");
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