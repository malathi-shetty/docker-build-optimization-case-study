package com.company.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppServiceTest {

    @Test
    void shouldReturnProjectName() {

        assertEquals(
                "Docker Build Optimization Project",
                AppService.getProjectName()
        );
    }

    @Test
    void shouldReturnHtmlResponse() {

        String html = AppService.getHtmlResponse();

        assertTrue(html.contains("<html>"));
        assertTrue(html.contains("</html>"));

        assertTrue(
                html.contains("Docker Build Optimization Project")
        );

        assertTrue(
                html.contains("Application Started Successfully")
        );

        assertTrue(
                html.contains("Backend Response Received")
        );
    }
}