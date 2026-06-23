package com.company.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    void shouldContainProjectTitle() {

        String response = App.getApplicationMessage();

        assertTrue(
                response.contains("Docker Build Optimization Project")
        );
    }
}