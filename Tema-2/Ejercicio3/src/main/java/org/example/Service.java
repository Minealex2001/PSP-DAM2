package org.example;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class Service implements Runnable {
    private static final String URL_TO_SHORTEN_KEY = "ALEJANDRO:URLS_TO_SHORT";
    private static final String SHORTENED_URL_KEY = "ALEJANDRO:SHORTED_URLS";
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String PERSONAL_DOMAIN = "alejandro.com/";

    @Override
    public void run() {
        try (Jedis jedis = new Jedis(Constantes.HOST, Constantes.PORT)) {
            String url;
            while ((url = jedis.lpop(URL_TO_SHORTEN_KEY)) != null) {
                String shorted = generateShortUrl();
                jedis.hset(SHORTENED_URL_KEY, shorted, url);
                System.out.println("La URL " + url + " ha sido acortada a " + PERSONAL_DOMAIN + shorted);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String generateShortUrl() {
        Random random = new Random();
        StringBuilder shortedUrl = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            shortedUrl.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return shortedUrl.toString();
    }
}