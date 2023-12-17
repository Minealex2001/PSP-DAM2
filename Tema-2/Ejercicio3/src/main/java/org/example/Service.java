package org.example;

import redis.clients.jedis.Jedis;

import java.util.Random;

import static org.example.Constantes.*;

public class Service implements Runnable{
    private static final String URL_TO_SHORTEN_KEY = "CHARLES:URLS_TO_SHORT";
    private static final String SHORTENED_URL_KEY = "CHARLES:SHORTED_URLS";
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final String PERSONAL_DOMAIN = "charles.com/";

    @Override
    public void run() {
        try (Jedis jedis = new Jedis(HOST, PORT)){
            while (true) {
                String url = jedis.lpop(URL_TO_SHORTEN_KEY);
                if (url != null) {
                    String shorted = shortUrl();
                    jedis.hset(SHORTENED_URL_KEY, shorted, url);
                    print("La URL " + url + " ha sido acortada a " + PERSONAL_DOMAIN + shorted);
                }
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void print(String message){
        System.out.println(message);
    }

    private static String shortUrl() {
        Random random = new Random();
        StringBuilder shortedUrl = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            shortedUrl.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return shortedUrl.toString();
    }
}
