Feature: Verify the POST method for services in SureService

  Scenario Outline: Enter correct information
    Given Get call to endpoint "<url>"
    When enter the information of a new service
    | name        | urlToImage                                                                                   | description                                                                              |
    | Example     | https://www.hogarmania.com/archivos/202002/proteger-las-tuberias-del-frio-668x400x80xX-1.jpg | the pipes in my house have been broken for more than a week, I need an urgent technician |
    Then response is "<statusCode>"
    Examples:
      | url       | statusCode |
      | /services | 200        |


