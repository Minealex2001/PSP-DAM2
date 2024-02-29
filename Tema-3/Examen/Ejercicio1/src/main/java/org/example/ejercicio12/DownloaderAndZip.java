package org.example.ejercicio12;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class DownloaderAndZip implements ListChangeListener<String> {

    @Override
    public void onChanged(Change<? extends String> c) {
        System.out.println(c.getList().get(c.getList().size()-1).split(",")[0] + " encolado como " + c.getList().get(c.getList().size()-1).split(",")[1]);
    }
    
}
