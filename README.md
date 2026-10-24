# Selenium Java Demo – UI Test Automation

UI test automation framework for [saucedemo.com](https://www.saucedemo.com), combining classic TestNG tests (Page Object Model) with BDD scenarios in Cucumber and Selenide.

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 21 | Language |
| Maven | 3.9 | Build & dependency management |
| Selenium WebDriver | 4.27.0 | Browser automation (TestNG tests) |
| Selenide | 7.5.1 | Concise UI automation with built-in waits (Cucumber steps) |
| Cucumber | 7.20.1 | BDD scenarios in Gherkin |
| TestNG | 7.10.2 | Test runner |

## Project Structure

```
src/test/java/
├── pages/        # Page Objects
├── tests/        # TestNG tests (Selenium + POM)
├── steps/        # Cucumber step definitions (Selenide)
└── runners/      # Cucumber runner (TestNG integration)
src/test/resources/
└── features/     # Gherkin feature files
```

## Test Coverage

**TestNG tests (`LoginTest`)**
- [TEST 1 – kurz beschreiben]
- [TEST 2 – kurz beschreiben]
- [TEST 3 – kurz beschreiben]

**Cucumber scenarios (`login.feature`)**
- Successful login with valid credentials
- Locked-out user sees the correct error message

## How to Run

Prerequisites: Java 21, Maven, Google Chrome.

```bash
# Run all tests
mvn test

# Run only the Cucumber scenarios
mvn test -Dtest=CucumberRunner
```

## Reports

- Cucumber HTML report: `target/cucumber-report.html`
- Surefire reports: `target/surefire-reports/`

## Design Decisions

- **Stable locators:** `data-test` attributes instead of CSS classes, which are often reused and change with styling.
- **Declarative Gherkin:** Feature files describe behaviour, not clicks; technical details live in the step code.
- **Parameterized steps:** Test data comes from the feature file (`{string}`), so one step covers multiple cases.
- **Explicit Surefire includes:** The runner class does not end in `Test`, so Surefire is configured to pick up `*Runner.java` as well. Otherwise the scenarios would silently be skipped.

## Roadmap

- [ ] Selenide-based Page Object for the Cucumber steps
- [ ] Unit tests with JUnit 5 and mocking (JMockit)
- [ ] CI pipeline (GitLab CI)
- [ ] Additional scenarios (cart, checkout)

**TestNG tests (`LoginTest`)**
- Valid login redirects to the products page
- Invalid password shows an error message
- Locked-out user sees an error message