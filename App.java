import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        Validaciones v = new Validaciones();

       
        int op = 0;

        while (op != 5) {

            System.out.println("\n===== SISTEMA DE RENTING =====");
            System.out.println("1. Gestion de Clientes");
            System.out.println("2. Gestion de Vehiculos");
            System.out.println("3. Gestion de Contratos");
            System.out.println("4. Informe General");
            System.out.println("5. Salir");


            switch (op) {

                case 1:
                    System.out.println("Saliendo del sistema...");
                    break;

                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;

                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

            }
        }
    }
}