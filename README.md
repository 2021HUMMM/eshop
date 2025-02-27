
# MODULE 1

## Reflection 1: Clean & secure coding reflection

### 1. Meaningful Names
- The code has already implement meaningful names, so that each variable, function, or class will be understood easily by readers

### 2. Function and Data Structures
- The functions in the controller and service are well-organized and follow the single responsibility principle.
### 3. Comments
- The code already have plenty of comments (on necessary parts) so that readers will know what's going on in that part of code

### 4. Error Handling
- **Controller**: currently, there is no error handling yet, e.g., for when a product is not found. I will fix this so that the program will show an error message or redirect the users.
- **Repository**: `findById()` returns `null` when a product is not found. (up until now, i decided to keep returning it to null to keep the simplicity, but still handles null case).

### 5. Secure Coding
- **Input Validation**: i have not yet implement input validation. It's important to validate inputs in the controller, especially for fields like `productName` and `productQuantity`. (UPDATE: already implemented)
- **SQL Injection Protection**: My current code does not interact with a database, so SQL injection is not yet a concern.
- **Sensitive Data** : My code currently doesn't handle sensitive data. I'll make sure to encrypt sensitive data in the future (like passwords) and use secure communication protocols like HTTPS to protect data in transit.

### Conclusion
The code already works fine, but there are few aspects that can be improved, especially in the security part. I will fix this next time.

## Reflection 2: 
- After writing unit tests, I feel confident that my code behaves as expected. However, writing tests can sometimes be time-consuming, especially when ensuring that all edge cases are covered.
The number of unit tests in a class depends on the complexity of the class being tested. Generally, each method should have at least one test case, and additional tests should be written for different edge cases and failure scenarios.
To ensure our unit tests are sufficient, we can use code coverage tools. These tools measure how much of the source code is executed by the tests. A high code coverage is ideal, 
but 100% coverage does not guarantee a bug-free program. Some bugs arise due to logical errors, race conditions, or incorrect assumptions, which might not be caught by simple coverage metrics.

- If we create a new functional test suite with the same setup procedures and instance variables as previous test suites, it violates the DRY (Don't Repeat Yourself) principle, leading to redundant code that is harder to maintain. Duplicating setup logic across multiple classes 
increases the risk of inconsistencies and makes updates more time-consuming. Additionally, hardcoded selectors and test data can make the code brittle and harder to update. To improve cleanliness, extract common setup and teardown
logic into a base test class or utility methods, and externalize test data (e.g., using properties files). This approach enhances maintainability and ensures consistency across test suites.

# MODULE 2

- deployment link: https://dull-jori-2021hummm-1a08dec6.koyeb.app/
- code coverage:

![code-coverage](https://i.ibb.co.com/8DXYGQ0J/code-coverage.png)

## Reflection

### 1. Problems & Solutions:
- i got notified for a few problems regarding code quality. most of them are because of the naming convention, which are supposed to be camel case.
before fixing, i was a little inconsistent between snake case and camel case. i fix the problem by simply renaming my tests to camel case. another problem that i
encounter was about unused import. but i simply left it because it was from the tutorial, and it might be used in the future.
### 2. CI/CD
- My project already aligns with the principles of CI/CD. I have implemented Continuous Integration (CI) using GitHub Actions, which automatically triggers workflows defined in .github/workflows whenever there is a push or pull request. 
As part of the CI process, a ci.yml workflow runs all unit tests and performs a PMD scan to analyze code quality and suggest best practices. For Continuous Deployment (CD), I have integrated Koyeb, which ensures that every change pushed 
to the repository is automatically deployed. This streamlined workflow enhances testing, code validation, and deployment efficiency.

# MODULE 3

## Reflection
### 1. Principles Applied in My Project
I applied the SOLID principles to ensure my project is structured, maintainable, and scalable. Here’s how each principle is implemented:

- SRP (Single Responsibility Principle): Each class in my project has only one responsibility and one reason to change:

    - CarController: Handles HTTP requests and user interactions.
    - CarServiceImpl: Contains business logic, such as validation and processing.
    - CarRepository: Manages data storage and retrieval.
  
  By following SRP, if I need to change how cars are stored, I only modify CarRepository without affecting CarController or CarServiceImpl.

- OCP (Open-Closed Principle): My project is open for extension but closed for modification, meaning I can add new features without altering existing code:

    - I use interfaces like CarService instead of hardcoding CarServiceImpl.
    - If I want to introduce a new type of vehicle (e.g., BikeService), I can create a new class without modifying CarServiceImpl. 
  
  This ensures the project can scale without breaking existing functionality.

- LSP (Liskov Substitution Principle): I fixed an LSP violation where CarController previously extended ProductController.

    - Before Fix: CarController extends ProductController, even though a car is a type of product, the controller logic was different.
    - After Fix: CarController is now independent and only handles car-related logic, ensuring it can be replaced or extended without issues.
  
  Now, if I introduce a new BikeController, it won’t be forced to inherit unrelated methods from ProductController.

- ISP (Interface Segregation Principle): Right now, all methods in CarService are used across the system, so I haven’t separated the interface yet.

    - However, if in the future I introduce a ReadOnlyCarService (which only retrieves cars), I might need to separate the findAll() and findById() methods into a dedicated retrieval interface.
    - This prevents forcing classes to implement unnecessary methods.
  
  For now, since every method is used, keeping CarService as a single interface is acceptable.

- DIP (Dependency Inversion Principle): Previously, CarController depended on CarServiceImpl directly, which violated DIP.

    - Before Fix: CarController had @Autowired private CarServiceImpl carService; (tight coupling).
    - After Fix: Now, it depends on CarService (an abstraction), making it easier to swap implementations. 
  
  Now, if I need to use a MockCarService for testing, I can inject it without modifying CarController. This keeps my high-level modules (controllers) independent of low-level modules (implementations).

### 2. Advantages of Applying SOLID
Applying SOLID makes my project scalable, maintainable, and testable:
- Easier to extend: If I add a BikeService, I don’t have to modify CarService, thanks to OCP.
- Better testability: Since CarController depends on CarService, I can mock it in unit tests, following DIP.
- More maintainable: Each class does only one thing, so debugging is easier, following SRP.

For example, if I change how cars are stored, I only need to update CarRepository without affecting other parts of the system.

### 3. Disadvantages of Not Applying SOLID
Without SOLID, my project would become hard to maintain and modify:

- Tightly coupled code: If CarController depended on CarServiceImpl, switching to another service would require modifying multiple files (violates DIP).
- Difficult to scale: If all vehicles shared a single VehicleService, adding new vehicle types would require modifying the existing class (violates OCP).
- Hard to test: If CarService had too many unrelated methods, mocking it for unit tests would be complicated (violates ISP).
- 
For example, if CarController were tightly coupled to CarServiceImpl, changing the implementation would break the entire project.

