### YourFleetManager
Web CRUD Application designed to manage a fleet of vehicles.

## technology used :
 # - Backend :
     - Spring Boot
     - Spring Data JPA
     - JPA & Hibernate
     - Thymeleaf
     - JUnit
     - Mockito
     - Lombok
 
 # - Frontend : 
     - HTML
     - Boostrap CSS
     
 # - Databases : 
     - H2 [ embedded ]
     - MySQL
     - PostgreSQl

 # Launching instruction :

  1.  Download 
  2.  Unzip 
  3.  Go to "SQL" folder and select the appropriate database schema 
  4.  Create database with this schema 
  5.  Load data into the database from the Data folder 
  6.  If you are using PostgreSQL database in application.properties change active profile to "postgresql", if you are using
      MySQL change to "mysql", if you are using H2 change to "h2"
  7.  In file application-mysql.properties, or application-postgresql.properties, or application-h2.properties ( depending on the   
      selected database ) enter correct database url, username and password ( for H2, you do not have to do it ). 
  8.  Run Application 
  9.  log in with username: "user" password: "user" on website "http://localhost:8000/" 
  10. Enjoy the management of your fleet of vehicles :)
  # Database diagram :
  
  ![](SQL/Database-Diagram-Screenshot.jpg)
 
 # Screenshots :
  
  ![](screenshots/screenshot1.jpg)
  
  ![](screenshots/screenshot2.jpg)
  
  ![](screenshots/screenshot3.jpg)
  
