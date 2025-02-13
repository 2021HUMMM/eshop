# Clean & secure coding reflection

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
- **Input Validation**: i have not yet implement input validation. It's important to validate inputs in the controller, especially for fields like `productName` and `productQuantity`.
- **SQL Injection Protection**: My current code does not interact with a database, so SQL injection is not yet a concern.
- **Sensitive Data** : My code currently doesn't handle sensitive data. I'll make sure to encrypt sensitive data in the future (like passwords) and use secure communication protocols like HTTPS to protect data in transit.

## Conclusion
The code already works fine, but there are few aspects that can be improved, especially in the security part. I will fix this next time.