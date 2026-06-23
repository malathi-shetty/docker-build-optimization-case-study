package com.company.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

@Test
void appClassShouldExist() {

    assertTrue(
            App.class.getName().contains("App")
    );
}

}