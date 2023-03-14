package steps;

import dependencyinjection.TestContextSetup;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;

public class TestHooks {



    @BeforeAll
    public static void deleteItems() {

        given().spec(new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://simple-grocery-store-api.glitch.me/")
                .build()).log().all()
                .pathParam("cartId", CartStepDef.cartId)
                .pathParam("itemId", "922355210")
                .when().delete("carts/{cartId}/items/{itemId}")
                .then().assertThat().statusCode(204);
    }
}
