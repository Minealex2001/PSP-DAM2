package src;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class HiloListaSegura {
    private List<String> lista;

    HiloListaSegura() {
        lista = new ArrayList<String>();
    }

    synchronized void add(String s) {
        lista.add(s);
    }

    String get(int index) {
        return lista.get(index);
    }

    int size() {
        return lista.size();
    }
}
