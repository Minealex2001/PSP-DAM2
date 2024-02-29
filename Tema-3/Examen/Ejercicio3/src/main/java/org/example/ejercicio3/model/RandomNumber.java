package org.example.ejercicio3.model;

import lombok.Data;

@Data
public class RandomNumber {
    // Variable para almacenar el número aleatorio
    private int random;

    // Constructor vacío
    public RandomNumber() {}

    // Constructor con un parámetro para inicializar el número aleatorio
    public RandomNumber(int random) {
        this.random = random;
    }

    // Método getter para obtener el número aleatorio
    public int getRandom() {
        return random;
    }

    // Método setter para establecer el número aleatorio
    public void setRandom(int random) {
        this.random = random;
    }
}