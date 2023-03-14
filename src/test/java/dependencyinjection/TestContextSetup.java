package dependencyinjection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContextSetup {

    public RequestSpecification requestSpecification;
    public static RequestSpecification req;
    public Response res;
    public JsonPath jsonPath;

public TestContextSetup () {

    req = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://simple-grocery-store-api.glitch.me/")
            .build();



}


}
