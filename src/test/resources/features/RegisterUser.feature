Feature: Register User Functionality


  Scenario Outline: As a user i want to register in sure service.
    Given I want to register

    Then I should see my created account

    Examples:
      | email              | password   | roles           |
      | carlos@gmail.com   | password   | ROLE_CLIENT     |
      | rody@gmail.com     | password   | ROLE_EMPLOYEE   |