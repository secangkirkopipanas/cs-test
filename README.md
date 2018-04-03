# Test Code for CreditSuisse #

## Description ##

This application checks the availability of the given list of URL.

## Version ##

1.0-SNAPSHOT

## How to use ##

Execute this command to start the application:
* In Unix
   ```
   $JAVA_HOME/bin/java -jar cs-test-1.0-SNAPSHOT.jar com.secangkirkopipanas.cstest.Application -u <comma-separated URLs> -i <interval time in seconds> -m <maximum tries>
   ```
* In Windows
   ```
   %JAVA_HOME%\bin\java.exe -jar cs-test-1.0-SNAPSHOT.jar com.secangkirkopipanas.cstest.Application -u <comma-separated URLs> -i <interval time in seconds> -m <maximum tries>
   ```

   ### Available options ###
   * -u <value>, --urls <value>     : Comma-separated URLs will be checked by the application
   * -i <value>, --interval <value> : Interval time in seconds
   * -m <value>, --maxtries <value> : Maximum number of tries
