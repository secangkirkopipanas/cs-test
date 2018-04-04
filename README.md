# Test Code for CreditSuisse #

## Author ##

[Robertus Lilik Haryanto]

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

   * -u <value>, --urls <value>     : Comma-separated URLs will be checked by the application (Mandatory)
   * -i <value>, --interval <value> : Interval time in seconds (Optional, default valuen is 1)
   * -m <value>, --maxtries <value> : Maximum number of tries (Optional, default value is 3)

   ### Example ###

   ```
   $JAVA_HOME/bin/java -jar cs-test-1.0-SNAPSHOT-jar-with-dependencies.jar com.secangkirkopipanas.cstest.Application -u http://www.google.com,http://www.yahoo.com -i 1 -m 3
   ```

   or,

   ```
   $JAVA_HOME/bin/java -jar cs-test-1.0-SNAPSHOT-jar-with-dependencies.jar com.secangkirkopipanas.cstest.Application -urls=http://www.google.com,http://www.yahoo.com -interval=1 -maxtries=3
   ````
   
   ### Expected output format ###
   
   ```
   timestamp_epoch,status_check,url,response_time_ms
   ```

   ### Expected output ###
   
   ```
   2018-04-04 22:01:21.165,GREEN,http://www.yahoo.com,524ms
   2018-04-04 22:01:21.273,GREEN,http://www.google.com,107ms
   ```

## Other related softwares ##

CI tools: [Travis-CI]

Code quality scanner: [SonarCloud]



[Robertus Lilik Haryanto]: mailto:robert.djokdja@gmail.com
[Apache Maven v3.5.3]: http://www-us.apache.org/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.zip
[Travis-CI]: https://travis-ci.org/secangkirkopipanas/cs-test
[SonarCloud]: https://sonarcloud.io/dashboard?id=com.secangkirkopipanas.cstest%3Acs-test