Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on the Ecommerce Page

  @Regression
  Scenario Outline: Positive Test for Submitting the Order
    Given I Logged in with email <email> and password <password>
    When I add product <productName> to the Cart
    And Checkout <productName> and submit the Order
    Then <confirmMessage> message is displayed on Confirmation Page
    And I should see the Order on Order History Page
    Examples:
      | email                    | password | productName | confirmMessage |
      | risshilbhatt@gmail.com   | Test@123 | ZARA COAT 3 | THANKYOU       |
      | rishilbhatt.qa@gmail.com | Test@123 | ADIDAS ORIGINAL | THANKYOU   |
