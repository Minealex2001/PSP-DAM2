package src;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiloInterfazCarrera extends Thread{
    private Carrera carrera;
    private HiloListaSegura resultado;
    private int corredores;

    @Override
    public void run() {
        while (resultado.size() < corredores) {
            try {
                System.out.printf(carrera.toString());

                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
