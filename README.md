# GitHub Repository Finder

This project provides a RESTful API to retrieve GitHub repositories and their associated branches with commits.

## Table of Contents

- [Introduction](#introduction)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Error Handling](#error-handling)
- [Configuration](#configuration)
- [Contributors](#contributors)

## Introduction

The GitHub Repository Finder is a Spring Boot application that utilizes GitHub API to fetch user repositories which are not forks with branches, and commits. It allows you to retrieve information in JSON format.

## Endpoints

The following endpoints are available:

- `GET /api/find`: Retrieves user repositories and their associated branches with commits.

## Installation

To run the application, follow these steps:

1. Clone this repository.
2. Build the project using Maven: `mvn clean install`.
3. Run the application: `mvn spring-boot:run`.

## Usage

You can use your preferred API client (such as Postman) to interact with the application. Send a `GET` request to `/api/find` with the following headers:

- `Accept: application/json` - to request JSON response

Additionally, provide the GitHub username as the request body.

Example request using `curl`:

curl -X GET
-H "Accept: application/json"
-d "username=your-github-username"
http://localhost:8080/api/find

## Error Handling

The application provides custom error handling for different scenarios:

- If the requested user is not found, it returns a `404 Not Found` response.
- If the requested response format is not acceptable (not JSON), it returns a `406 Not Acceptable` response.

## Configuration

The application uses Spring Boot and RestTemplate to interact with GitHub API. The configuration is provided in the `RepoConfig` class.

## Contributors

- plafulCloud
  
Feel free to contribute to this project by adding new features, improving error handling, and enhancing the documentation.


