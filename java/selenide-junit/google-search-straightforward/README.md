# Simple Google E2E test

## Pre-conditions

* Maven
* Java 1.8
* chromedriver.exe should be present in PATH

## Description

Writing such tests can be usefull on a start of a project,
when you need to test is web ui automation is a good decision for particular project.

There is no report tool for this project, cause it should emulate the 'very start'
of a automation.

This approach is avaible cause of Selenide framework. It uses Element Factory pattern, which allows
to use webelements rightaway, without creating extra classes as it work for Page Factory. Also Selenide
interface makes tests pretty readable by themself:

 ```sh
 $("#lst-ib").setValue("Selenium automates browsers").pressEnter();
 ```

 For even better readability you can store elements in variables:

 ```
 SelenideElement searchInput = $("#lst-ib");
 ```

 And then write even more readable tests:

 ```
 searchInput.setValue("Selenium automates browsers").pressEnter();
 ```

Biggest plus of this approach is you can build tests for most critical parts of the product in few days, and they
will be improving quality of your QA right away.

Minus is that such tests have hardly reusable code, and not very nice reporter, so it can be not very good decision
for long-term automation project.

## Test example

```
    open("http://google.com/ncr");

    $("#lst-ib").setValue("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible).shouldHaveSize(10)
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a")
            .click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));
```

## Components

* [Selenide](http://selenide.org/index.html)
* [JUnit](http://junit.org/junit4/)

## To run tests

Use command

```sh
$ mvn test
```
