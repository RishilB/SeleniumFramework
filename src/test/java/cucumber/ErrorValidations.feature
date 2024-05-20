Feature: Error Validation

  Scenario Outline: Error Validation Test with incorrect Email & Password
    Given I landed on the Ecommerce Page
    When I Logged in with email <email> and password <password>
    Then <confirmMessage> message is displayed in Toast
    Examples:
      | email                    | password  | confirmMessage |
      | risshilbhatt@gmail.com   | Test@1234 | THANKYOU       |
      | rishilbhatt.qa@gmail.com | Test@1234 | THANKYOU       |
