Feature: Test Basic feature
  Scenario Outline: Test retrieving products
    Given The API status is up
    When  User sends a GET request on the product resource with "<category>" and "<results>"
    Then the status code is "200"
    And the x-powered-by field in the header reponse is "Express"
    And the category in the response is "<category>"
    And number of results displayed is "<results>"

    Examples:
    |category     |results|
    |dairy        |3      |
    |fresh-produce|2      |

