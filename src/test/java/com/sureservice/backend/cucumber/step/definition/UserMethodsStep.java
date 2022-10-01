package com.sureservice.backend.cucumber.step.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class UserMethodsStep {

    private RequestSpecification requestSpecification;

    private Response response;

    private Scenario scenario;

    private String token;
    private int statusCode;
    private String bodyMessage;

    @Before
    public void before(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("the user accesses the registration endpoint")
    public void theUserAccessesTheRegistrationEndpoint() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
        requestSpecification = given();
    }

    @When("Enter your registration information with the correct information:")
    public void enterYourRegistrationInformationWithTheCorrectInformation(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);
        Set<String> role = new HashSet<>();
        role.add(rows.get(1).get(2));
        HashMap user = new HashMap();
        user.put("email", rows.get(1).get(0));
        user.put("password", rows.get(1).get(1));
        user.put("roles", role);

        statusCode =
                given()
                .contentType("application/json")
                .body(user)
        .when()
                .post("http://localhost:8080/api/v1/users/auth/sign-up")
                .then()
                .extract().statusCode();

    }

    @Then("Response is {string}")
    public void responseIs(String status) { Assert.assertEquals(status, statusCode + ""); }

    @When("Enter your information:")
    public void enterYourInformation(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        Set<String> role = new HashSet<>();
        role.add(rows.get(1).get(2));
        HashMap user = new HashMap();
        user.put("email", rows.get(1).get(0));
        user.put("password", rows.get(1).get(1));
        user.put("roles", role);

        response =
                given()
                        .contentType("application/json")
                        .body(user)
                        .when()
                        .post("http://localhost:8080/api/v1/users/auth/sign-up");

        statusCode = response.getStatusCode();
        bodyMessage = response.body().print();
    }

    @And("the message is returned {string}")
    public void theMessageIsReturned(String message) {
        Assert.assertEquals(message, bodyMessage);
    }

    @Given("the user is registered in SureService")
    public void theUserIsRegisteredInSureService(DataTable dataTable) {
        enterYourInformation(dataTable);
    }

    @When("enter your login information:")
    public void enterYourLoginInformation(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        HashMap register = new HashMap();
        register.put("email", rows.get(1).get(0));
        register.put("password", rows.get(1).get(1));

        response =
                given()
                        .contentType("application/json")
                        .body(register)
                        .when()
                        .post("http://localhost:8080/api/v1/users/auth/sign-in");

        statusCode = response.getStatusCode();
        bodyMessage = response.body().print();
        if (statusCode == 200) token = response.body().path("token");
    }
}
