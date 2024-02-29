package org.example.ejercicio3.controller;
import org.example.ejercicio3.model.RandomNumber;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/random")
public class RandomNumberController {

    // Instancia de la clase Random para generar números aleatorios
    private final Random random = new Random();

    // Endpoint para obtener un array de 100 números aleatorios
    @GetMapping("/numbers")
    public int[] getRandomNumbers() {
        return IntStream.range(0, 100).map(i -> random.nextInt()).toArray();
    }

    // Endpoint para obtener un número aleatorio con 'd' dígitos
    @GetMapping("/number/{d}")
    public RandomNumber getRandomNumber(@PathVariable int d) {
        int randomNumber = random.nextInt((int) Math.pow(10, d));
        return new RandomNumber(randomNumber);
    }

    // Endpoint para actualizar un número aleatorio y devolver otro con el mismo número de dígitos
    @PutMapping("/number")
    public ResponseEntity<RandomNumber> updateRandomNumber(@RequestBody RandomNumber randomNumber) {
        int digits = (int) Math.log10(randomNumber.getRandom()) + 1;
        int newRandomNumber = random.nextInt((int) Math.pow(10, digits));
        return ResponseEntity.ok(new RandomNumber(newRandomNumber));
    }
}