package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSS  FSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Distancias {
    public Distancias() {
    }


    public static void iniciarCalculo() {
        
        /* 
        Scanner leer = new Scanner(System.in);
        ArrayList<Float> numeros = new ArrayList<>();

        System.out.println("¿Desea ingresar los números desde la consola (C) o desde un archivo (T texto o E excel)?");
        String opcion = leer.next().trim().toUpperCase();
        leer.nextLine(); // Consumir la nueva línea después de leer la opción

        if (opcion.equals("C")){
            System.out.println("Ingresa números decimales (copia y pega todos, separados por saltos de línea):");
            System.out.println("Presiona Enter dos veces para finalizar:");

            while (leer.hasNextLine()) {
                String linea = leer.nextLine().trim();
                if (linea.isEmpty()) {
                    break; // Finalizar entrada con línea vacía
                }
                try {
                    float numero = Float.parseFloat(linea.replace(',', '.'));
                    numeros.add(numero);
                } catch (NumberFormatException e) {
                    System.out.println("¡Error! '" + linea + "' no es un número válido.");
                }
            }
        }  else if (opcion.equals("T")) { 
            System.out.println("Ingrese la dirección del archivo:");
            String direccionArchivo = leer.nextLine().trim();
            File archivo = new File(direccionArchivo);

            try (Scanner scannerArchivo = new Scanner(new FileReader(archivo))) {
                while (scannerArchivo.hasNextLine()) {
                    String linea = scannerArchivo.nextLine().trim();
                    if (!linea.isEmpty()) {
                        try {
                            float numero = Float.parseFloat(linea.replace(',', '.'));
                            numeros.add(numero);
                        } catch (NumberFormatException e) {
                            System.out.println("¡Error en el archivo! '" + linea + "' no es un número válido.");
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("¡Error! No se encontró el archivo en la dirección especificada.");
                return; // Salir del programa si el archivo no se encuentra
            }
    }	 else if (opcion.equals("E")) {
            System.out.println("Ingrese la dirección del archivo Excel (.xlsx):");
            String direccionArchivo = leer.nextLine();
            File archivo = new File(direccionArchivo);
            try (FileInputStream fis = new FileInputStream(archivo);
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                XSSFSheet sheet = workbook.getSheetAt(0); // Leer la primera hoja

                // Asumimos que los datos de "Ui" están en la primera columna (índice 0)
                for (Row row : sheet) {
                    Cell cell = row.getCell(0); // Obtener la primera celda de la fila
                    if (cell != null) {
                        try {
                            // Intentar obtener un valor numérico
                            double numeroDouble = cell.getNumericCellValue();
                            numeros.add((float) numeroDouble);
                        } catch (IllegalStateException e) {
                            // Si no es un número, intentar obtenerlo como cadena y parsearlo
                            try {
                                String numeroStr = cell.getStringCellValue().replace(',', '.');
                                numeros.add(Float.parseFloat(numeroStr));
                            } catch (IllegalStateException | NumberFormatException ex) {
                                System.out.println("¡Error en el archivo Excel! No se pudo leer el valor en la fila " + row.getRowNum() + ", columna 0.");
                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("¡Error! No se encontró el archivo Excel en la dirección especificada.");
                return;
            } catch (IOException e) {
                System.out.println("¡Error al leer el archivo Excel: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Opción inválida. El programa finalizará.");
            return;
        }
*/
        // Lectura del archivo y almacenamiento de los valores en una lista me costo
        // tiempo pero le entendi
        System.out.println("Que archivo desea leer?");
        System.out.println("1) datos.csv");
        System.out.println("2) Pruebas2.csv");
        System.out.println("3) pruebas3.csv");
        System.out.println("4) Salir");

        Scanner leer = new Scanner(System.in);
        System.out.println("Elija una opcion:");
        int numArchivo = leer.nextInt();
        String archivo = null;
        switch (numArchivo) {
            case 1:
                archivo = "datos.csv";
                break;
            case 2:
                archivo = "Pruebas2.csv";
                break;
            case 3:
                archivo = "pruebas3.csv";
                break;
            case 4:
                System.out.println("Saliendo...");
                return; // Exit the method if the user chooses to exit
            default:
                System.out.println("Opción no válida. Saliendo...");
                return; // Exit the method if the user enters an invalid option
        }
String linea;
        String delimitador = ",";
        ArrayList<Double> listaValores = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(archivo));
            while ((linea = br.readLine()) != null) {
                String[] tokens = linea.split(delimitador);
                for (String token : tokens) {
                    token = token.trim();
                    if (!token.isEmpty()) {
                        try {
                            double valor = Double.parseDouble(token);
                            listaValores.add(valor);
                        } catch (NumberFormatException e) {
                            // Ignorar valores no numéricos ya que pues hay uno ahi que no es numerico
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Leer parámetros
        System.out.print("\nIngresa a (límite inferior): ");
        float a = Float.parseFloat(leer.next());

        System.out.print("Ingresa theta (probabilidad de éxito): ");
        float theta = Float.parseFloat(leer.next());
        System.out.print("Ingresa n (numero de espacios): ");
        int n = leer.nextInt();

        float B = a + theta;
        ArrayList<Integer> huecos = new ArrayList<>(Collections.nCopies(n + 1, 0)); // Inicializar con ceros

        // Contar huecos
        int contadorActual = 0;
        int c = 0;
        int c0 = 0;
        float eoi = 0;
        for (Double num : listaValores) {
            c++;
            System.out.print(c + "\t" + num + "\t");
            if (num < a || num > B) {
                System.out.println("0");
                contadorActual++;
                if (c0 > 0) {
                    huecos.set(0, huecos.get(0) + 1);
                    c0 = 0;
                    eoi++;
                }
            } else {
                c0++;
                System.out.println("1\t" + contadorActual);
                if (contadorActual > 0) {
                    if (contadorActual >= n) {
                        huecos.set(n, huecos.get(n) + 1);
                    } else {
                        huecos.set(contadorActual, huecos.get(contadorActual) + 1);
                    }
                    eoi++;
                    contadorActual = 0;
                }
            }

        }
        if (c0 > 0) {
            huecos.set(0, huecos.get(0) + 1);
            c0 = 0;
            eoi++;
        }
        System.out.println(eoi);
        // Calcular estadístico chi-cuadrado
        System.out.println("\ni\tPi\t\tOi\tEi\tEi-Oi\t(Ei-Oi)^2/Ei");
        float chiCuadrado = 0;
        float cpi = 0;
        float Pi;
        for (int i = 0; i < huecos.size(); i++) {
            if (i == huecos.size() - 1) {
                Pi = 1 - cpi;
            } else {
                Pi = (float) (Math.pow(1 - theta, i) * theta);
            }
            cpi += Pi;
            float Oi = huecos.get(i);
            float Ei = eoi * Pi;

            if (Ei == 0) continue; // Evitar división por cero

            float termino = (float) Math.pow(Ei - Oi, 2) / Ei;
            chiCuadrado += termino;

            System.out.printf("%d\t%.6f\t%d\t%.2f\t%.2f\t%.4f%n",
                    i, Pi, huecos.get(i), Ei, Ei - Oi, termino);
        }
        System.out.printf("\nEstadístico chi-cuadrado calculado: %.4f%n", chiCuadrado);
        System.out.println("Cual es el Chi^2 de comparacion?");

        float x2 = Float.parseFloat(leer.next());
        if (chiCuadrado < x2) {
            System.out.println("Se acepta H0");
        } else System.out.println("No se acepta H0");
        leer.close();
    }


       
}