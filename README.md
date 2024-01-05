AP Automation Software - Back End
This is the back end of an AP (Accounts Payable) automation software, designed to streamline and automate the AP process for businesses. This back end is built using Spring Boot, a popular Java framework for building web applications.

Features
RESTful API endpoints for various functions such as user authentication, bill processing, and payment processing
Integration with a MySQL database for storing user and transaction data
Spring Security with JWT (JSON Web Tokens) for authentication and authorization
Implements a modular design with separate modules for user management, approval groups, bill processing, PO (Purchase Order) creation and approval, POR (Purchase Order Request) approval, and payment processing
Getting Started
To run this back end on your local machine, follow these steps:

Clone the repository to your local machine.
Install Java 11 and MySQL.
Set up a MySQL database and create a application.properties file with your database connection information (sample file included in this repository).
Run the application using your preferred Java IDE or by running the command ./mvnw spring-boot:run in the project directory.
API Endpoints
This back end provides several API endpoints for various functions:

/api/auth/signup - register a new user
/api/auth/signin - login and retrieve a JWT token

Contributing
This project is open for contributions. If you would like to contribute, please follow the Contributing Guidelines and submit a pull request.

License
This project is licensed under the MIT License.
