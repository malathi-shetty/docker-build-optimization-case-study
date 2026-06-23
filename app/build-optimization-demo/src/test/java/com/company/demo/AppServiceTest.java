package com.company.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppServiceTest {

@Test
void shouldContainProjectName() {

    assertEquals(
            "Docker Build Optimization Project",
            AppService.PROJECT_NAME
    );
}

@Test
void shouldContainHtmlResponse() {

    assertTrue(
            AppService.HTML_RESPONSE.contains("<html>")
    );

    assertTrue(
            AppService.HTML_RESPONSE.contains("</html>")
    );

    assertTrue(
            AppService.HTML_RESPONSE.contains(
                    "Docker Build Optimization Project"
            )
    );

    assertTrue(
            AppService.HTML_RESPONSE.contains(
                    "Application Started Successfully"
            )
    );

    assertTrue(
            AppService.HTML_RESPONSE.contains(
                    "Backend Response Received"
            )
    );
}

}