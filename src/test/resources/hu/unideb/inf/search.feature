Feature: YourLogo search process

  Background:
    Given The home page is opened

  Scenario Outline:
    Given The search field is filled with '<data>'
    And I click the search button
    Then I get '<product>'
    Examples:
      | data | product |
      | Blouse | Blouse |
      | Faded  | Faded Short Sleeve T-shirts |