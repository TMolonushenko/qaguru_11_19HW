package tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.containsString;

public class SpecDemoWebShop {

    public static RequestSpecification requestD =
            with()
                    .baseUri("http://demowebshop.tricentis.com/")
                    .log().all()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    public static ResponseSpecification responseSpecD = new ResponseSpecBuilder()
            .expectBody(containsString("success"))
            // .expectStatusCode(200)
            .build();
}
