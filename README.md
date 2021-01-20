[![Build Status](https://travis-ci.org/AOEpeople/Tagging.svg?branch=master)](https://travis-ci.org/AOEpeople/Tagging)

# **Controlled drinking**
This app is a back end of Controlled drinking app. This app's primary function is to collect data from the mobile app and store them to a database.

Technologies used:
 - Spring Boot (2.3.1.RELEASE)
 - PostgresSQL
 - Hibernate
 - Vaadin 17
 
## How to run on localhost
If you want to try the app, download it to your device and open in favourite IDE (IntelliJ, NetBeans...). Wait until all maven dependencies are download and then run as a standard java code.

## System requirements:
 - Java 8 or 9
 - Maven 3.2+
 - Gradle 4
-  Node.js (installed automatically by Vaadin)
-  npm (installed automatically by Vaadin)

## Servlet Containers
Spring Boot supports the following embedded servlet containers:
- Tomcat 8.5 - version 3.1
- Jetty 9.4 - version 3.1
- Undertow 1.4 - version 3.1


## Deployment

Deployment for every cloud technology is a little different. This tutorial may contain your technology.
https://vaadin.com/learn/tutorials/cloud-deployment

## Deploy on Heroku
For a testing app on server, I use Heroku. For this reason, I can handle a manual on how to deploy on the Heroku.
> !!! app_name replace by the name of your app !!! 

1.  Install Heroku CLI  [The Heroku CLI | Heroku Dev Center](https://devcenter.heroku.com/articles/heroku-cli)
2.  Login to herokul. From now use command line.
	```text
	heroku login
	```
3.  Create app. 
	```text
	heroku create <app_name>
	```
4.  Set plugin correctly in pom.xml. This app contains all necessary dependencies, but it needs to be set correctly. Replace in plugin heroku-maven-plugin tag  < appName > by the name of your app.

	```text
	<plugin>
	   <groupId>com.heroku.sdk</groupId>
	   <artifactId>heroku-maven-plugin</artifactId>
	   <version>2.0.13</version>
	   <configuration>
	       <appName>app_name</appName>
	       <processTypes>
	   		<web>java $JAVA_OPTS -jar target/ControlledDrinking-0.9.4-SNAPSHOT.jar --server.port=$PORT</web>
	       </processTypes>
	   </configuration>
	</plugin>
	```
5.  Deploy by the following command from the project directory.
	```text
	mvn clean package -Pproduction heroku:deploy
	```
6. Go to the browser and navigate to _<app_name>.herokuapp.com_
