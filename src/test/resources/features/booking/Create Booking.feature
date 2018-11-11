@Functional
Feature: Create a new booking.

  As a user
  I would like to be able to enter my details into the hotel booking form an see my booking appear as the next item in the table
  So that I can book my stay at the hotel.

  @Web
  Scenario Outline: Create a booking with and without a deposit.
    Given I fill out the hotel booking with my information
    And I specify that I <Condition> be paying a deposit
    When I save that information
    Then I should see my booking

    Examples:
      | Condition |
      | will      |
      | will not  |