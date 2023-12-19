package org.example;

import java.io.IOException;
import java.util.Properties;

public class Constantes {
    public static final String HOST;
    public static final int PORT;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Constantes.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
        HOST = properties.getProperty("host");
        PORT = Integer.parseInt(properties.getProperty("port"));
    }
}