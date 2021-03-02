# Crawler Microservice using Spring Boot


### Running application in commandLine

two parameters required 

1. url
2. flag (true - internal links or false - external links (default))


### Prerequisites

Following software should already be installed on workstation for testing

```
JDK1.8 or higher
Maven

```

### Buidling project

git clone 

```
mvn clean package
```

Once the command is sucessful it will generate **target/crawler-0.0.1-SNAPSHOT.jar** file

### Running the Appication

Following command will run application from folder where Attached jar downloaded to.

```
java -jar crawler-0.0.1-SNAPSHOT.jar
```


Project Structure

src/main/java

CrawlerApplication
CrawlerConfig 
CrawlerService
Webpage

src/main/resources

application.properties
logback.xml - suppress log console info

src/test/java

CrawlerApplicationTests - htmlunit test cases for crawler sites and skipped sites

