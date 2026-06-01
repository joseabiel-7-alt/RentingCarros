import java.util.Scanner;

public class Validaciones {

    public int validarEntero(String msg, Scanner sc) {

        try {

            System.out.print(msg);
            return Integer.parseInt(sc.nextLine());

        } catch (Exception e) {

            System.out.println("Ingrese un numero valido.");
            return validarEntero(msg, sc);
        }
    }

    public int validarRango(int min, int max, int numero, Scanner sc, String msg) {

        if (numero < min || numero > max) {

            System.out.println("Ingrese una opcion entre " + min + " y " + max);
            numero = validarEntero(msg, sc);

            return validarRango(min, max, numero, sc, msg);
        }

        return numero;
    }

    public float validarFloat(String msg, Scanner sc) {

        try {

            System.out.print(msg);
            float n = Float.parseFloat(sc.nextLine());

            if (n <= 0) {

                System.out.println("Ingrese un valor positivo.");
                return validarFloat(msg, sc);
            }

            return n;

        } catch (Exception e) {

            System.out.println("Ingrese un decimal valido.");
            return validarFloat(msg, sc);
        }
    }

    public String validarTexto(String msg, Scanner sc) {

        System.out.print(msg);

        String dato = sc.nextLine().trim();

        if (dato.isEmpty()) {

            System.out.println("Campo obligatorio.");

            return validarTexto(msg, sc);
        }

        return dato;
    }

    public String validarNombre(String msg, Scanner sc) {

        System.out.print(msg);

        String dato = sc.nextLine().trim();

        dato = dato.replaceAll("\\s+", " ");

        String[] partes = dato.split(" ");

        if (partes.length > 2) {

            System.out.println("Nombre invalido.");

            return validarNombre(msg, sc);
        }

        if (!dato.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+( [a-zA-ZáéíóúÁÉÍÓÚñÑ]+)?")) {

            System.out.println("Solo letras.");

            return validarNombre(msg, sc);
        }

        return dato;
    }

    public String validarCedula(String msg, Scanner sc) {

        System.out.print(msg);
        String dato = sc.nextLine().trim();
        ;

        if (!dato.matches("[0-9]+")) {

            System.out.println("Solo numeros.");
            return validarCedula(msg, sc);
        }

        return dato;
    }

    public String validarPlaca(String msg, Scanner sc) {

        System.out.print(msg);
        String dato = sc.nextLine().trim();
        ;

        if (!dato.matches("[a-zA-Z0-9]+")) {

            System.out.println("No se permiten caracteres especiales.");
            return validarPlaca(msg, sc);
        }

        return dato;
    }

    public String validarTelefono(String msg, Scanner sc) {

        System.out.println("===== TIPO TELEFONO =====");
        System.out.println("1. Fijo, debe contener 7 digitos");
        System.out.println("2. Celular debe contener 10 digito");

        int tipo = validarRango(
                1,
                2,
                validarEntero("Seleccione opcion: ", sc),
                sc,
                "Seleccione opcion: ");

        System.out.print(msg);
        String dato = sc.nextLine().trim();
        ;

        if (!dato.matches("[0-9]+")) {

            System.out.println("Solo numeros.");
            return validarTelefono(msg, sc);
        }

        if (tipo == 1 && dato.length() != 7) {

            System.out.println("Telefono fijo debe tener 7 digitos.");
            return validarTelefono(msg, sc);
        }

        if (tipo == 2 && dato.length() != 10) {

            System.out.println("Celular debe tener 10 digitos.");
            return validarTelefono(msg, sc);
        }

        return dato;
    }

    public String validarFecha(String msg, Scanner sc) {

        System.out.print(msg);
        String fecha = sc.nextLine().trim();
        ;

        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {

            System.out.println("Formato invalido. Use dd/mm/yyyy");
            return validarFecha(msg, sc);
        }

        return fecha;
    }

    public String validarAlfanumerico(String msg, Scanner sc) {

        System.out.print(msg);
        String dato = sc.nextLine().trim();
        ;

        if (!dato.matches("[a-zA-Z0-9]+")) {

            System.out.println("Solo letras y numeros.");
            return validarAlfanumerico(msg, sc);
        }

        return dato;
    }

    public boolean fechaPosterior(String inicio, String fin) {

        String[] fi = inicio.split("/");
        String[] ff = fin.split("/");

        int diaInicio = Integer.parseInt(fi[0]);
        int mesInicio = Integer.parseInt(fi[1]);
        int anioInicio = Integer.parseInt(fi[2]);

        int diaFin = Integer.parseInt(ff[0]);
        int mesFin = Integer.parseInt(ff[1]);
        int anioFin = Integer.parseInt(ff[2]);

        if (anioFin < anioInicio) {
            return false;
        }

        if (anioFin == anioInicio && mesFin < mesInicio) {
            return false;
        }

        if (anioFin == anioInicio &&
                mesFin == mesInicio &&
                diaFin <= diaInicio) {
            return false;
        }

        return true;
    }

    public int validarModelo(String msg, Scanner sc) {

        int modelo = validarEntero(msg, sc);

        if (modelo < 1000 || modelo > 9999) {

            System.out.println("El modelo debe tener 4 digitos positivos.");

            return validarModelo(msg, sc);
        }

        return modelo;
    }

}