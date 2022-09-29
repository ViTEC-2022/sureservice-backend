Feature: Register User Functionality


  Scenario Outline: As a user i want to register in sure service.
    Given I want to register
    When I enter my own information like email <email>, password <password> and roles <roles>
    Then I should see my created account

    Examples:
      | email              | password   | roles           |
      | carlos@gmail.com   | password   | ROLE_CLIENT     |
      | rody@gmail.com     | password   | ROLE_EMPLOYEE   |