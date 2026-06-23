package com.company.demo;

public final class AppService {

    private AppService() {
    }

    public static final String PROJECT_NAME =
            "Docker Build Optimization Project";

    public static String getProjectName() {
        return PROJECT_NAME;
    }

    public static final String HTML_RESPONSE = """
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
}