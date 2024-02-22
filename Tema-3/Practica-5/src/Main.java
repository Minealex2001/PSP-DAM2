import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        Dowloader dowloader = new Dowloader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ObservableList<String> urls = FXCollections.observableArrayList();

        //se añade el objeto dowloader a la lista de escuchadores de la lista de urls
        urls.addListener(dowloader);
        System.out.println("Si quieres terminar de ejecutar el programa escribe 'exit'");
        String url = "";
        do{
            System.out.println("Introduce la url del archivo que quieres descargar : ");
            url = sc.nextLine();

            if(!url.equalsIgnoreCase("exit")){
                urls.add(url);
            }
        }while(!url.equalsIgnoreCase("exit"));
    }
}