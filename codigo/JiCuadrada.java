package codigo;

import java.util.Scanner;

public class JiCuadrada {

    public static double[] VALORES = {
            0.0619, 0.0824, 0.0856, 0.0994, 0.125, 0.1294, 0.1487, 0.1573, 0.1599,
            0.1627, 0.1658, 0.1676, 0.24, 0.2594, 0.2661, 0.2737, 0.274, 0.3109,
            0.3263, 0.3276, 0.3321, 0.3358, 0.3492, 0.3629, 0.3632, 0.3867, 0.3899,
            0.3975, 0.4103, 0.416, 0.44, 0.4522, 0.4802, 0.4875, 0.4916, 0.4927,
            0.5319, 0.5645, 0.5697, 0.6355, 0.6776, 0.6831, 0.6963, 0.7215, 0.7564,
            0.7604, 0.7652, 0.7821, 0.7901, 0.8017, 0.8049, 0.8086, 0.8097, 0.8135,
            0.8413, 0.8767, 0.8972, 0.9297, 0.9476, 0.9563, 0.9609, 0.9862, 0.9909,
            0.9928
    };
    public static double[][] JiCuadradaTabla = {
            { 10.8274, 9.1404, 7.8794, 6.6349, 5.0239, 3.8415, 2.7055, 2.0722, 1.6424, 1.3233, 1.0742, 0.8735, 0.7083,
                    0.5707, 0.4549 },
            { 13.8150, 11.9827, 10.5965, 9.2104, 7.3778, 5.9915, 4.6052, 3.7942, 3.2189, 2.7726, 2.4079, 2.0996, 1.8326,
                    1.5970, 1.3863 },
            { 16.2660, 14.3202, 13.2771, 11.8290, 9.3484, 7.8147, 6.2514, 5.3170, 4.4083, 3.9462, 3.6220, 3.3650,
                    3.1693, 3.0103, 2.8784 },
            { 18.4662, 16.4238, 14.8602, 12.7637, 11.1433, 9.4877, 7.7949, 6.7449, 5.9886, 5.3481, 4.8004, 4.3347,
                    3.9303, 3.5794, 3.2723 },
            { 20.5147, 18.3854, 16.7496, 15.0863, 13.2825, 11.0705, 9.2363, 8.1152, 7.2893, 6.6257, 6.0644, 5.5731,
                    5.1319, 4.7278, 4.3515 },
            { 22.4575, 20.2491, 18.5475, 16.8119, 14.4494, 12.5916, 10.6446, 9.4164, 8.5581, 7.8408, 7.2331, 6.6948,
                    6.2108, 5.7652, 5.3481 },
            { 24.3213, 22.2020, 20.2777, 18.0142, 16.0128, 14.0671, 12.0174, 10.7947, 9.0371, 8.3834, 7.8061, 7.2832,
                    6.7983, 6.3458, 5.9182 },
            { 26.1239, 23.7742, 21.9549, 20.0902, 17.5345, 15.5073, 13.3616, 12.0271, 11.0301, 10.2189, 9.5245, 8.9094,
                    8.3505, 7.8325, 7.3441 },
            { 27.8767, 25.4625, 23.5893, 21.6660, 19.0228, 16.9190, 14.6837, 13.2880, 12.2421, 11.3887, 10.6564,
                    10.0060, 9.4136, 8.8632, 8.3428 },
            { 29.5879, 27.1119, 25.1181, 23.2087, 20.4832, 18.3070, 15.9872, 14.3420, 13.4420, 12.5489, 11.7811,
                    11.0971, 10.4737, 9.9418, 9.4372 }
    };

    public static final double N = 64;

    public JiCuadrada() {

    }

    public static void iniciarCalculo() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el error:");
        double error = leer.nextDouble();
        System.out.println("Ingrese el numero de intervalos:");
        int numeroDeIntervalos = leer.nextInt();

