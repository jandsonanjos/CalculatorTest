package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    void deveRealizarGET() {

        given()

                .when()
                .get("/posts/1")

                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void deveRealizarPOST() {

        String body = """
                {
                  "title": "teste",
                  "body": "api",
                  "userId": 1
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .post("/posts")

                .then()
                .statusCode(201)
                .body("title", equalTo("teste"));
    }

    @Test
    void deveRealizarPUT() {

        String body = """
                {
                  "id": 1,
                  "title": "atualizado",
                  "body": "novo",
                  "userId": 1
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .put("/posts/1")

                .then()
                .statusCode(200)
                .body("title", equalTo("atualizado"));
    }

    @Test
    void deveRealizarDELETE() {

        given()

                .when()
                .delete("/posts/1")

                .then()
                .statusCode(200);
    }
}