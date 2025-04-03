# book-api

## Overview

A simple Book API to demonstrate exception handling best practice in Spring Boot.

With Book API, you can observe what each progression of exception handling looks like, from no exception handling, to 
exception handling with a try-catch block, to exception handling via a global exception handler.

## Exception Handling

Exception handling is an essential part of writing robust and maintainable software. Try-catch blocks offer a starting 
point for exception handling, however, as your application grows, try-catch blocks can lead to code duplication and 
inconsistencies in error responses. Spring Boot offers a powerful solution to these challenges in the form of a 
global exception handler.

A global exception handler acts as a centralised error management component, intercepting exceptions thrown within the 
application's request-response cycle. This centralised approach offers several key benefits:
* **Eliminates Code Duplication**: Consolidating error handling in one place prevents the need to repeat the same 
try-catch blocks across multiple controllers or service classes.
* **Consistent Error Responses**: Ensures that all exceptions, regardless of their origin, are mapped to appropriate 
HTTP status codes and formatted error messages, providing a predictable experience for clients consuming your API.
* **Simplified Maintenance**: A global exception handler makes it easier to update error messages, introduce new 
exception types and modify your overall error response approach in a single location.

## Book API Explained

With Book API, you perform the simple operation of fetching a book from a GET endpoint using a bookId.

Book API contains an in-memory database, preloaded only with 1 book (see data.sql).

Hitting a getBook endpoint with a bookId that does not exist will throw a BookNotFoundException.

### BookControllers

There are different versions of BookController, each representing a different level of sophistication in
exception handling.

| Controller       | Description                                                                                                                                                                 |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| BookControllerV1 | Features 1 getBook endpoint and allows us to see an API error response with no exception handling.                                                                          |
| BookControllerV2 | 1 getBook endpoint with a try-catch block. Allows us to see an API error response with try-catch exception handling.                                                        |
| BookControllerV3 | 1 getBook endpoint with a try-catch block that has 2 catches. Illustrates that if we use the try-catch approach, we will usually have to catch more than one exception.     |
| BookControllerV4 | 2 endpoints with try-catch blocks. Vividly illustrates the problem that starts to emerge with the try-catch approach once our application starts to grow: code duplication. |
| BookControllerV5 | Shows what our endpoints can look like when using global exception handling - no try-catch blocks, no duplication.                                                          |

## Using Book API

### Prerequisites

* Maven installed.
* Java 21 installed.
* An API client tool - such as HTTPie or Bruno - installed.

### Running The Service

Run Book API using your IDE or ```mvn spring-boot:run```.

### Scenarios

#### No exception handling

Make a request to GET ```/v1/book/{bookId}``` endpoint with a bookId that does not exist.

#### Exception handling with a try-catch block

Make a request to GET ```/v2/book/{bookId}``` endpoint with a bookId that does not exist.

#### Exception handling via a global exception handler

Un-comment the following line in application.properties.

```spring.profiles.active=global-exception-handling```

Re-run the service.

Make a request to GET ```/v3/book/{bookId}``` endpoint with a bookId that does not exist.

## Global Exception Handler Implementation

The global exception handler implementation used here is compromised of several components, delineated in the table
below.

| Class                  | Description                                                                                                  |
|------------------------|--------------------------------------------------------------------------------------------------------------|
| ErrorType              | An enum class featuring API error types in a standardised format.                                            |
| ErrorDetails           | An error response body object based on a proposed RFC standard: https://www.rfc-editor.org/rfc/rfc9457.html. |
| BaseException          | An abstract class which defines the contract for our custom exceptions.                                      |
| GlobalExceptionHandler | All exceptions thrown during the request-response cycle of any controller are handled in this class.         |
| BookControllerV5       | Our endpoints built in a global exception handler concordant manner i.e., no cumbersome try-catch blocks.    |

Some notable details to highlight regarding the implementation:
* ErrorDetails populates itself with fields from custom exceptions. Custom exceptions are populated
with fields from ErrorType. When we handle unexpected errors in the GlobalExceptionHandler, ErrorDetails is populated 
with fields from ErrorType directly.
* In some GlobalExceptionHandler class implementations, every custom exception has a handler. In this implementation, 
the use of BaseException, and the standardisation it confers upon custom exceptions, means that we can handle all 
custom exceptions with a single handler, reducing duplication.