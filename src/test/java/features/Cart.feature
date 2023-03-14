Feature: Carts

  @Create
  Scenario: Test Creating cart
    Given The API status is up
    When user sends a post http request to create a cart
    Then the status code is "201"
    And a cart is successfully created with an id
    And the x-powered-by field in the header reponse is "Express"

  Scenario: Test Get cart
    Given The API status is up
    When the user sends a get http request to retrieve a cart
    Then the status code is "200"
    And the x-powered-by field in the header reponse is "Express"

  Scenario: Test add tem to cart
    Given The API status is up
    When user sends a post http request to add an item in cart
    Then the status code is "201"
    And the item is displayed in the cart

    Scenario: test get cart items
      Given The API status is up
      When user sends a get http request to get cart items
      Then the status code is "200"





