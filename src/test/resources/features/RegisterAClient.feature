Feature: Register a client in SureService

  Background:
    Given The applicant is already registered as a user
    And log In

  Scenario Outline: The user enters correct information
    When enter your registration information
      | name     | age | phone     | altphone | urlToImage                                                                    | address         | description |
      | Leonardo | 34  | 951000410 | 85124    | https://pm1.narvii.com/6815/fb3e7bc27968ffa315835f9725f82881231c6711v2_hq.jpg | Calle Nueva Dem | example     |
    Then get status code "<statusCode>"
    Examples:
      | statusCode |
      | 200        |

