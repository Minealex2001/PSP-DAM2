package org.alejandro.ejercicio1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HiloPersonalizado extends Thread {
    NumeroPersonalizado numeroPersonalizado;



    @Override
    public void run(){
        for (int i = 0; i < 500; i++){
            numeroPersonalizado.setNumero(numeroPersonalizado.getNumero() + 1);
        }
    }
}
