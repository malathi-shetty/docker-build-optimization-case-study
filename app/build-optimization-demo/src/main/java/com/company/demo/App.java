package com.company.demo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

private static final Logger LOGGER =
        Logger.getLogger(App.class.getName());

private static final int SERVER_PORT = 8080;

public static void main(String[] args) throws Exception {

    HttpServer server =
            HttpServer.create(
                    new InetSocketAddress(SERVER_PORT),
                    0
            );

    server.createContext("/", App::homeHandler);

    server.setExecutor(null);

    server.start();

    LOGGER.info("Application Started");
    LOGGER.info("Server running on port " + SERVER_PORT);
}

static void homeHandler(HttpExchange exchange) {

    try {

        byte[] responseBytes =
                AppService.getHtmlResponse().getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.sendResponseHeaders(
                200,
                responseBytes.length
        );

        try (OutputStream os =
                     exchange.getResponseBody()) {

            os.write(responseBytes);
        }

    } catch (Exception e) {

        LOGGER.log(
                Level.SEVERE,
                "Error processing HTTP request",
                e
        );
    }
}

}