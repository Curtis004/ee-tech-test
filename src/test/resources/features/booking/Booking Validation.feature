@Functional
Feature: Validation when creating a booking.

  As a user
  I would like to be informed of any issues with the information I provide
  so that I can amend the offending information with correct information

  Scenario Outline:
    Given I fill out the hotel booking with the following information:
      | firstname    | lastname   | totalprice | depositpaid | bookingdates              |
      | <First Name> | <Lastname> | <Price>    | <Deposit>   | <Check In> to <Check Out> |
    When I save that information
    Then I should get an appropriate error message akin to "<Invalid Reason>"

  @Web
    Examples:
      | First Name | Lastname | Price | Deposit | Check In | Check Out | Invalid Reason            |
#      |            |          |       | false   |          |           | Null values provided |
      | 123456     | 123123   | ABC   | true    | ABC      | ABC       | Wrong data types provided |
#      | £$%^&*     | £$%^&*  | 10    | true    | today+1  | today+1   | Special characters invalid |
#      | Test       | Test    | -20   | false   | today+1  | today+2   | Price must be positive int |
#      | Test       | Test    | 20    | true    | today-5  | today-3   | Checkin & out in the past  |
#      | Test       | Test    | 20    | false   | today+2  | today-1   | Checkout in the past       |
#
#  @Backend
#    Examples:
#      | First Name | Surname | Price | Deposit | Check In | Check Out | Invalid Reason          |
#      | Test       | Test    | 10    | ABC     | today+1  | today+2   | Deposit value not valid |