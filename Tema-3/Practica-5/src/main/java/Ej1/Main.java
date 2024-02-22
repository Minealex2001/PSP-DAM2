package Ej1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {
    public static void main(String[] args) throws IOException {
        Downloader downloader = new Downloader();
        ObservableList<String> urls = FXCollections.observableArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        urls.addListener(downloader);
        System.out.println("Si quieres terminar de ejecutar el programa escribe 'exit'");
        String url = "";
        do{
            System.out.println("Introduce la url del archivo que quieres descargar : ");
            url = br.readLine();
            if(!url.equalsIgnoreCase("exit")){
                urls.add(url);
            }
        }while(!url.equalsIgnoreCase("exit"));
    }
}
