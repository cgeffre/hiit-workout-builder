# HIIT Workout Builder


ABOUT

This application allows you to create and be led through customized high-intensityh interval training (HIIT) sessions. The application is under active development and new features are being added regularly. Please read below for information on setting up and running the application as well as technical details.


SETUP

Set your IDE to compile the application using Java Development Kit (JDK) 11. You may need to have Gradle installed if you are not compiling the application from IntelliJ IDEA or Spring Tool Suite. Fork and clone the Git repository to your IDE and then set up a local MySQL server. The server and schema settings should be configured in MySQL Workbench to:
- Port: 3306
- Title: hiit-workout-builder
- Username: hiit-workout-builder
- Password: localadmin
- Schema Privileges: Select All

For MySQL Workbench version 8.0, the username, password, and schema privileges can be set in Server > Users and Privileges. If you wish to use different database settings, you will need to update /src/main/resources/application.properties in the hiit-workout-builder source code to reflect your choices.

You should now able to run the application from your IDE and access it on your local server.


TECHNICAL INFORMATION

This is a Spring Boot web application with Gradle handling the build. It is primarily coded in Java along with JavaScript functions handling certain features. The application uses Thymeleaf, HTML5, and CSS on the frontend.
