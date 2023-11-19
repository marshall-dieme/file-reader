
# File Reader Application

This project demonstrate how to save file content (XLSX, XLS, CSV, JSON) in a database using spring boot. For this exemple, I use a dummy class named `MyModel`.
Feel free to customize it based on your data.

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Clone the Repository](#clone-the-repository)
  - [Run the Application](#run-the-application)
- [Customization](#customization)
- [Postman](#postman)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project demonstrates a simple Java program that generates dummy data for the `MyModel` class and exports it to Excel, JSON, and CSV files. The dummy data includes 10 records with unique IDs and placeholder attribute values.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven (for building the project)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/my-model-data-exporter.git
cd file-reader 
```

### Run the application
```bash
mvn clean install
mvn spring-boot:run
```
The application will run on Spring boot default port `8080`

### Customization
Create your file in one of the following format:
    - XLS or XLSX
    - CSV
    - JSON

### Postman
In this example I use postman to sent request to the endpont

#### Open Postman
Launch the postman application on your machine

####  Create a New Request
Click on the "New" button in the top left corner of Postman, and then click on "Request."

#### Enter Request Details
For this one we need the url and the file.

    - Request URL : http://localhost:8080/api/v1/file-reader

Don't forget to add the file as request param

![image](https://github.com/marshall-dieme/file-reader/assets/58795947/ca46e718-ae2c-4b39-99e7-b91acd60c725)


#### Send the Request

Click on the "Send" button to send the request to your Spring Boot application.

#### View Response
Postman will display the response received from your Spring Boot application. Verify that the response matches your expectations.


![image](https://github.com/marshall-dieme/file-reader/assets/58795947/c486f6db-105d-4f9b-91c7-442062b49081)
    

## Contributing
Feel free to contribute to this project by opening issues or submitting pull requests.

