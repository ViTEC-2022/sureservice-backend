package com.sureservice.backend.cucumber.step.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ServiceMethodsStep {

    private RequestSpecification requestSpecification;
    private Response response;
    private Scenario scenario;

    private String URL;
    private String token;
    private int userId;
    private int statusCode;
    private String bodyMessage;
    @Given("Get call to endpoint {string}")
    public void getCallToEndpoint(String arg0) {
        URL = "http://localhost:8080/api/v1" + arg0;
        RestAssured.baseURI = URL;
        requestSpecification = given();
    }

    @When("enter the information of a new service")
    public void enterTheInformationOfANewService(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        HashMap service = new HashMap();
        service.put("name", rows.get(1).get(0));
        service.put("urlToImage", rows.get(1).get(1));
        service.put("description", rows.get(1).get(2));

        response =
                given()
                        .contentType("application/json")
                        .body(service)
                        .when()
                        .post(URL);

        statusCode = response.getStatusCode();

    }

    @Then("response is {string}")
    public void responseIs(String status) {
        Assert.assertEquals(status, statusCode + "");
    }
}
