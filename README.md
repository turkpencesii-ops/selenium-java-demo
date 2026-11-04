# Selenium Java Demo – UI Test Automation

[![pipeline status](https://gitlab.com/turkpencesii-ops1/selenium-java-demo/badges/main/pipeline.svg)](https://gitlab.com/turkpencesii-ops1/selenium-java-demo/-/pipelines)

UI test automation framework for [saucedemo.com](https://www.saucedemo.com), combining classic TestNG tests (Page Object Model) with BDD scenarios in Cucumber and Selenide, plus unit tests with JUnit 5 and JMockit.

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 21 | Language |
| Maven | 3.9 | Build & dependency management |
| Selenium WebDriver | 4.27.0 | Browser automation (TestNG tests) |
| Selenide | 7.5.1 | Concise UI automation with built-in waits (Cucumber steps) |
| Cucumber | 7.20.1 | BDD scenarios in Gherkin |
| TestNG | 7.10.2 | Test runner |
| JUnit 5 | 5.11.4 | Unit tests |
| JMockit | 1.56.0 | Mocking in unit tests |

## Project Structure

```
src/main/java/
└── payment/      # Sample payment service (unit-tested with mocks)
src/test/java/
├── base/         # Test base class (WebDriver setup/teardown)
├── pages/        # Page Objects (Selenium for TestNG, Selenide for Cucumber)
├── tests/        # TestNG tests (Selenium + POM)
├── steps/        # Cucumber step definitions (Selenide)
├── runners/      # Cucumber runner (TestNG integration)
└── payment/      # Unit tests (JUnit 5 + JMockit)
src/test/resources/
└── features/     # Gherkin feature files
```

## Test Coverage

**TestNG tests (`LoginTest`)**
- Valid login redirects to the products page
- Invalid password shows an error message
- Locked-out user sees an error message

**Cucumber scenarios (`login.feature`)**
- Successful login with valid credentials
- Locked-out user sees the correct error message

**Unit tests (`PaymentServiceTest`, JUnit 5 + JMockit)**
- Successful payment returns true
- Declined payment returns false
- Negative amount is rejected without calling the payment gateway

## How to Run

Prerequisites: Java 21, Maven, Google Chrome.

```bash
# Run all tests
mvn test

# Run only the Cucumber scenarios
mvn test -Dtest=CucumberRunner

# Run only the unit tests (no browser needed)
mvn test -Dtest=PaymentServiceTest
```

## Continuous Integration

All tests run automatically on every push in a [GitLab CI pipeline](https://gitlab.com/turkpencesii-ops1/selenium-java-demo/-/pipelines), defined in `.gitlab-ci.yml`. Docker image: `maven:3.9-eclipse-temurin-21`. Two jobs run in parallel in the `test` stage:

| Job | What it runs | Why |
|---|---|---|
| `unit-tests` | `mvn -B test -Dtest=PaymentServiceTest` | Fast feedback in seconds, no browser needed |
| `ui-tests` | Full suite (TestNG, Cucumber, unit tests) in headless Chrome | End-to-end check of the UI flows |

- The `ui-tests` job installs Google Chrome in the container; Selenium Manager resolves the matching ChromeDriver.
- Headless mode is switched on only in CI (`-Dheadless=true`, `-Dselenide.headless=true`), so tests still open a visible browser locally for debugging.
- JUnit XML reports are published to GitLab's test report view. The Cucumber HTML report and Selenide failure screenshots are kept as job artifacts.

Run the UI tests headless on your machine:

```bash
mvn test -Dheadless=true -Dselenide.headless=true
```

## Reports

- Cucumber HTML report: `target/cucumber-report.html`
- Surefire reports: `target/surefire-reports/`

## Design Decisions

- **Stable locators:** `data-test` attributes instead of CSS classes, which are often reused and change with styling.
- **Declarative Gherkin:** Feature files describe behaviour, not clicks; technical details live in the step code.
- **Parameterized steps:** Test data comes from the feature file (`{string}`), so one step covers multiple cases.
- **Explicit Surefire includes:** The runner class does not end in `Test`, so Surefire is configured to pick up `*Runner.java` as well. Otherwise the scenarios would silently be skipped.
- **Two test frameworks in one build:** Surefire runs TestNG and the JUnit Platform side by side through explicitly configured providers.

## Roadmap

- [x] Selenide-based Page Object for the Cucumber steps
- [x] Unit tests with JUnit 5 and mocking (JMockit)
- [x] CI pipeline for unit tests (GitLab CI)
- [x] Run UI and Cucumber tests in CI (headless Chrome)
- [ ] Maven dependency cache in CI for faster pipelines
- [ ] Additional scenarios (cart, checkout)
