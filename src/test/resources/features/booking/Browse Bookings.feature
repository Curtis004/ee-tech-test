@Functional
Feature: Browse existing bookings.

  As a user
  I would like to see all the bookings that are currently registered
  So that I can manage them

  @Web
  Scenario: Retrieve bookings that have been saved.
    Given I have a previous booking
    When I land on the booking application
    Then I should see my booking

  @Backend
  Scenario: Retrieve a booking that has not been saved.
    Given I do not have a previous booking
    But I have a booking reference
    When I try to retrieve that booking
    Then I should see that that booking cannot be found