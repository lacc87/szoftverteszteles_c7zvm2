Feature: YourLogo registration process

  Background:
    Given The home page is opened
    And The Sign in link is clicked

  Scenario:
    Given The Create an account is clicked
    Then an Invalid email address error message is shown

  Scenario Outline:
    Given The Email address field is field with random valid email
    When I click on Create an account
    And I leave empty this '<field>'
    And I click on the register button
    Then I get '<msg>' error message
    Examples:
      | field              | msg                                          |
      | customer_firstname | firstname is required.                       |
      | phone_mobile       | You must register at least one phone number. |
      | customer_lastname  | lastname is required.                        |
      | passwd             | passwd is required.                          |
      | address1           | address1 is required.                        |
      | city               | city is required.                            |
      | postcode           | The Zip/Postal code you've entered is invalid. It must follow this format: 00000 |
      | id_state           | This country requires you to choose a State.                                     |


