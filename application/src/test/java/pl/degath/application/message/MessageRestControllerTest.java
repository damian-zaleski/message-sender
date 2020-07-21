package pl.degath.application.message;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pl.degath.application.message.request.SaveMessageRequest;
import pl.degath.application.message.request.SendMessagesRequest;
import pl.degath.message.domain.Message;
import pl.degath.message.port.MessageRepository;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("requires-cassandra")
public class MessageRestControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private MessageRepository repo;

    @Test
    public void saveMessage() {
        SaveMessageRequest request = new SaveMessageRequest("zaleskid1@gmail.com", "asdsad", "This is email content", 15);

        given()
                .contentType(APPLICATION_JSON.toString())
                .body(request)
                .post("/api/v1/messages")
                .then()
                .statusCode(200);
    }

    @Test
    public void sendMail() {
        repo.insert(new Message("Test title", "this is test content", "test@gmail.com", 15));

        SendMessagesRequest request = new SendMessagesRequest(15);

        given()
                .contentType(APPLICATION_JSON.toString())
                .body(request)
                .post("/api/v1/messages/send")
                .then()
                .statusCode(200);
    }

    @Test
    public void getMessagesByEmail() {
        existingUser();
        existingUser();

        given()
                .pathParam("email", "test@gmail.com")
                .param("pageNumber", 0)
                .param("size", 5)
                .get("/api/v1/messages/{email}")
                .then()
                .statusCode(200);
    }

    private void existingUser() {
        repo.save(new Message("Test title", "this is test content", "test@gmail.com", 15));
    }

    @Test
    public void sohuldThrow400IfValidationFailed() {
        given()
                .pathParam("email", "DefinitelyNotEmail")
                .param("pageNumber", 0)
                .param("size", 5)
                .get("/api/v1/messages/{email}")
                .then()
                .statusCode(400);
    }

    @Test
    @Tag("slow")
    public void shouldRemoveSavedMessageAfter5Minutes() {
        SaveMessageRequest request = new SaveMessageRequest("zaleskid1@gmail.com", "asdsad", "This is email content", 15);

        given()
                .contentType(APPLICATION_JSON.toString())
                .body(request)
                .post("/api/v1/messages")
                .then()
                .statusCode(200);

        var sizeBefore5Minutes = repo.count();
        wait5Minutes();
        var sizeAfter5Minutes = repo.count();
        assertThat(sizeBefore5Minutes).isGreaterThan(sizeAfter5Minutes);
    }

    private void wait5Minutes() {
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
