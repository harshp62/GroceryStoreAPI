package steps;

import dependencyinjection.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class MyStepdefs {

    TestContextSetup testContextSetup;
    public MyStepdefs (TestContextSetup testContextSetup) {

        this.testContextSetup =testContextSetup;
    }


    @Given("The API status is up")
    public void the_api_status_is_up() {

 given().log().all().spec(testContextSetup.req)
         .when().get("status")
         .then().log().all().assertThat().statusCode(200).body("status", equalTo("UP"));


    }
    @When("User sends a GET request on the product resource with {string} and {string}")
    public void user_sends_a_get_request_on_the_product_resource_with_and(String string, String string2) {

        testContextSetup.requestSpecification = given().spec(testContextSetup.req)
                .log().all().pathParams("resource","products")
                .queryParam("category",string)
                .queryParam("results",string2);
        testContextSetup.res = testContextSetup.requestSpecification
                .when().get("/{resource}");
    }
    @Then("the status code is {string}")
    public void the_status_code_is(String string) {
        // Write code here that turns the phrase above into concrete actions
testContextSetup.res.then().log().all().assertThat().statusCode(Integer.parseInt(string));
    }
    @Then("the category in the response is {string}")
    public void the_category_in_the_response_is(String string) {
        String response = testContextSetup.res.then().log().all().extract().response().asString();
        testContextSetup.jsonPath = new JsonPath(response);
        int size = testContextSetup.jsonPath.getList("$").size();

        for(int i=0; i<size;i++) {

            System.out.println(testContextSetup.jsonPath.getString("["+i+"].category"));
            assertTrue(testContextSetup.jsonPath.getString("["+i+"].category").equalsIgnoreCase(string));
        }


    }
    @Then("number of results displayed is {string}")
    public void number_of_results_displayed_is(String string) {

        String response = testContextSetup.res.then().log().all().extract().response().asString();
        testContextSetup.jsonPath = new JsonPath(response);
        String size = String.valueOf(testContextSetup.jsonPath.getList("$").size());

        assertTrue(size.equalsIgnoreCase(string));





    }


}
