# Spring Boot Start template
## 1. Instructions

### - 1.1 Install needed techs

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-security)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Asciidoctor](https://asciidoctor.org/)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)



### - 1.1.1 Java Development Kit (JDK) 11

#### Linux (Ubuntu and Debian)

Execute the command on terminal `sudo apt-get install openjdk-11-jdk`;

In case the package manager can't find the package, add the repository `sudo add-apt-repository ppa:openjdk-r/ppa`;

update the manager `sudo apt-get update`;

Run the install command again `sudo apt-get install openjdk-11-jdk`;

Test if you are in the correct version with the commands `java --version` or `javac --version`

#### Windows

Download installation of [Open JDK](https://openjdk.java.net/projects/jdk/11/); unzip and install;

Test in CMD or Powershell with the commands `java -version` or `javac --version`

If the answer is "_Not recognized as an internal or external command..._";

1. Locate the Java installation directory

   <div>

   <span dir="">If you didn't change the path during installation, it will look like this </span>`C:\Program Files\Java\jdk17`

   </div>
2. Go to **Control Panel** > **System** > **Advanced System Settings**
3. Click on the **Environment Variables** button
4. Under **System Variables**, click **New**
5. In the **Variable Name** field enter<span dir="">`JAVA_HOME`</span>
6. <span dir="">In the **Variable Value** field, enter your JDK installation path</span>
7. Click **OK** and **Apply Changes** when prompted

All open terminals must be closed and reopened for the settings to work

#### Install by Intellij

On the Project Structure tab, click on add JDK and choose one (Any as long as version 11)
![image](https://user-images.githubusercontent.com/55551413/151868017-6b6eaccd-5a88-4f99-a045-027eeaec20ba.png)

### 1.1.2 Install PostgreSQL

#### Linux (Ubuntu e Debian)

`sudo apt update` e `sudo apt install postgresql postgresql-contrib`

PostgreSQl is installed with peer authentication by default, so to access your terminal, use `sudo su postgres` and `psql` where you will have access to the SQL command console with the default user `postgres`;

**Change Authentication Form**

If you want to change the form of authentication to one with a password, look for the `pg_hba.conf` file which is normally in `/etc/postgresql/14/main/pg_hba.conf` and change the lines 
![image](https://user-images.githubusercontent.com/55551413/151867837-8afacef1-bba3-4007-a0a5-53c3bd805982.png)

from `peer` to `md5` and after that restart the postgres service with `sudo service postgresql restart`;

**Remember to create a password for the user in question before making this modification**

#### Windows

Enter the website [PostgreSQL](https://www.postgresql.org/download/windows/) and select the desired version , must be greater than 9.6 (I recommend 14);

Download and install;

The same change above must be made if you want to authenticate the postgreSQL user with a password;

### 1.1.3 Install Apache Maven 3.6.3 (Java Library Dependencies Manager)

#### Linux (Ubuntu e Debian)

Update the manager `sudo apt-get update`;

Run `sudo apt-get -y install maven`;

Check installation and version with `mvn -version`

#### Windows

Enter the site [Maven](https://maven.apache.org/download.cgi), look for version 3.6.3, download the zip and install;

After that, the environment variables need to be configured;

1. Locate the **Maven** installation directory and the **bin** directory

2. Go to **Control Panel** > **System** > **Advanced System Settings**

3. Click on the **Environment Variables** button

4. Under **System Variables**, click **Path** and **Edit**

5. Click **New** and add the path to the **bin** directory

6. Click **OK** and **Apply Changes** when prompted

Open CMD or Powershell and test with `mvn -v` command

### 1.2 Database config

#### Linux

Create the database using a graphical database management tool like [pgAdmin](https://www.pgadmin.org/)

Or,

Open the terminal and run `sudo su postgres`;

Create the database with `createdb spring-template`;

Ensure there is a user `postgres` with password `postgres` and that PostgreSQL is running on port `5432`

#### Windows

Create the database using a graphical database management tool like [pgAdmin](https://www.pgadmin.org/)

Or, change the authentication method as mentioned in the **PostgreSQL** installation tab on **Linux**;

Open Powershell or CMD and run the commands `psql -U postgres`, enter the password for the user `postgres` and you will have access to the SQL console;

If the answer is "_Not recognized as an internal or external command..._":

1. In the Start menu search bar, type **variables** and click on the option **Edit system environment variables**;
2. Make sure that a window named **System Properties** is open, and the **Advanced** section is selected;
3. Click on the option **Environment variables**;
4. In the **System variables** section, scroll down until you find the option titled **Path**. When you find it, double click on **Path**;
5. A new window will appear with a list of paths. Click on the **New** button, and a field will appear to add one more path to this list;
6. Now, just add the path that leads to the **PostgreSQL/14/bin/** folder (14 is the installed version of PostgreSQL). In my case, that path is **C:\Program Files\PostgreSQL\14\bin\** (since I selected the default installation locations). If you also selected the default locations when performing the installation, then most likely the path leading to the bin folder is the same as the one I wrote above.
7. Finally, just click on **OK** and, when returning to the previous window, click on **Apply**


Run the command `create database spring-template;`;

To check if it was created, run <span dir="">`\l`</span> while still in the console;

#### Obs

If you are using another postgresql user or password, change the fields `spring.datasource.username`, `spring.datasource.password`, `spring.flyway.user` and `spring.flyway.password` on  `src/main/resources/application.properties` accordingly.

### 1.3 Running the application

Download the necessary libraries and build the application inside root directory with the command <span dir="">`mvn clean package -U -e`</span>

Run the application with `mvn spring-boot:run`

## 2. API-Guide

### The guide for the API is served in the application at 
[API Guide](http://localhost:8080/public/docs/api-guide)
