package codigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kolmogorov {



    public Kolmogorov(){

    }

    public static void iniciarCalculo() {





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

        // Convertir la lista a un array de primitivos double para pues poner ahi
        // decimales aunque son muchas capaz un float
        double[] arrayValores = new double[listaValores.size()];
        for (int i = 0; i < listaValores.size(); i++) {
            arrayValores[i] = listaValores.get(i);
        }

        // Ordenar el array de menor a mayor usando bubble sort lo cheque en google
        // porque no me acordaba del metodo
        bubbleSortAscending(arrayValores);

        // Calcular totalDivision y restaTotal ya que mi logica es muy simple
        double[] totalDivision = new double[arrayValores.length];
        double[] restaTotal = new double[arrayValores.length];
        for (int i = 0; i < arrayValores.length; i++) {
            int contador = i + 1; // Posición empezando en 1
            double division = (double) contador / arrayValores.length;
            totalDivision[i] = division;
            restaTotal[i] = Math.abs(arrayValores[i] - division);
        }

        // Buscar el número mayor en restaTotal y su posición para poder imprimir esa
        // informacion
        double numMayor = restaTotal[0];
        int posMayor = 0;
        for (int i = 1; i < restaTotal.length; i++) {
            if (restaTotal[i] > numMayor) {
                numMayor = restaTotal[i];
                posMayor = i;
            }
        }

        // Crear las variables NumeroUi y NumeroIn basadas en la posición posMayor para
        // solo imprimirlas y ya
        double NumeroUi = arrayValores[posMayor];
        double NumeroIn = totalDivision[posMayor];

        // Imprimir una tabla con la posición de cada array, arrayValores, totalDivision
        // y restaTotal
        System.out.println("i\tUi\t          i/N\t                   Di");
        for (int i = 0; i < arrayValores.length; i++) {
            System.out.println((i + 1) + "\t" + arrayValores[i] + "\t\t"
                    + totalDivision[i] + "\t\t" + restaTotal[i]);
        }
        double Dtotal = 1.36 / Math.sqrt(arrayValores.length);
        System.out.println("-----------------------------------VALORES-------------------------");
        System.out.println();
        System.out.println(
                "i=" + posMayor + "\t Ui=" + NumeroUi + "\tI/N=" + NumeroIn + "\tDi=" + numMayor + "\tDna=" + Dtotal);
        System.out.println();
        System.out.println();
        if (numMayor < Dtotal) {
            System.out.println("---------SE ACEPTA H0----------");
        } else {
            System.out.println("---------NO SE ACEPTA H0--------");
        }
    }

    // Método bubble sort para ordenar de menor a mayor el cual lo vi en youtube
    public static void bubbleSortAscending(double[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
        }
    }

}
