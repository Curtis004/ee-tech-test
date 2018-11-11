@Functional
Feature: Delete bookings.
  As a user
  I would like to be able to delete my booking
  so that I can cancel my trip to the hotel.

  @Web
  Scenario: Delete a previously created booking.
    Given I have a previous booking
    When I land on the booking application
    And I delete that booking
    Then I should see that my booking is removed

  @Backend
  Scenario: Try to delete a booking that doesn't exist.
    Given I do not have a previous booking
    But I have a booking reference
    When I delete that booking
    Then I should see an error stating that booking doesn't exist

  @Backend
  Scenario: Try to delete a booking without authentication.
    Given I have a previous booking
    But I am not authenticated
    When I delete that booking
    Then I should see an error stating that I'm not authenticated

  @Backend
  Scenario: Try to delete a booking with invalid authentication.
    Given I have a previous booking
    But I am incorrectly authenticated
    When I delete that booking
    Then  I should see an error stating that I'm incorrectly authenticated