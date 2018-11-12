# Manual Execution - Run 1 (10/11/2018)

**NOTE: I wouldn't normally use markdown to write up this sort of thing, you'd hope there would be appropriate tooling.. but if not I'd recommend getting some.**

## Details
*Spec: Functional Testing Spec.md*<br />
*Executor: Joshua Curtis*

Stories and scenarios link back to the testing spec, as do the steps to reproduce.

| No. | Story | Scenario | Summary                         | Expected           | Actual             | Pass/Fail | Remedial Action |
|-----|-------|----------|---------------------------------|--------------------|--------------------|-----------|-----------------|
| 1   | 1.    | 1.       | Browse the bookings             | Booking is visible | Booking is visible | Pass      | N/A             |
| 2   | 1.    | 2.       | Get a booking that doesnt exist | Booking not found  | Booking not found  | Pass      | N/A             |

| No. | Story | Scenario | Summary                              | Expected           | Actual             | Pass/Fail | Remedial Action |
|-----|-------|----------|--------------------------------------|--------------------|--------------------|-----------|-----------------|
| 3   | 2.    | 1.       | Create a new booking                 | Booking is created | Booking is created | Pass      | N/A             |
| 4   | 2.    | 2.       | Create a new booking (false deposit) | Booking is created | Booking is created | Pass      | N/A             |

| No. | Story | Scenario | Summary                       | Expected           | Actual                 | Pass/Fail | Remedial Action                       |
|-----|-------|----------|-------------------------------|--------------------|------------------------|-----------|---------------------------------------|
| 5   | 3.    | 1.       | Delete existing booking       | Booking is deleted | Booking is deleted     | Pass      | N/A                                   |
| 6   | 3.    | 2.       | Delete non existent booking   | Booking not found  | 405 Method Not allowed | Fail      | Method is allowed, resource not found |
| 7   | 3.    | 3.       | Delete a booking without auth | 401 Unauthorized   | 403 Forbidden          | Pass      | Consider 401, see RFC7235             |
| 8   | 3.    | 4.       | Delete a booking invalid auth | 403 Forbidden      | 403 Forbidden          | Pass      | N/A                                   |

| No. | Story | Scenario | Summary                           | Expected      | Actual          | Pass/Fail | Remedial Action      |
|-----|-------|----------|-----------------------------------|---------------|-----------------|-----------|----------------------|
| 9   | 4.    | 1.       | Create with null fields           | Error message | Fails silently  | Fail      | Implement validation |
| 10  | 4.    | 2.       | Create with incorrect data types  | Error message | Fails silently  | Fail      | Implement validation |
| 11  | 4.    | 3.       | Create with special chars         | Error message | Fails silently  | Fail      | Implement validation |
| 12  | 4.    | 4.       | Create with negative price        | Error message | Fails silently  | Fail      | Implement validation |
| 13  | 4.    | 5.       | Create with multi decimal price   | Error message | Creates booking | Fail      | Implement validation |
| 14  | 4.    | 5.       | Create with checkin & out in past | Error message | Fails silently  | Fail      | Implement validation |
| 15  | 4.    | 6.       | Create with checkout              | Error message | Fails silently  | Fail      | Implement validation |
| 16  | 4.    | 7.       | Create with invalid deposit       | Error message | Fails silently  | Fail      | Implement validation |


