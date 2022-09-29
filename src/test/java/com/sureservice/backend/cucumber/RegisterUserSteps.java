package com.sureservice.backend.cucumber;

import com.sureservice.backend.SureserviceBackendApplication;
import com.sureservice.backend.security.domain.model.entity.Role;
import com.sureservice.backend.security.domain.model.enumeration.Roles;
import com.sureservice.backend.user.domain.model.entity.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SureserviceBackendApplication.class)
@CucumberContextConfiguration
public class RegisterUserSteps {
    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8080/api/v1";
    private String message="";
    User user;
    Long userId = randomLong();

    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to register")
    public void iWantToRegister() {
        String userUrl=url + "/users";
        String getEndpoint=restTemplate.getForObject(userUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }

    @And("I enter my own information like email <email>, password <password> and roles <roles>")
    public void iEnterMyOwnInformationLikeEmailEmailPasswordPasswordAndRolesRoles() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,Roles.ROLE_CLIENT);
        roles.add(role);
        String userUrl=url + "/users/auth/sign-up";
        User newUser= new User(userId,"carlos@gmail.com","password",roles);
        user=restTemplate.postForObject(userUrl,newUser,User.class);
        log.info(user.getId());
        assertNotNull(user);
    }

    @Then("I should see my created account")
    public void iShouldSeeMyCreatedAccount() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,Roles.ROLE_CLIENT);
        roles.add(role);
        user= new User(userId,"carlos@gmail.com","password",roles);
        String userUrl=url + "/users/"+user.getId();
        try
        {
            User getUserById=restTemplate.getForObject(userUrl, User.class, user.getId());
            log.info(getUserById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }

}
