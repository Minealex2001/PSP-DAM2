package ejercicio1;

import ejercicio1.hilos.Gardener;
import ejercicio1.hilos.Town;

public class Main {
    public static void main(String[] args) {
        final int GARDENERS = 3;
        final int PARKS = 10;
        final long DURATION = 30000;

        Town town = new Town(PARKS);
        Gardener[] gardeners = new Gardener[GARDENERS];
        for(int i = 0; i < GARDENERS; i++) {
            gardeners[i] = new Gardener(town);
            gardeners[i].start();
        }

        for(int i = 0; i < GARDENERS; i++) {
            try {
                gardeners[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
