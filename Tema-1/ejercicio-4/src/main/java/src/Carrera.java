package src;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Carrera {
    private List<Corredor> corredores;
    private List<HiloCorredor> hiloCorredores;
    private List<HiloCorredorCaida> hiloCorredorCaidas;
    private HiloListaSegura listaSegura;
    private HiloInterfazCarrera interfazCarrera;
    private static final int FINAL_CARRERA = 200;

    public Carrera(List<Corredor> corredores) {
        this.corredores = corredores;
        this.listaSegura = new HiloListaSegura();
        this.hiloCorredores = new ArrayList<>();
        this.hiloCorredorCaidas = new ArrayList<>();
        for (Corredor runner: corredores) {
            this.hiloCorredores.add(new HiloCorredor(runner, Carrera.FINAL_CARRERA, listaSegura));
            this.hiloCorredorCaidas.add(new HiloCorredorCaida(runner, Carrera.FINAL_CARRERA));
        }
        this.interfazCarrera = new HiloInterfazCarrera(this, listaSegura, corredores.size());
    }

    public void start() {
        for (HiloCorredor runThread: hiloCorredores) {
            runThread.start();
        }
        for (HiloCorredorCaida fallThread: hiloCorredorCaidas) {
            fallThread.start();
        }
        interfazCarrera.start();
    }

    public void end() throws InterruptedException {
        for (HiloCorredor runThread: hiloCorredores) {
            runThread.join();
        }
        for (HiloCorredorCaida fallThread: hiloCorredorCaidas) {
            fallThread.join();
        }
        interfazCarrera.join();

        System.out.printf("Classification:\r\n");
        for (int i = 0; i < listaSegura.size(); i++) {
            System.out.printf("%d - %s\r\n", i+1, listaSegura.get(i));
        }
    }

    @Override
    public String toString() {
        String ui = "\r\n".repeat(10) + "-".repeat(FINAL_CARRERA+2) + "\r\n";
        for (Corredor runner: corredores) {
            ui += "|" + " ".repeat(runner.getPosicion()) + runner.getSimbolo() + " ".repeat(Math.max(FINAL_CARRERA - runner.getPosicion()-1, 0)) + "!\r\n";
        }
        ui += "-".repeat(FINAL_CARRERA+2) + "\r\n";
        return ui;
    }
}
