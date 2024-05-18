package nau.coursework4.server;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Sendgrid {

    private static final String SENDGRID_API_KEY = "aaa";  // Replace with your SendGrid API key
    private static final String SENDGRID_URL = "https://api.sendgrid.com/v3/mail/send";

    public static void sendEmail(String email, String subject, String body) {
        // Json payload string
        String jsonPayload = String.format("""
            {
              "personalizations": [{
                "to": [{"email": "%s"}]
              }],
              "from": {"email": "support@crwnd.dev"},
              "subject": "%s",
              "content": [{
                "type": "text/plain",
                "value": "%s"
              }]
            }
            """, email, subject, body);

        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SENDGRID_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SENDGRID_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 202) throw new RuntimeException("Failed to send email");

            // Print status code and response body
            System.out.println("[Sendgrid] Status Code: " + response.statusCode());
            System.out.println("[Sendgrid] Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}