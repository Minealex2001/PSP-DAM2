package org.example;

public class Utilidades {

    public void rellenarArray(int[] numerosPrimos) {
        for (int i = 0; i < numerosPrimos.length; i++) {
            numerosPrimos[i] = (int) (Math.random() * (Integer.MAX_VALUE - (Integer.MAX_VALUE * 0.9) + 1));
        }
    }

    public boolean naive(int numero) {
        boolean esPrimo = true;
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                esPrimo = false;
                break;
            }
        }
        return esPrimo;
    }
}


