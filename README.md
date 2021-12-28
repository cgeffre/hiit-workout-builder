# HIIT Workout Builder


ABOUT

This application allows you to create and be led through customized high-intensity interval training (HIIT) sessions. The application is under active development and new features are being added regularly. Please read below for information on setting up and running the application as well as technical details.


SETUP

Simply visit https://hiit-builder.herokuapp.com/ to access the most recent web release of this application. Note that the application is currently open for free and public use, so please be respectful when adding exercises and workouts.

To use the application locally, set your IDE to compile the application using Java Development Kit (JDK) 11. You may need to have Gradle installed if you are not compiling the application from IntelliJ IDEA or Spring Tool Suite. Fork and clone the Git repository to your IDE and run the application. It is configured to an external SQL server, so you don't need to set up at a database locally unless you wish to create a private collection of exercises and workouts.


TECHNICAL INFORMATION

This is a Spring Boot web application with Gradle handling the build. It is primarily coded in Java with JavaScript functions handling certain features and the application follows a Model-View-Controller design pattern. The application uses Thymeleaf, HTML5, and CSS on the frontend and a ClearDB SQL server with Hibernate to implement a persistent database.
