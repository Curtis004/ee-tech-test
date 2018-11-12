# Manual Testing Spec

**NOTE: I wouldn't normally use markdown to write up this sort of thing, you'd hope there would be appropriate tooling.. but if not I'd recommend getting some.**

## User Stories

Given that there were no provided functional requirements I've written a few basic ones that can be used as both manual and automated tests.

---

**1. As a user I would like to see all the bookings that are currently registered so that I can manage them.**

```
   @Web
1. Given I have a previous booking
   When I land on the booking application
   Then I should see my booking
```
```
   @Backend
2. Given I do not have a previous booking
   But I have a booking reference
   When I try to retreive that booking
   Then I should see that that booking cannot be found
```

---

**2. As a user, I would like to be able to enter my details into the hotel booking form an see my booking appear as the next item in the table so that I can book my stay at the hotel.**

```
   @Web
1. Given I fill out the hotel booking with my information.
   And I specify that I <Condition> be paying a deposit
   When I save that information
   Then my booking request should be present

   Examples:
    | Condition |
    | will      |
    | will not  |
```

---

**3. As a user I would like to be able to delete my booking so that I can cancel my trip to the hotel.**

```
   @Web
1. Given I have a previous booking
   When I delete that booking
   Then I should see that my booking is removed
```
```
   @Backend
2. Given I do not have a previous booking
   But I have a booking reference
   When I delete that booking
   Then I should see that the booking cannot be found
```
```
   @Backend
3. Given I have a previous booking
   But I am not authenticated
   When I delete that booking
   Then I should see that I am not authenticated
```
```
   @Backend
4. Given I have a previous booking
   But I am incorrectly authenticated
   When I delete that booking
   Then I should see that I am incorrectly authenticated
```
---

**4. As a user I would like to be informed of any issues with the information I provide so that I can amend the offending information with correct information.**

```
   @Web
1. Given I fill out the hotel booking with the following information:
    | firstname    | surname   | price   | deposit   | checkin    | checkout    |
    | <First Name> | <Surname> | <Price> | <Deposit> | <Check In> | <Check Out> |
    When I save that information
    Then I should get an appropriate error message akin to "<Invalid Reason>"

   Examples:
    | <First Name> | <Surname> | <Price> | <Deposit> | <Check In> | <Check Out> | Invalid Reason             |
    |              |           |         | false     |            |             | Null values provided       |
    | 123456       | 123123    | ABC     | true      | ABC        | ABC         | Wrong data types provided  |
    | £$%^&*       | £$%^&*    | 10      | true      | today+1    | today+1     | Special characters invalid |
    | Test         | Test      | -20     | false     | today+1    | today+2     | Price must be positive int |
    | Test         | Test      | 20      | true      | today-5    | today-3     | Checkin & out in the past  |
    | Test         | Test      | 20      | false     | today+2    | today-1     | Checkout in the past       |

   @Backend
   Examples:
    | <First Name> | <Surname> | <Price> | <Deposit> | <Check In> | <Check Out> | Invalid Reason          |
    | Test         | Test      | 10      | ABC       | today+1    | today+2     | Deposit value not valid |
```

---