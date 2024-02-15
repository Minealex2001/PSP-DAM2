package src;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Corredor {
    private final String simbolo;
    private final int velocidadBase;
    private final int probabilidadTurbo;
    private final int probabilidadCaida;
    private int posicion;

    public Corredor(String simbolo, int velocidadBase, int probabilidadTurbo, int probabilidadCaida) throws Exception {
        this.simbolo = simbolo;
        this.velocidadBase = velocidadBase;
        this.probabilidadTurbo = probabilidadTurbo;
        this.probabilidadCaida = probabilidadCaida;
        this.posicion = 0;
        if (!Corredor.atributosValidos(velocidadBase, probabilidadTurbo, probabilidadCaida)) {
            throw new Exception("Invalid Runner attributes.");
        }
    }

    public synchronized boolean avanzarHastaElFinal(int raceEnd) {
        if (posicion < raceEnd) {
            if (Math.random() < probabilidadTurbo / 10.) {
                posicion += velocidadBase;
            }
            posicion += velocidadBase;
        }
        return posicion < raceEnd;
    }

    public synchronized boolean caida(int raceEnd) {
        if (posicion < raceEnd) {
            if (Math.random() < probabilidadCaida / 10.) {
                posicion -= velocidadBase;
            }
        }
        return posicion < raceEnd;
    }

    static boolean atributosValidos(int speed, int turbo, int fall) {
        int sum = speed + turbo + fall;
        return (speed <= 5 &&
                turbo <= 5 &&
                fall <= 5 &&
                speed >= 1 &&
                turbo >= 1 &&
                fall >= 1 &&
                sum <= 10);
    }

    @Override
    public String toString() {
        return this.getSimbolo();
    }
}
