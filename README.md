# HIIT Workout Builder


ABOUT

This application allows users to create and be led through customized high-intensity interval training (HIIT) sessions. HIIT Workout Builder is under active development and new features are being added regularly. Please read below for information on running the application as well as technical details.


SETUP

Visit https://hiit-builder.herokuapp.com/ to access the most recent web release of this application. Note that the application is currently open for free and public use, so anything you add will be accessible to other users.

To use the application locally, set your IDE to compile the application using Java Development Kit (JDK) 11. You may need to have Gradle installed if you are not compiling the application from IntelliJ IDEA or Spring Tool Suite. You will need to set up your own application.properties file with the database settings you wish to use. Fork and clone the Git repository to your IDE and run the application.


TECHNICAL INFORMATION

This is a Spring Boot web application with Gradle handling the build. It is primarily coded in Java with JavaScript functions handling certain key features. The application follows a Model-View-Controller design pattern and uses Thymeleaf, HTML5, and CSS to render views. SQL is used with Hibernate to implement the persistent database.
