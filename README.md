#Spring Fast Starter - H2/MySQL Version

This is simple starter web applicationb based on spring framework (together with spring boot) to boost your productivity
    while starting up with a CRUD based applications requiring basic setup like

- login page with users stored in the database
- registration page open to all users
- admin control panel
- role based configuration
- simple secured resources available only after login (through web based html form)

In the future possible improvements will include:

- login with 3rd party providers (Twitter, Facebook, LinkedIn)

## More or less fancy stuff used:

- incorporating **spring** and **spring boot** goodness
- view templates based on **Thymeleaf** and **Twitter Bootstrap**
- common view dependencies resolved with **WebJars**
- **'Remember Me'** persistent login cookie functionality
- separate profiles for production and development
- unit, integration and acceptance tests (**Selenium** + **Page Objects**)
- **Lombok** for simple POJOs and logging functionality
- **Gravatar** service integration for user profile images
- **H2** and **MySQL** databases support out of the box
- Development mode database populating scripts
- **Liquibase** for database changes management


## Build and startup

To build the application simply execute the following command:

```
./gradlew clean build
```

To run the application execute the command below:

```
java -jar ./build/libs/sfs-mysql-0.0.1-SNAPSHOT.war
```