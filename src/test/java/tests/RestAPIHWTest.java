package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static listener.CustomAllureListener.withCustomTemplates;
import static org.hamcrest.Matchers.*;

public class RestAPIHWTest {


    @Test
    void singleUserTest() {

        given()
                .filter(withCustomTemplates())
                .when()
                .contentType(JSON)
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    void singleUserNotFoundTest() {
        given()
                .filter(withCustomTemplates())
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void listTest() {
        given()
                .filter(withCustomTemplates())
                .param("id", 2)
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("data.name", is("fuchsia rose"));
    }

    @Test
    void createTest() {
        String authDataCreate = "{\"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .filter(withCustomTemplates())
                .body(authDataCreate)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"));

    }

    @Test
    void upDateTest() {
        String upData = "{\"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given()
                .filter(withCustomTemplates())
                .body(upData)
                .param("name", "morpheus")
                .param("job", "zion resident")
                .param("updatedAt", "2022-04-08T09:32:51.115Z")
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("job", is("zion resident"));
    }


    @Test
    void deleteTest() {
        given()
                .filter(withCustomTemplates())
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);

    }



    @Test
    void registerSuccessfulTest() {
        String inputData = "{\"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .filter(withCustomTemplates())
                .body(inputData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is(notNullValue()));
    }


}
