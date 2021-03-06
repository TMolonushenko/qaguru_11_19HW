package tests;

import config.Data;
import models.Credentials;
import models.WishListModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static listener.CustomAllureListener.withCustomTemplates;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tests.SpecDemoWebShop.requestD;
import static tests.SpecDemoWebShop.responseSpecD;


public class DemoWebShopOwnerTest  {

    @Test
    void addToWishListSpecTest() {


        WishListModel wishListModel =
                given()
                        .filter(withCustomTemplates())
                        .spec(requestD)
                        .body("addtocart_18.EnteredQuantity=1&addtocart_19.EnteredQuantity=1")
                        .when()
                        .post("addproducttocart/details/18/2")
                        .then()
                        .spec(responseSpecD)
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(WishListModel.class);

        assertThat(wishListModel.getSuccess()).isEqualTo(true);
        assertThat(wishListModel.getMessage()).isEqualTo("The product has been added to your <a href=\"/wishlist\">wishlist</a>");
        assertThat(wishListModel.getUpdateTopWishlistsectionhtml()).isEqualTo("(1)");
    }

    @Test
    void authorizationOwnerTest() {


        String authorizationCookie =
                given()
                        .filter(withCustomTemplates())
                        .formParam("Email", Data.config.email())
                        .formParam("Password", Data.config.password())
                        .spec(requestD)
                        .when()
                        .post("login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(302)
                        .extract().cookie("NOPCOMMERCE.AUTH");

        open("http://demowebshop.tricentis.com/customer/info"); // ?????????????????? ???????? ?????? ?????????????????? ????????

        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)); // ?????????????????????????? ????????

        open("http://demowebshop.tricentis.com/customer/info");// ?????????????? ?????????????? ?????? ?????????????????????? ????????????
        sleep(5000);
        $(".account").shouldHave(text("mol@gmail"));

    }

}
