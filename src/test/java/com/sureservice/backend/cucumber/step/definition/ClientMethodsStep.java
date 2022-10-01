package com.sureservice.backend.cucumber.step.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ClientMethodsStep {

    private RequestSpecification requestSpecification;
    private Response response;
    private Scenario scenario;

    private String URL;
    private String token;
    private int userId;
    private int statusCode;
    private String bodyMessage;

    @Before
    public void before(Scenario scenario){
        this.scenario = scenario;
    }
    @Given("The applicant is already registered as a user")
    public void theApplicantIsAlreadyRegisteredAsAUser() {
        Set<String> role = new HashSet<>();
        role.add("ROLE_CLIENT");
        HashMap user = new HashMap();
        user.put("email", "user@outlook.es");
        user.put("password", "123456789");
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

    @And("log In")
    public void logIn() {
        HashMap register = new HashMap();
        register.put("email", "user@outlook.es");
        register.put("password", "123456789");

        response =
                given()
                        .contentType("application/json")
                        .body(register)
                        .when()
                        .post("http://localhost:8080/api/v1/users/auth/sign-in");

        statusCode = response.getStatusCode();
        bodyMessage = response.body().print();
        token = response.body().path("token");
        userId = response.body().path("id");

    }

    @When("enter your registration information")
    public void enterYourRegistrationInformation(DataTable dataTable) {
        URL = "http://localhost:8080/api/v1/clients/" + userId;

        List<List<String>> rows = dataTable.asLists(String.class);
        HashMap client = new HashMap();
        client.put("name", rows.get(1).get(0));
        client.put("age", Integer.valueOf(rows.get(1).get(1)));
        client.put("phone", rows.get(1).get(2));
        client.put("altphone", rows.get(1).get(3));
        client.put("urlToImage", rows.get(1).get(4));
        client.put("address", rows.get(1).get(5));
        client.put("description", rows.get(1).get(6));

        response =
                given()
                        .contentType("application/json")
                        .header(new Header("Authorization", "Bearer " + token))
                        .body(client)
                        .when()
                        .post(URL);

        statusCode = response.getStatusCode();
    }

    @Then("get status code {string}")
    public void getStatusCode(String status) { Assert.assertEquals(status, statusCode + ""); }
}
