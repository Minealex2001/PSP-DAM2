package org.example;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.example.Constantes.*;

public class Client {
    private static final String URL_TO_SHORTEN_KEY = "ALEJANDRO:URLS_TO_SHORTEN";
    private static final String SHORTENED_URL_KEY = "ALEJANDRO:SHORTENED_URLS";
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
                handleCommand(jedis, command);
            } while (!command.equals("exit"));

        } catch (IOException e) {
            System.out.println("Error reading input");
        } finally {
            thread.interrupt();
        }
    }

    private static void handleCommand(Jedis jedis, String command) {
        String[] commandSplitted = command.split(" ", 2);
        String action = commandSplitted[0];
        String argument = commandSplitted.length >= 2 ? commandSplitted[1] : null;

        switch (action) {
            case "shorten":
                if (argument != null) {
                    jedis.lpush(URL_TO_SHORTEN_KEY, argument);
                } else {
                    System.err.println("Invalid command: Missing URL");
                }
                break;
            case "url":
                if (argument != null) {
                    System.out.println(jedis.hget(SHORTENED_URL_KEY, argument));
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
    }
}