import java.io.FileWriter;
import java.io.IOException;

public class Exportar {

    public String exportar(String contenido, String nombreArchivo) {

        try (FileWriter fw = new FileWriter(nombreArchivo)) {

            fw.write(contenido);

            return "Archivo " + nombreArchivo + " exportado correctamente.";

        } catch (IOException e) {

            return "Error al exportar archivo " + nombreArchivo + ": " + e.getMessage();
        }
    }
}