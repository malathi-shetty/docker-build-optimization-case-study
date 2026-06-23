package com.company.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    void shouldReturnApplicationMessage() {

        assertEquals(
                "Docker Build Optimization Project",
                App.getApplicationMessage()
        );
    }

    @Test
    void shouldBuildHtmlResponse() {

        String html = App.buildHtmlResponse();

        assertTrue(
                html.contains("Docker Build Optimization Project")
        );

        assertTrue(
                html.contains("Application Started Successfully")
        );

        assertTrue(
                html.contains("Backend Response Received")
        );

        assertTrue(
                html.contains("<html>")
        );

        assertTrue(
                html.contains("</html>")
        );
    }
}