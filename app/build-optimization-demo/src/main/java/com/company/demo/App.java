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

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", App::homeHandler);

        server.setExecutor(null);

        server.start();

        LOGGER.info("Application Started");
        LOGGER.info("Server running on port 8080");
    }

    private static void homeHandler(HttpExchange exchange) {

        try {

            String response = """
                    <html>
                    <head>
                        <title>Docker Build Optimization Demo</title>
                    </head>
                    <body>
                        <h1>Docker Build Optimization Project</h1>
                        <p>Application Started Successfully</p>
                        <p>Backend Response Received</p>
                    </body>
                    </html>
                    """;

            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(200, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
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