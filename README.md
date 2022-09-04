# JavaDevHomeWork8

## How does it work and what are the main points?
    
This application is a simple crm system, the purpose of which is to learn how to use the Spring Boot framework.

First of all, for the convenience of the user, each page of the website has a navigation bar for easy navigation between pages.

## Entities in the application:
    - Manufacturers
    - Products
    - Users

According to the task in this work, restrictions for users are implemented. All pages except the main page require authentication. On the Manufacturers, Products, Users pages, all system users can view the list of records in the database, but only admin users can save, modify or delete records.

Each record Manufacturers, Products has an individual card with full information and an image. To view, you need to press the "VIEW MORE" button of the selected entry.

If you are logged in with an admin account using the "ADD NEW" button in the navigation bar, you can create a new entry for any of the entities. On the Manufacturers, Products, Users pages, thanks to the buttons next to each entry, you can delete or change an entry in the database.

## Utility Credentials for login:

ADMIN:
* login: admin@com.ua<br>
* password: 1<br>
USER:
* login: user@com.ua<br>
* password: 1<br>

## GitHub repository:
<a href="https://github.com/Vadim-Havrylitse/JavaDevHomeWork8">Vadim-Havrylitse</a>

## Database:
MySQL

## Technologies end services:
JAVA, SQL, MYSQL, SPRING BOOT,
FLYWAY, HIBERNATE, THYMELEAF,
BOOTSTRAP, MAVEN, TOMCAT,
POSTMAN, GIT, AWS, HEROKU

## INSTRUCTION FOT LOCAL INSTANCE:

<li>Clone repository from GitHub.
<li>In file 'system.property' add url, username and password for connect to your DB.</li>
<li>In file 'system.property' set custom server port or delete row 'server.port' and use default port in your computer.</li>
<li>That's all — press "Run".
