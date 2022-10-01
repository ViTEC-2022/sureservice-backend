Feature: Register a user in SureService

  Background:
    Given the user accesses the registration endpoint

  Scenario Outline: User enters valid information
    When Enter your registration information with the correct information:
      | email                  |  password | roles       |
      | user1@outlook.es       | 123456789 | ROLE_CLIENT |
    Then Response is "<statusCode>"
    Examples:
      | statusCode |
      | 200        |

  Scenario Outline: User enters invalid information
    When Enter your information:
      | email              |  password | roles       |
      | user               | 123456789 | ROLE_CLIENT |
    Then Response is "<statusCode>"
    Examples:
      | statusCode |
      | 400        |

  Scenario Outline: User is already registered
    When Enter your information:
      | email                  |  password | roles       |
      | user1@outlook.es       | 123456789 | ROLE_CLIENT |
    Then Response is "<statusCode>"
    And the message is returned "<message>"
    Examples:
      | statusCode | message                |
      | 400        | Email is already used. |

