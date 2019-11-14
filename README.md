# CPlayers - A Case Study

## Problem Statement

Build a system to search for a cricket player, get player statistics, add players to favourite list and recommend most liked/favourite players to user.

## Requirements

### The application needs to fetch cricket players from the following API.
https://www.cricapi.com/

Refer the following URLs to explore more on the cricket player APIs.
https://www.cricapi.com/how-to-use.aspx
https://www.cricapi.com/how-to-use/player-stats-api.aspx
https://www.cricapi.com/how-to-use/player-finder.aspx

### A frontend where the user can register/login to the application, find cricket player, get player statistics, add player to favourite list and view recommended players.
### User can add a player to favourite list and should be able view the favourite list.
### A recommendation engine/service should be able to store all the unique favourite players from all the users and maintain counter for number of users added a particular player into favourite list. 
### An option to view recommended players should be available on frontend. 

## Microservices/Modules
- UserService - should be able to manage user accounts.
- UI (User interface) -  should be able to
   - Search a player by name
   - View player statistics
   - Add a player to favourite list
   - View favourite players
   - View recommended players
- UI should be responsive which can run smoothly on various devices 
- FavouriteService - should be able to store all the favourite players for a user
- PlayerRecommendationService - should be able to store all the unique favourite players from all the users and maintain counter for number of users added a particular player into favourite list.

## Tech Stack
- Spring Boot
- MySQL, MongoDB
- API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

- Building frontend:
   1. Building responsive views:
      - Register/Login
      - Search for a player
      - Player list - populating from external API
      - Build a view to show favourite players
      - Build a view to show recommended players
   2. Using Services to populate these data in views
   3. Stitching these views using Routes and Guards
   4. Making the UI Responsive
   5. E2E and unit tests
   6. Writing CI configuration file
   7. Dockerize the frontend

- Building the UserService
   1. Creating a server in Spring Boot to facilitate user registration and login using JWT token and MySQL
   2. Writing swagger documentation
   3. Unit Testing
   4. Write CI Configuration
   5. Dockerize the application
   6. Write docker-compose file to build both frontend and backend application

- Create an API Gateway which can serve UI and API Request from same host

- Building the Favourite Service
   1. Building a server in Spring Boot to facilitate CRUD operation over favourite players stored in MongoDB
   2. Writing Swagger Documentation
   3. Build a Producer for RabbitMQ which
      i. Produces events like what user added to favourite list
   4. Write Test Cases
   5. Write CI Configuration
   6. Dockerize the application
   7. Update the docker-compose

- Building the PlayerRecommendationService
   1. Building a Consumer for RabbitMQ
      - i. On a new event generated Update the recommendations in the system. Store the recommendation list in MongoDB.
      - ii. Maintain list of unique recommended players based on what user added into favourite list and keep counter for number of users added a particular player into favourite list
   2. Build an API to get Recommendations
   3. Writing Swagger Documentation
   4. Write Test Cases
   5. Write CI Configuration
   6. Dockerize the application
   7. Update the docker-compose
   8. Update the API Gateway

- Create Eureka server and make other services as client

- Demonstrate the entire application
