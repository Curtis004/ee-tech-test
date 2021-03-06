# EE Tech Test

**IMPORTANT: The tests were written with my opinions in full force therefore some of them fail based on the functional requirements I came up with.
This was to ensure I could write enough code to demonstrate what I wanted to demonstrate and not be limited by current functional capability.**

## Keep in mind

* I created the framework on an OSX machine, I've implemented measures to ensure it'll work in chrome on windows and linux but not much else.
* The code is overkill in relation to what we're testing but it's intended to show how I think and tackle problems.. testing the booking site it merely a side effect.
* The manual testing resources and executions are in `./manual/`.
* Cross browser testing can be done through serenity and aggregate reporting, so I didn't include it as it's not my own solution.
* No comments, normally anything I build has complete javadoc (or equivalent) but time.

## Summary of Approach

### Manual

1. I started by doing a general security and standards audit of the application including some basic penetration testing and WAI comparisons.
I found an array of issues both major and minor with security and accessibility as well as instances of non adherence to the HTTP spec.
My findings from this audit are `./manual/Security and Standards Audit.md`

2. After the audit was complete I needed to define some operating parameters for my manual an automated testing.
I choose a BDD format for describing some implied functional expectations as the syntax is transferable between both manual and automated testing.
My testing specification is `./manual/Functional Testing Spec.md`

3. Once I had the specification I could run my first manual execution, which it did. Results can be found `./manual/Manual Execution - Run 1 (10-11-2018).md`

### Automated

1. I chose to go with Java as a few projects I'm working on are using it and therefore I wouldn't require any refreshment.
In choosing Java the frameworks I advocate (changes from time to time) are Cucumber, Serenity and Rest Assured (API testing).
I believe that BDD has it's place and serenity provides helpful abstraction to get moving quickly and great reporting, I decided
that the screenplay pattern would be overkill for what I was doing so this is a vanilla cucumber and serenity project.

2. Using the stories defined for the manual testing I created feature files, step libraries and page objects (pretty much in that order) for web testing.
For testing the backend directly I integrated rest assured into steps specifically used to interact with the backend.

## Running the tests

You can run the tests with maven using `mvn verify`.

### Reports

Reports are generated by serenity, they can be found at `./target/site/index.html` once the tests have run.