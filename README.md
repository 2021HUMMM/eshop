
# Reflection 1: Clean & secure coding reflection

## 1. Meaningful Names
- The code has already implement meaningful names, so that each variable, function, or class will be understood easily by readers

## 2. Function and Data Structures
- The functions in the controller and service are well-organized and follow the single responsibility principle.
## 3. Comments
- The code already have plenty of comments (on necessary parts) so that readers will know what's going on in that part of code

## 4. Error Handling
- **Controller**: currently, there is no error handling yet, e.g., for when a product is not found. I will fix this so that the program will show an error message or redirect the users.
- **Repository**: `findById()` returns `null` when a product is not found. I will fix this by, for example, throwing a custom exception like `ProductNotFoundException`.

## 5. Secure Coding
- **Input Validation**: i have not yet implement input validation. It's important to validate inputs in the controller, especially for fields like `productName` and `productQuantity`. (UPDATE: already implemented)
- **SQL Injection Protection**: My current code does not interact with a database, so SQL injection is not yet a concern.
- **Sensitive Data** : My code currently doesn't handle sensitive data. I'll make sure to encrypt sensitive data in the future (like passwords) and use secure communication protocols like HTTPS to protect data in transit.

## Conclusion
The code already works fine, but there are few aspects that can be improved, especially in the security part. I will fix this next time.

# Reflection 2: 
- After writing unit tests, I feel confident that my code behaves as expected. However, writing tests can sometimes be time-consuming, especially when ensuring that all edge cases are covered.
The number of unit tests in a class depends on the complexity of the class being tested. Generally, each method should have at least one test case, and additional tests should be written for different edge cases and failure scenarios.
To ensure our unit tests are sufficient, we can use code coverage tools. These tools measure how much of the source code is executed by the tests. A high code coverage is ideal, 
but 100% coverage does not guarantee a bug-free program. Some bugs arise due to logical errors, race conditions, or incorrect assumptions, which might not be caught by simple coverage metrics.

- If we create a new functional test suite with the same setup procedures and instance variables as previous test suites, it violates the DRY (Don't Repeat Yourself) principle, leading to redundant code that is harder to maintain. Duplicating setup logic across multiple classes 
increases the risk of inconsistencies and makes updates more time-consuming. Additionally, hardcoded selectors and test data can make the code brittle and harder to update. To improve cleanliness, extract common setup and teardown
logic into a base test class or utility methods, and externalize test data (e.g., using properties files). This approach enhances maintainability and ensures consistency across test suites.