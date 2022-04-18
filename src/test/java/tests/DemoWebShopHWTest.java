package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.WishListModel;
import models.lombok.UserLombok;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static tests.Specs.request;
import static tests.Specs.responseSpec;
import static listener.CustomAllureListener.withCustomTemplates;

public class DemoWebShopHWTest {

    @Test
    void addToWishListTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("addtocart_18.EnteredQuantity=1&addtocart_19.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/18/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is(notNullValue()));
    }


    @Test
    void authorizationTest() {
        String authorizationCookie =

                given()
                        .filters(withCustomTemplates())
                        .spec(request)
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("Email", "mol@gmail.com")
                        .formParam("Password", "123123")
                        .when()
                        .post("http://demowebshop.tricentis.com/login")
                        .then()
                        .log().all()
                        .statusCode(302)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");

        open("http://demowebshop.tricentis.com/customer/info"); // открываем сайт для установки куки

        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)); // устанавливаем куки

        open("http://demowebshop.tricentis.com/customer/info"); // глазами смотрим что авторизация прошла

        $(".account").shouldHave(text("mol@gmail"));

    }

    @Test
    void addToWishListSpecTest() {

        WishListModel wishListModel =
                given()
                        .filters(withCustomTemplates())
                        .spec(request)
                        .body("addtocart_18.EnteredQuantity=1&addtocart_19.EnteredQuantity=1")
                        .when()
                        .post("addproducttocart/details/18/2")
                        .then()
                        .spec(responseSpec)
                        .log().all()
                        .statusCode(200)
                        .extract().as(WishListModel.class);

        assertThat(wishListModel.getSuccess()).isEqualTo(true);
        assertThat(wishListModel.getMessage()).isEqualTo("The product has been added to your <a href=\"/wishlist\">wishlist</a>");
        assertThat(wishListModel.getUpdateTopWishlistsectionhtml()).isEqualTo("(1)");
    }
}
