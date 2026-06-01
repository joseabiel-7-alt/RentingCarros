import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class ImportarArchivoTxt {

    public LinkedList<Cliente> leerClientes(String nombreArchivo, LinkedList<Cliente> listaClientes) {
        String ruta = nombreArchivo + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            String cedula = "", nombre = "", apellido = "", telefono = "", direccion = "", licencia = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Cedula: "))
                    cedula = line.substring(8).trim();
                else if (line.startsWith("Nombre: "))
                    nombre = line.substring(8).trim();
                else if (line.startsWith("Apellido: "))
                    apellido = line.substring(9).trim();
                else if (line.startsWith("Telefono: "))
                    telefono = line.substring(9).trim();
                else if (line.startsWith("Direccion: "))
                    direccion = line.substring(10).trim();
                else if (line.startsWith("Licencia: "))
                    licencia = line.substring(9).trim();

                if (line.isEmpty() || line.startsWith("---")) {
                    if (!cedula.isEmpty()) {
                        listaClientes.add(new Cliente(cedula, nombre, apellido, telefono, direccion, licencia));

                        cedula = nombre = apellido = telefono = direccion = licencia = "";
                    }
                }
            }

            if (!cedula.isEmpty()) {
                listaClientes.add(new Cliente(cedula, nombre, apellido, telefono, direccion, licencia));
            }

        } catch (IOException e) {
            System.out.println("No se encontró archivo " + nombreArchivo + ".txt");
        }
        return listaClientes;
    }

    public LinkedList<Vehiculo> leerVehiculos(String nombreArchivo, LinkedList<Vehiculo> vs) {
        String ruta = nombreArchivo + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;

            String marca = "";
            int modelo = 0;
            double precio = 0.0;
            String estado = "";
            String tipo = "";

            String combustible = "";
            String transmision = "";
            String traccion = "";
            double capacidad = 0.0;
            String placa = "";

            while ((line = br.readLine()) != null) {

                if (line.startsWith("Placa: ")) {
                    placa = line.substring(7).trim();
                } else if (line.startsWith("Marca: ")) {
                    marca = line.substring(7).trim();
                } else if (line.startsWith("Modelo: ")) {
                    modelo = Integer.parseInt(line.substring(8).trim());
                } else if (line.startsWith("Precio: ")) {
                    precio = Double.parseDouble(line.substring(8).trim());
                } else if (line.startsWith("Estado: ")) {
                    estado = line.substring(8).trim(); // LEEMOS el estado
                } else if (line.startsWith("Tipo: ")) {
                    tipo = line.substring(6).trim();
                }

                else if (line.startsWith("Combustible: ")) {
                    combustible = line.substring(13).trim();
                } else if (line.startsWith("Transmision: ")) {
                    transmision = line.substring(13).trim();
                }

                else if (line.startsWith("Traccion: ")) {
                    traccion = line.substring(10).trim();
                } else if (line.startsWith("Capacidad: ")) {
                    capacidad = Double.parseDouble(line.substring(11).trim());
                }

                if (line.isEmpty() || line.startsWith("---")) {
                    if (!marca.isEmpty()) {
                        Vehiculo v = null;

                        if (tipo.equals("SEDAN")) {

                            v = new CarroSedan(
                                    placa.isEmpty() ? "TEMP" : placa,
                                    marca,
                                    modelo,
                                    (float) precio,
                                    combustible,
                                    transmision);
                        } else if (tipo.equals("SUV")) {

                            v = new CamionetaSUV(
                                    placa.isEmpty() ? "TEMP" : placa,
                                    marca,
                                    modelo,
                                    (float) precio,
                                    traccion,
                                    (float) capacidad);
                        }

                        if (v != null) {
                            v.setEstado(estado);
                            vs.add(v);
                        }

                        marca = "";
                        modelo = 0;
                        precio = 0.0;
                        estado = "";
                        tipo = "";
                        combustible = "";
                        transmision = "";
                        traccion = "";
                        capacidad = 0.0;
                        placa = "";
                    }
                }
            }

            if (!marca.isEmpty()) {
                Vehiculo v = null;
                if (tipo.equals("SEDAN")) {
                    v = new CarroSedan(placa.isEmpty() ? "TEMP" : placa, marca, modelo, (float) precio, combustible,
                            transmision);
                } else if (tipo.equals("SUV")) {
                    v = new CamionetaSUV(placa.isEmpty() ? "TEMP" : placa, marca, modelo, (float) precio, traccion,
                            (float) capacidad);
                }

                if (v != null) {
                    v.setEstado(estado);
                    vs.add(v);
                }
            }

            System.out.println("¡Vehículos importados correctamente!");

        } catch (IOException e) {
            System.out.println("No se encontró el archivo " + nombreArchivo + ".txt");
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de números en " + nombreArchivo + ".txt");
        }

        return vs;
    }

    public LinkedList<ContratoRenting> leerContratos(String nombreArchivo, LinkedList<ContratoRenting> listaContratos,
            Stack<ContratoRenting> historial) {
        String ruta = nombreArchivo + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            String id = "", cedula = "", placa = "", fechaInicio = "", fechaFin = "";
            int dias = 0;
            double valor = 0.0;
            String estado = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID: "))
                    id = line.substring(4).trim();
                else if (line.startsWith("Cliente: "))
                    cedula = line.substring(9).trim();
                else if (line.startsWith("Vehiculo: "))
                    placa = line.substring(9).trim();
                else if (line.startsWith("Inicio: "))
                    fechaInicio = line.substring(9).trim();
                else if (line.startsWith("Fin: "))
                    fechaFin = line.substring(6).trim();
                else if (line.startsWith("Dias: "))
                    dias = Integer.parseInt(line.substring(6).trim());
                else if (line.startsWith("Valor: "))
                    valor = Double.parseDouble(line.substring(7).trim());
                else if (line.startsWith("Estado: "))
                    estado = line.substring(8).trim();

                if (line.isEmpty() || line.startsWith("---")) {
                    if (!id.isEmpty()) {
                        ContratoRenting contrato = new ContratoRenting(id, cedula, placa, fechaInicio, fechaFin, dias,
                                (float) valor);

                        if (estado.equals("Activo")) {
                            listaContratos.add(contrato);
                        } else {
                            historial.push(contrato);
                        }

                        id = cedula = placa = fechaInicio = fechaFin = "";
                        dias = 0;
                        valor = 0.0;
                        estado = "";
                    }
                }
            }

            if (!id.isEmpty()) {
                ContratoRenting contrato = new ContratoRenting(id, cedula, placa, fechaInicio, fechaFin, dias,
                        (float) valor);
                if (estado.equals("Activo"))
                    listaContratos.add(contrato);
                else
                    historial.push(contrato);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo " + nombreArchivo + ".txt: " + e.getMessage());
        }
        return listaContratos;
    }
}
