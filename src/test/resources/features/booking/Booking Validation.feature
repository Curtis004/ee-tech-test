@Functional
Feature: Validation when creating a booking.

  As a user
  I would like to be informed of any issues with the information I provide
  so that I can amend the offending information with correct information

  @Web
  Scenario Outline: Test that validation errors are thrown if invalid data is submitted through the app.
    Given I fill out the hotel booking with the following information:
      | firstname    | lastname   | totalprice | depositpaid | fromto                    |
      | <First Name> | <Lastname> | <Price>    | <Deposit>   | <Check In> to <Check Out> |
    When I save that booking information
    Then I should get an appropriate error message akin to "<Invalid Reason>"

    @Web
    Examples: Web input examples
      | First Name | Lastname | Price | Deposit | Check In | Check Out | Invalid Reason             |
      |            |          |       | false   |          |           | Null values provided       |
      | 123456     | 123123   | ABC   | true    | 123      | 123       | Wrong data types provided  |
      | £$%^&*     | £$%^&*   | 10    | true    | today+1  | today+2   | Special characters invalid |
      | Test       | Test     | -20   | false   | today+1  | today+2   | Price must be positive int |
      | Test       | Test     | 20    | true    | today-5  | today-3   | Checkin & out in the past  |
      | Test       | Test     | 20    | false   | today+2  | today-1   | Checkout in the past       |

  @Backend
  Scenario Outline: Test that validation errors are thrown if invalid data is submitted through the backend.
    Given I fill out the hotel booking with the following information:
      | firstname    | lastname   | totalprice | depositpaid | fromto                    |
      | <First Name> | <Lastname> | <Price>    | <Deposit>   | <Check In> to <Check Out> |
    When I save that booking information
    Then I should get an appropriate error message akin to "<Invalid Reason>"

    Examples: Backend examples
      | First Name | Lastname | Price | Deposit | Check In | Check Out | Invalid Reason          |
      | Test       | Test     | 10    | ABC     | today+1  | today+2   | Deposit value not valid |