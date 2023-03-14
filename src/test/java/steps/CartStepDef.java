package steps;

import dependencyinjection.TestContextSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;


public class CartStepDef {

    TestContextSetup testContextSetup;
    public static String cartId = "Q0rugwfQraRpTW0mCPSx-";

    public CartStepDef (TestContextSetup testContextSetup) {

        this.testContextSetup=testContextSetup;
    }

    @When("user sends a post http request to create a cart")
    public void userSendsAPostHttpRequest() {

        testContextSetup.requestSpecification = given().spec(testContextSetup.req).log().all();

        testContextSetup.res = testContextSetup.requestSpecification.when().post("carts");


    }


    @And("a cart is successfully created with an id")
    public void aCartIsSuccessfullyCreatedWithAnId() {

         testContextSetup.res.then().assertThat().body("created", equalTo(true));
         String response = testContextSetup.res.then().extract().body().asString();
         testContextSetup.jsonPath = new JsonPath(response);

         cartId= testContextSetup.jsonPath.getString("cartId");
        System.out.println(cartId);


    }

    @When("the user sends a get http request to retrieve a cart")
    public void theUserSendsAGetHttpRequestToRetrieveACart() {

        testContextSetup.requestSpecification = given().log().all().spec(testContextSetup.req)
                .pathParams("cartId",cartId);

        testContextSetup.res = testContextSetup.requestSpecification.when()
                .get("carts/{cartId}");

    }

    @And("the user gets the cart with his\\/her id")
    public void theUserGetsTheCartWithHisHerId() {


    }

    @And("the x-powered-by field in the header reponse is {string}")
    public void theXPoweredByFieldInTheHeaderReponseIs(String arg0) {

        testContextSetup.res.then().assertThat().header("x-powered-by", equalTo(arg0));
    }

    @When("user sends a post http request to add an item in cart")
    public void userSendsAPostHttpRequestToAddAnItemInCart() {

        testContextSetup.requestSpecification = given().spec(testContextSetup.req)
                .pathParams("cartId",cartId)
                .body("{\n" +
                        "   \"productId\": 4643,\n" +
                        "   \"quantity\": 2\n" +
                        "}");

        testContextSetup.res = testContextSetup.requestSpecification.when().log().all()
                .post("carts/{cartId}/items");

    }

    @And("the item is displayed in the cart")
    public void theItemIsDisplayedInTheCart() {

      String response =   given().spec(testContextSetup.req).pathParam("cartId", cartId).log().all()
                .when().get("carts/{cartId}/items")
                .then().log().all().extract().response().asString();

      testContextSetup.jsonPath = new JsonPath(response);

      assertTrue(testContextSetup.jsonPath.getString("[0].productId").equalsIgnoreCase("4643"));

    }

    @When("user sends a get http request to get cart items")
    public void userSendsAGetHttpRequestToGetCartItems() {

       testContextSetup.requestSpecification= given().log().all().spec(testContextSetup.req)
                .pathParams("cartId",cartId);
       testContextSetup.res = testContextSetup.requestSpecification.when()
               .get("carts/{cartId}/items").then().log().all().extract().response();



    }
}
