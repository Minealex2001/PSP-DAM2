package org.example.practica2.ejercicio2;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Ejercicio2 {
    public static void main(String[] args) throws IOException {

        System.Logger logger = System.getLogger("Ejercicio2");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introduce la ruta de la carpeta/fichero que quieres comprimir: ");

        String ruta = reader.readLine();

        System.out.println("Introduce la ruta donde quieres guardar el fichero comprimido: ");

        String rutaComprimido = reader.readLine();

        System.out.println("Introduce el nombre del fichero comprimido: ");

        String nombreComprimido = reader.readLine() + ".zip";

        // Concatena la ruta de la carpeta con el nombre del archivo zip
        FileOutputStream fos = new FileOutputStream(rutaComprimido + File.separator + nombreComprimido);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(ruta);

        if (fileToZip.isFile()) {
            logger.log(System.Logger.Level.INFO, "Es un fichero");
            CompletableFuture.runAsync(() -> {
                try {
                    compressFile(fileToZip, zipOut, logger, nombreComprimido);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).join(); // Espera a que se complete la tarea
        } else if (fileToZip.isDirectory()) {
            logger.log(System.Logger.Level.INFO, "Es un directorio");
            CompletableFuture.runAsync(() -> {
                try {
                    compressDirectory(fileToZip, zipOut, logger, nombreComprimido);
                } catch (IOException e) {
                    logger.log(System.Logger.Level.ERROR, "Error al comprimir el directorio: " + fileToZip.getName(), e);
                }
            }).join();
        }
        zipOut.close();
        fos.close();
    }


    private static void compressDirectory(File fileToZip, ZipOutputStream zipOut, System.Logger logger, String parentDir) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }

        if (!fileToZip.getName().endsWith("/")) {
            zipOut.putNextEntry(new ZipEntry(parentDir + fileToZip.getPath() + "/"));
            zipOut.closeEntry();
        }

        File[] children = fileToZip.listFiles();
        for (File childFile : children) {
            if (childFile.isDirectory()) {
                compressDirectory(childFile, zipOut, logger, parentDir + fileToZip.getPath() + "/");
                continue;
            }
            compressFile(childFile, zipOut, logger, parentDir);
        }
    }

    private static void compressFile(File fileToZip, ZipOutputStream zipOut, System.Logger logger, String parentDir) throws IOException {
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(parentDir + fileToZip.getPath());
        zipOut.putNextEntry(zipEntry);
        logger.log(System.Logger.Level.INFO, "Comprimiendo fichero: " + fileToZip.getPath());

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        logger.log(System.Logger.Level.INFO, "Fichero comprimido: " + fileToZip.getPath());
        fis.close();
        logger.log(System.Logger.Level.WARNING, "Proceso finalizado");
    }
}