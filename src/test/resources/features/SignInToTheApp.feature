Feature: A user sign in to SureService

  Background:
    Given the user is registered in SureService
      | email                  |  password | roles       |
      | user@outlook.es        | 123456789 | ROLE_CLIENT |

  Scenario Outline: User enters correct credentials
    When enter your login information:
    | email                     | password  |
    | user@outlook.es           | 123456789 |
    Then Response is "<statusCode>"
    Examples:
      | statusCode |
      | 200        |

  Scenario Outline: User enters incorrect credentials
    When enter your login information:
      | email                     | password  |
      | user@outlook.es           | 12345678  |
    Then Response is "<statusCode>"
    And the message is returned "<message>"
    Examples:
      | statusCode | message                                                 |
      | 400        | An error occurred while authenticating: Bad credentials |
