# What you need to make this work

You don't need to have maven installed. the `mvnw` in the root directory can be used to run the program.
I've also created a run configuration for IntelliJ.
When you run the program from IntelliJ itll automatically download all the dependancies that are needed.

# Dependancies
So far the dependancies I'm using for this project are:
* [Spring-Boot](https://spring.io/projects/spring-boot)
* [lombok](https://projectlombok.org/) - Autogenerates functions (setters, getters, constructors, etc)
* [mysql-connector](https://www.w3resource.com/mysql/mysql-java-connection.php)
* [h2database](https://h2database.com/html/main.html)

# How the program is structured
## Layout

Maven projects have a really long-winded layout but you only have to know what a few of them do.
* mvnw  / .mvn
    * So you can build and run the program without installing Maven
    * The .mvn directory needs to be there for this to work right

* .idea
    * Holds the configuration for IntelliJ

* src/main/resources
    * This only has application.properties in it. This holds the information needed for `@Autowired` jdbc instances to connect to the database. The creds need to be swapped for the Apex database.

* src/main/java/test
    * This is where tests are supposed to go. I haven't written any..
* src/main/java/com/mcm/hris
    * Non-test code goes here. There are a couple of directories under it for organization.
        * /db_types
            * I put types that map directly to tables in the database here. I name them TableNameData.java so they are easily recognizable in other places in code.
        * /modules
            * This is where modules go, the main parent class `Module.java` lives here as well as all of the classes that use it.
        * /utils
            * Collection of stuff that I found useful



