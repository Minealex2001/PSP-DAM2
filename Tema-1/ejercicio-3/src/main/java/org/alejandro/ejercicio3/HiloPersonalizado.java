package org.alejandro.ejercicio3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class HiloPersonalizado extends Thread {
    AtomicInteger numeroPersonalizado;



    @Override
    public void run(){
        for (int i = 0; i < 500; i++){
            this.numeroPersonalizado.incrementAndGet();
        }
    }
}
