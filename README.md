# Completed task
*This task is dedicated for job application.*

### Steps for demo test

1. (**Additional step**) Prepare test data. Valid Email for SignUp step is obtained from 3rd party source (instead of 
   fake email generation).
2. Searches for the Domain on the https://www.hostinger.com/domain-checker page;
3. Adds Alternative Domain to the Cart;
4. Ensuring that domain is Added to Cart (both in registration and summary part);
5. (**Additional step**) To access authentication page, pressing checkout button in cart page;
6. Signing up with Account;
7. Go to Payment method selection page (**in addition**: additional validation of payments page).
 
Additional notes / requirements for test implementation:
- Test automation framework and programming language is your choice. Please let us know which tools you have chosen;
- If there are “any specific actions” which needs to be done in order to Start the test - list them;
- Commit source to a public repository (GitHub) and send the link for us;
- Time frame: unlimited;
- Test should be repeatable

***

### Project implementation

Project was implemented using:

- ***Maven*** as project  build automation tool.
- ***Junit5*** as a framework for testing.
- ***Selenide*** as a framework for web UI automation.

### Project requirements

Tu run tests from this project, you will need:
- Use intellij (for example: *2020.3.2 community edition*) or any other similar IDE.
- Import source as **Maven** project.
- Project were tested only in Chrome. For this reason, **ChromeDriver** should be added to resources directory, so that 
  path of the chromedriver will look like – `src/main/resources/chromedriver` (name of chrome driver should be identical).
  Chromedriver could be  downloaded from [website]("https://chromedriver.chromium.org/downloads"). Please select Chrome
  version accordingly to your OS and **Chrome browser**. Chrome browser configs are placed under: `src/main/java/configs/UiConfigs.java`. 
- To start test execution, access `src/test/java/DomainOrdering.java` class and press 'play' button (class level/test method level)

*Additional notes: I was writing this code using java JDK **1.8.0_74**.*

***

*If you have any questions, please do not hesitate to ask.*

Ignas C.
  

