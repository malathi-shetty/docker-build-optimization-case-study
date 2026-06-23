package com.company.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void shouldContainHtmlResponse() {

        String html = AppService.HTML_RESPONSE;

        assertTrue(
                html.contains(
                        "Docker Build Optimization Project"
                )
        );

        assertTrue(
                html.contains(
                        "Application Started Successfully"
                )
        );

        assertTrue(
                html.contains(
                        "Backend Response Received"
                )
        );
    }
}