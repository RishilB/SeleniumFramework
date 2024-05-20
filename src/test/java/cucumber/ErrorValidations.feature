Feature: Error Validation

  @ErrorValidations
  Scenario Outline: Error Validation Test with incorrect Email & Password
    Given I landed on the Ecommerce Page
    When I Logged in with email <email> and password <password>
    Then "Incorrect email or password." message is displayed.
    Examples:
      | email                    | password  | confirmMessage |
      | risshilbhatt@gmail.com   | Test@1234 | THANKYOU       |
      | rishilbhatt.qa@gmail.com | Test@1234 | THANKYOU       |
