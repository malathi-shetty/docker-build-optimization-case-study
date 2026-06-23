package com.company.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", App::homeHandler);

        server.setExecutor(null);
        server.start();

        System.out.println("Application Started");
        System.out.println("Server running on port 8080");
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

            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}