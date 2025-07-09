# API Automation Framework

A robust Java-based REST API test automation framework using **TestNG**, **Rest-Assured**, **Allure**, and **WireMock** for optional API mocking.

---

## Tech Stack

- **Java 17+**
- **TestNG** – Test framework
- **Rest-Assured** – API interaction
- **Allure** – Rich reporting
- **Maven** – Build and dependency management
- **WireMock** – Optional mock server
- **JSON Schema Validator** – Response contract validation

---

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Andani2020/api-automation-framework.git
   cd api-automation-framework

2. **Install JDK 17+**
    Ensure you have JDK 17 or higher installed. You can check your Java version with:
    ```bash
    java -version
    ```
    If you need to install JDK, follow the instructions for your operating system.
    
    **Note:**
    Make sure JAVA_HOME is set to your JDK 17+ installation.

3. **Install Maven**
   Ensure you have Maven installed. You can check your Maven version with:
   ```bash
   mvn -v
   ```
   If Maven is not installed, follow the [Maven installation guide](https://maven.apache.org/install.html).
4. **Install Allure (for reporting)**
   Follow the [Allure installation guide](https://docs.qameta.io/allure/#_installing_a_commandline) to install Allure CLI.
   Cornfirm installation by running:
   ```bash
     allure --version
     ```
5. **Install WireMock (optional)**
   If you want to use WireMock for mocking APIs, follow the [WireMock installation guide](http://wiremock.org/docs/running-standalone/).
   You can run WireMock as a standalone server or as part of your tests.

6. **Run Tests**
   To run the tests, execute the following command:
   ```bash
   mvn clean test
   ```
   This will compile the code, run the tests, and generate Allure reports.  
7. **View Allure Reports**
   After running the tests, you can generate and view the Allure report with:
   ```bash
   mvn allure:serve
   ```
   This will start a local server and open the report in your default web browser.
8. **View WireMock Logs (if used)**
   If you are using WireMock, you can view the logs in the `wiremock/logs` directory to see the requests and responses.

## Assumptions & Limitations
   ***API Key Required: Real API tests require a valid key for x-api-key in headers.***

   ***Internet Required: Tests run against a live API unless using a mock fallback.***

   ***Image IDs are dynamic: Some endpoints may fail if the image_id no longer exists.***

   ***Test Data Not Persistent: Votes created via POST are not always retrievable unless API supports it reliably.***

   ***Mock Server not yet preconfigured: WireMock support is planned but not implemented in this version.***

        
