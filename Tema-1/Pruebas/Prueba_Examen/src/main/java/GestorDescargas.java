import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {
    public static void main(String[] argumentos) throws IOException {
        GestorDescargas gd = new GestorDescargas();
        gd.descargarArchivo("https://www.boe.es/legislacion/documentos/ConstitucionCASTELLANO.pdf", "resultado.txt");
        BufferedReader br = new BufferedReader(new FileReader("resultado.txt"));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
    }

    public void descargarArchivo(String url_descargar, String nombreArchivo) {
        System.out.println("Descargando " + url_descargar);
        try {
            URL laUrl = new URL(url_descargar);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(laUrl.openStream()));
            FileWriter escritorFichero = new FileWriter(nombreArchivo);
            String linea;
            while ((linea = bReader.readLine()) != null) {
                escritorFichero.write(linea);
            }
            escritorFichero.close();
            bReader.close();
        } catch (MalformedURLException e) {
            System.out.println("URL mal escrita!");
        } catch (IOException e) {
            System.out.println("Fallo en la lectura del fichero");
        }
    }
}