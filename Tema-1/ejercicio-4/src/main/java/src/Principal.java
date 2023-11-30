package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) throws InterruptedException, IOException {

        List<Corredor> listaCorredores = new ArrayList<>();;
        Principal.buscarCorredores(listaCorredores);
        Carrera carrera = new Carrera(listaCorredores);
        carrera.start();
        carrera.end();
    }

    public static void buscarCorredores(List<Corredor> runnerList) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.print("Numero de Participantes: ");
        int corredores = Integer.parseInt(br.readLine());

        while (runnerList.size() < corredores) {
            String runnerSymbol;
            int velocidadCorredores, velocidadTurbo, probablidadDeCaer;

            System.out.printf("Simbolo del participante " + (runnerList.size() + 1) + ": ");
            runnerSymbol = br.readLine();
            System.out.printf("Atributos del corredor " + (runnerList.size() + 1) + " (Max 10 puntos)\r\nCorredor " + (runnerList.size() + 1) + " Velocidad base (1-5): ");
            velocidadCorredores = Integer.parseInt(br.readLine());
            System.out.printf("Corredor " + (runnerList.size() + 1) + "Probabilidad de turbo (1-5): ");
            velocidadTurbo = Integer.parseInt(br.readLine());
            System.out.printf("Corredor " + (runnerList.size() + 1) + " probabilidad de caer (1-5): ");
            probablidadDeCaer = Integer.parseInt(br.readLine());

            try {
                runnerList.add(new Corredor(runnerSymbol, velocidadCorredores, velocidadTurbo, probablidadDeCaer));
                System.out.printf("Corredor %d Creado!!!\r\n", runnerList.size());
            } catch (Exception e) {
                System.out.printf("Corredor no añadido", runnerList.size());
            }

        }
    }
}
