package com.company.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HelloServletTest {

    @Test
    void doGetShouldReturnHelloMessage() throws Exception {

        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();

        verify(response).setContentType("text/plain");
        assertTrue(sw.toString().contains("Hello from Tomcat!"));
    }

    @Test
    void doGet_shouldHandleIOException_branch() throws Exception {

        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(response.getWriter()).thenThrow(new java.io.IOException("fail"));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
