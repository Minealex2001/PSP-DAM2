package org.example.ejercicio12;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    /**
     * Método para descargar un archivo de una URL.
     * @param url la URL del archivo a descargar
     * @param fileName el nombre del archivo donde se guardará la descarga
     * @return un CompletableFuture que se completa cuando la descarga ha terminado
     */
    public static CompletableFuture<Void> downloadFile(String url, String fileName) {
        return CompletableFuture.runAsync(() -> {
            try (InputStream in = new URL(url).openStream()) {
                Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    /**
     * Método para comprimir todos los archivos en un archivo .zip.
     * @param fileNames una lista de nombres de archivos a comprimir
     * @param zipFileName el nombre del archivo .zip donde se guardarán los archivos comprimidos
     * @return un CompletableFuture que se completa cuando la compresión ha terminado
     */
    public static CompletableFuture<Void> compressFiles(List<String> fileNames, String zipFileName) {
        return CompletableFuture.runAsync(() -> {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName))) {
                for (String fileName : fileNames) {
                    ZipEntry zipEntry = new ZipEntry(fileName + ".html");
                    zos.putNextEntry(zipEntry);
                    Files.copy(Paths.get(fileName), zos);
                    zos.closeEntry();
                    Files.deleteIfExists(Paths.get(fileName));
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
    
}
