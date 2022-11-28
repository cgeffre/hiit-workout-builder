# HIIT Workout Builder


ABOUT

This application allows users to create and be led through customized high-intensity interval training (HIIT) sessions. HIIT Workout Builder is under active development and new features are being added regularly. Please read below for information on running the application as well as technical details.


SETUP

To use the application locally, clone the repository and set your IDE to compile the application using Java Development Kit (JDK) 11. You may need to have Gradle installed if you are not compiling the application from IntelliJ IDEA or Spring Tool Suite. You will need to set up your own application.properties file in the resources directory with the database settings you wish to use.


TECHNICAL INFORMATION

This is a Spring Boot web application with Gradle handling the build. It is primarily coded in Java with JavaScript functions handling certain key features. The application follows a Model-View-Controller design pattern and uses Thymeleaf, HTML5, and CSS to render views. SQL is used with Hibernate to implement persistence.
