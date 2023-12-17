package org.example;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.example.Constantes.*;

public class Client {
    private static final String URL_TO_SHORTEN_KEY = "CHARLES:URLS_TO_SHORTEN";
    private static final String SHORTENED_URL_KEY = "CHARLES:SHORTENED_URLS";
    private static final String HELP_MESSAGE = """
                                Commands:
                                shorten <URL> - Shorten the given URL
                                url <shortened URL> - Return the original URL
                                help - Shows this message
                                exit - Exit the program""";

    public static void main(String[] args) {
        Thread thread = new Thread(new Service());

        try (Jedis jedis = new Jedis(HOST, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(HELP_MESSAGE);
            thread.start();

            String command;
            do {
                System.out.print("#: ");
                command = reader.readLine();
                String[] commandSplitted = command.split(" ", 2);

                switch (commandSplitted[0]) {
                    case "shorten":
                        if (commandSplitted.length >= 2) {
                            String url = commandSplitted[1];
                            jedis.lpush(URL_TO_SHORTEN_KEY, url);
                        } else {
                            System.err.println("Invalid command: Missing URL");
                        }
                        break;
                    case "url":
                        if (commandSplitted.length >= 2) {
                            String shortedUrl = commandSplitted[1];
                            System.out.println(jedis.hget(SHORTENED_URL_KEY, shortedUrl));
                        } else {
                            System.err.println("Invalid command: Missing shortened URL");
                        }
                        break;
                    case "help":
                        System.out.println("\n" + HELP_MESSAGE);
                        break;
                    case "exit":
                        break;
                    default:
                        System.err.println("Invalid command: Command not found");
                        break;
                }
            } while (!command.equals("exit"));

        } catch (IOException e) {
            System.out.println("Error reading input");
        } finally {
            thread.interrupt();
        }
    }
}