#Spring Fast Starter - MySQL Version

This is simple starter web applicationb based on spring framework (together with spring boot) to boost your productivity
    while starting up with a CRUD based applications requiring basic setup like

- login page with users stored in the database
- registration page open to all users
- admin controll panel
- role based configuration
- simple secured resources available only after login (through web based html form)

In the future possible improvements will include:

- login with 3rd party providers (Twitter, Facebook, LinkedIn)

## Build and startup

To build the application simply execute the following command:

```
./gradlew clean build
```

To run the application execute the command below:

```
java -jar ./build/libs/sfs-mysql-0.0.1-SNAPSHOT.war
```