        imprimirEncabezado();
        calcular(error, numeroDeIntervalos);
    }

    public static void imprimirEncabezado() {
        System.out.println("Prueba de Ji-Cuadrada");
        System.out.println("i\t\t" + "O\t\t" + "E\t\t" + "O-E\t\t" + "(O-E)2/E\t\t" + "\n" + //
                "");
    }

    public static void calcular(double error, double numeroDeIntervalos) {

        int observadosActual = 0;
        double intervaloInferiorActual = 0f;
        double intervaloSuperiorActual = 0f;
        double oMenosE = 0;
        double oMenosECuadradoSobreE = 0;
        double sumatoria = 0;
        double deltaIntervalo = 0;
        deltaIntervalo = 1 / numeroDeIntervalos;
        double E = 64 / numeroDeIntervalos;
        for (int i = 0; i < numeroDeIntervalos; i++) {
            intervaloSuperiorActual = intervaloInferiorActual + deltaIntervalo;
            observadosActual = calcularObservados(intervaloInferiorActual, intervaloSuperiorActual);

            oMenosE = observadosActual - E;
            oMenosECuadradoSobreE = ((observadosActual - E) * (observadosActual - E)) / E;
            System.out.printf("%.3f\t\t%d\t\t%.1f\t\t%.1f\t\t%.1f%n",
                    intervaloSuperiorActual, observadosActual, E, oMenosE, oMenosECuadradoSobreE);

            // System.out.println(intervaloSuperiorActual + "\t\t" + observadosActual+
            // "\t\t" + E+ "\t\t" + oMenosE);
            // System.out.printf("%.1f%n", intervaloSuperiorActual);
            sumatoria += oMenosECuadradoSobreE;
            intervaloInferiorActual += deltaIntervalo;
            intervaloSuperiorActual += deltaIntervalo;

        }

        concluir(sumatoria, error, numeroDeIntervalos);

    }

    public static int calcularObservados(double intervaloInferiorActual, double intervaloSuperiorActual) {
        int observados = 0;
        for (int i = 0; i < VALORES.length; i++) {
            if (VALORES[i] >= intervaloInferiorActual && VALORES[i] <= intervaloSuperiorActual) {
                observados++;
            }
        }
        return observados;

    }

    public static void concluir(double sumatoria, double error, double numeroDeIntervalos) {
        // double numeroDeIntervalos=0;
        int posicionError = 0;
        if (error == 0.001) {
            posicionError = 0;
        } else if (error == 0.0025) {
            posicionError = 1;
        } else if (error == 0.005) {
            posicionError = 2;
        } else if (error == 0.01) {
            posicionError = 3;
        } else if (error == 0.025) {
            posicionError = 4;
        } else if (error == 0.05) {
            posicionError = 5;
        } else if (error == 0.1) {
            posicionError = 6;
        } else if (error == 0.15) {
            posicionError = 7;
        } else if (error == 0.2) {
            posicionError = 8;
        } else if (error == 0.25) {
            posicionError = 9;
        } else if (error == 0.3) {
            posicionError = 10;
        } else if (error == 0.35) {
            posicionError = 11;
        } else if (error == 0.4) {
            posicionError = 12;
        } else if (error == 0.45) {
            posicionError = 13;
        } else if (error == 0.5) {
            posicionError = 14;
        }
        System.out.println("La sumatoria de (O-E)2/E es: " + sumatoria);

        int fila = (int) Math.round(sumatoria);
        double jicuadradaValor = JiCuadradaTabla[(int) (numeroDeIntervalos - 2)][posicionError];
        System.out.println("Ji CUadrada es: " + jicuadradaValor);
        if (sumatoria < jicuadradaValor) {
            System.out.println(
                    "Conclusion : NO hay pruebas suficientes para determinar que los numeros NO estan distribuidos uniformemente ");
        } else {
            System.out.println(
                    "Conclusion : NO hay pruebas suficientes para determinar que los numeros SI estan distribuidos uniformemente ");

        }
    }

}
