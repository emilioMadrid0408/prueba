package codigo;

import java.util.Scanner;

public class AppPrincipal {

    public static void main(String[] args) {
        System.out.println("Que prueba desea realizar?");
        System.out.println("1)  Xi Cuadrada");
        System.out.println("2)  Kolmogorov");
        System.out.println("3)  ");
        System.out.println("1)  ");

        Scanner leer = new Scanner(System.in);
        int numPrueba = leer.nextInt();
        switch (numPrueba) {
            case 1:
                JiCuadrada jiCuadrada = new JiCuadrada();
                jiCuadrada.iniciarCalculo();

                break;
            case 2:
                Kolmogorov kolmogorov = new Kolmogorov();
                kolmogorov.iniciarCalculo();
                break;
            default:
                break;
        }

    }

}