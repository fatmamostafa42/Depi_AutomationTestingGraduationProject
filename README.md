# BrowserStack Demo Automation

Selenium/TestNG automation framework for the BrowserStack Demo single page application.

The old `ONL4_SWD6_G3` project is used as a reference only for the Page Object style.

## Structure

```text
src
|
+-- main
|
+-- test
    +-- java
        +-- baseTest
        |   +-- BaseTest.java
        +-- pages
        |   +-- HomePage.java
        |   +-- LoginPage.java
        |   +-- ProductsPage.java
        |   +-- CartPage.java
        |   +-- CheckoutPage.java
        |   +-- ConfirmationPage.java
        +-- utilities
        |   +-- NavbarComponent.java
        |   +-- FooterComponent.java
        |   +-- HeroSectionComponent.java
        |   +-- PromoBannerComponent.java
        |   +-- ProductCardComponent.java
        +-- tests
            +-- HomeTests.java
            +-- LoginTests.java
            +-- ProductTests.java
            +-- CartTests.java
            +-- CheckoutTests.java
```

## Design Notes

- `utilities` contains shared UI blocks that appear across pages.
- `BaseTest.initializeUtilities()` creates shared utilities for tests that need them.
- Pages import utilities only when the shared block appears on that page.
- `CheckoutPage` handles the checkout form only.
- `ConfirmationPage` handles the final order confirmation screen separately.
- Demo user credentials and default product data are kept in `BaseTest` because the required scope is `demouser` only.

## Wait Strategy

The framework uses explicit waits before every important interaction:

- wait for visibility before reading or typing
- wait for clickability before clicking
- wait for URL changes after navigation
- wait for expected text after cart, product, login, and checkout state changes

There is no `Thread.sleep`.

## Run

```bash
mvn clean test
```

Optional:

```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
mvn clean test -Dheadless=true
```
