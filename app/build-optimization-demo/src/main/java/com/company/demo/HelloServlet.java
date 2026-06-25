package com.company.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private static final Logger LOGGER =
            Logger.getLogger(HelloServlet.class.getName());

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {

        response.setContentType("text/plain");

        try {
            PrintWriter writer = response.getWriter();
            writer.println("Hello from Tomcat!");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write response", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}