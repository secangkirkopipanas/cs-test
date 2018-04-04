# Test Code for CreditSuisse #

## Description ##

This application checks the availability of the given list of URL.

## Version ##

1.0-SNAPSHOT


## How to build ##

###### Prerequisites ######

* [Apache Maven v3.5.3]


###### From Unix command line ######

```
$MVN_HOME/bin/mvn clean package
```


###### From Windows command line ######

```
%MVN_HOME%\bin\mvn clean package
```


## How to use ##

Execute this command to start the application:

* In Unix
   ```
   $JAVA_HOME/bin/java -jar cs-test-<version>-jar-with-dependencies.jar com.secangkirkopipanas.cstest.Application -u <comma-separated URLs> -i <interval time in seconds> -m <maximum tries>
   ```
* In Windows
   ```
   %JAVA_HOME%\bin\java.exe -jar cs-test-<version>-jar-with-dependencies.jar com.secangkirkopipanas.cstest.Application -u <comma-separated URLs> -i <interval time in seconds> -m <maximum tries>
   ```

   ### Available options ###
   * -u <value>, --urls <value>     : Comma-separated URLs will be checked by the application
   * -i <value>, --interval <value> : Interval time in seconds
   * -m <value>, --maxtries <value> : Maximum number of tries


## Other related softwares ##

CI tools: [Travis-CI]

Code quality scanner: [SonarCloud]



[Apache Maven v3.5.3]: http://www-us.apache.org/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.zip
[Travis-CI]: https://travis-ci.org/secangkirkopipanas/cs-test
[SonarCloud]: https://sonarcloud.io/dashboard?id=com.secangkirkopipanas.cstest%3Acs-test