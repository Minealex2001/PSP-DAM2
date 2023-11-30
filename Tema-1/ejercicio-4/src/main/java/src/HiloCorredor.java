package src;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class HiloCorredor extends Thread{
    private Corredor corredor;
    private int finalCarrera;
    private HiloListaSegura listaSegura;


    @Override
    public void run() {
        while (corredor.avanzarHastaElFinal(finalCarrera)) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        listaSegura.add(corredor.getSimbolo());
    }

}
