package tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specs {

    public static RequestSpecification request =
            with()
                    .baseUri("http://demowebshop.tricentis.com/")
                    .log().all()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            // .expectBody(containsString("success"))
            .build();
}
