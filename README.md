**********************************************************************

## 1.Task

This project is an REST API developed in Java using Spring boot.

This project provides options to place odds and retrieves odds for an User

### 1.1 Place Odds

 POST /odds/ Places with below body places odds given User exists and Bet exists
 
 ```
 {
   "betId": 1,
   "userId": "1",
   "odds": "1/10"
 }
 ```
 
### 1.2 Retrieve Odds Placed for the Bet

 GET /odds/{betId} returns the Odds placed for specific betId as an array
 
 ```
    [
         {
             "betId": 1,
             "userId": "1",
             "odds": "1/10"
         }
     ]
 ```
 
 If there are no bets available for the given betId returns 404
 
**********************************************************************

## 2. Technical Details:

### 2.1 Tools&Framework:

   The below are the list of tools and framework used in the project!

* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - for Dependency Management
* [Java](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Java 11 as Programming language
 
### 2.2 Key Features to highlight:

  1.Handled invalid requests with appropriate error message
  2.Added swagger with specific information which will help usage of API
  3.100% unit test coverage for both controller and service class
  

### 2.3 Solution & Assumptions

  1. POST /odds/ places odds given
     If the odds are valid
     If the betId is valid
     If the userId is valid
  2. For Simplicity, Bet entity is defined with only name and not against for/against/draw types
  3. If any one of Odds/Bet/UserId are invalid then an appropriate error message would be returned with HTTP status as 400
  4. /odds/{betId} retrieves all the odds placed against an particular betId
  5. If there are no odds placed for an BetId , then 404 will be returned by end point
  6. This app uses internal H2 volatile memory for storing the data
  7. Since its internal memory and test app, I have maintained credentials in the properties file. 
     Ideally we can make it as configurable and pass it during App Boot.
  8. data.sql placed in the /resources path is responsible for creation and population of entity and data
  9. Maintained status for Bets , Odds and User   
  10.Currently only default profile is used in application.properties but if required this can be extended to use environment specific ones
  
**********************************************************************
 
## 3.Swagger
 
 If this application is being accessed locally,then swagger UI can be accessed at
 
http://localhost:8080/swagger-ui.html#/ 

**********************************************************************

## 4.Run Application

Below command can be used to invoke the application

mvn spring-boot:run

**********************************************************************

## 4.1 Docker

This App is now available as a docker image

To access this image , please use below command

docker pull balasr3/oddsapi

**********************************************************************
