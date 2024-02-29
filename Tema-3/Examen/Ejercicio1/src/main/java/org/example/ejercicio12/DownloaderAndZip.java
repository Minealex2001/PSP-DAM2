package org.example.ejercicio12;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Clase que implementa un escuchador de cambios en una lista.
 * Imprime un mensaje cada vez que se añade un elemento a la lista.
 */
public class DownloaderAndZip implements ListChangeListener<String> {

    /**
     * Método que se ejecuta cuando se produce un cambio en la lista.
     * @param c el cambio que ha ocurrido
     */
    @Override
    public void onChanged(Change<? extends String> c) {
        System.out.println(c.getList().get(c.getList().size()-1).split(",")[0] + " encolado como " + c.getList().get(c.getList().size()-1).split(",")[1]);
    }
    
}
