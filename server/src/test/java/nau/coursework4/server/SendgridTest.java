package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendgridTest {

    @Test
    void sendEmail() {
        Sendgrid.sendEmail("support@crwnd.dev", "test", "test");
    }
}