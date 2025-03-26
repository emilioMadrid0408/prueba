package codigo;


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
    public static final double N = 64;

    public JiCuadrada() {
        

    }
    public static void iniciarCalculo(){
        imprimirEncabezado();
        calcular();
    }

    public static void imprimirEncabezado() {
        System.out.println("Prueba de Ji-Cuadrada");
        System.out.println("i\t\t" + "O\t\t" + "E\t\t" + "O-E\t\t" + "(O-E)2/E\t\t" + "\n" + //
                "");
    }

    public static void calcular() {
      
        int observadosActual = 0;
        float intervaloInferiorActual = 0f;
        float intervaloSuperiorActual = 0.1f;
        double E = N/10;
        double oMenosE=0;
        double oMenosECuadradoSobreE=0;
        for (int i = 0; i < 10; i++) {
            observadosActual = calcularObservados(intervaloInferiorActual, intervaloSuperiorActual);
            
            oMenosE = observadosActual - E;
            oMenosECuadradoSobreE = ((observadosActual-E)*((observadosActual-E)))/E;
            System.out.printf("%.1f\t\t%d\t\t%.1f\t\t%.1f%n", intervaloSuperiorActual, observadosActual, E, oMenosE,oMenosECuadradoSobreE);
            //System.out.println(intervaloSuperiorActual + "\t\t" + observadosActual+ "\t\t" + E+ "\t\t" + oMenosE);
            // System.out.printf("%.1f%n", intervaloSuperiorActual);
           
            intervaloInferiorActual += 0.1f;
            intervaloSuperiorActual += 0.1f;
            

        }
        
    }

    public static int calcularObservados(float intervaloInferiorActual, float intervaloSuperiorActual) {
        int observados = 0;
        for (int i = 0; i < VALORES.length; i++) {
            if (VALORES[i] >= intervaloInferiorActual && VALORES[i] <= intervaloSuperiorActual) {
                observados++;
            }
        }
        return observados;

    }

}