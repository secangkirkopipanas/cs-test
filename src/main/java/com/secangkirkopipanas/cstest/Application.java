package com.secangkirkopipanas.cstest;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Main application
 *
 * @author Robertus Lilik Haryanto (robert.djokdja@gmail.com)
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();

    private int interval = 1;
    private int maxtries = 3;

    private URLChecker urlChecker = new URLChecker();

    public Application() {
        super();
        options.addRequiredOption("u", "urls", true, "URLs to be checked");
        options.addOption("i", "interval", true, "Interval time for the checking");
        options.addOption("m", "maxtries", true, "Maximum number of tries");
    }

    private void usage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "$JAVA_HOME/bin/java -jar cs-test-1.0-SNAPSHOT-jar-with-dependencies.jar com.secangkirkopipanas.cstest.Application", options);
    }

    public String getHealthStatusOutput(String[] args) {

        List<String> urlAsList = null;

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);

            String urls = line.getOptionValue("u");
            urlAsList = Arrays.asList(urls.split("\\s*,\\s*"));

            if (line.hasOption('i')) {
                interval = Integer.parseInt(line.getOptionValue("i"));
            }
            if (line.hasOption('m')) {
                maxtries = Integer.parseInt(line.getOptionValue("m"));
            }

            if (logger.isDebugEnabled()) {
                logger.debug("URLs     : {}", urlAsList);
                logger.debug("Interval : {}", interval);
                logger.debug("Max Tries: {}", maxtries);
            }

            return urlChecker.healthStatus(urlAsList);

        } catch (ParseException exp) {
            usage();
        } catch (Exception exp) {
            logger.error("Unexpected exception: {}", exp.getMessage());
            System.exit(2);
        }

        return null;
    }

    public static void main(String[] args) {
        Application app = new Application();
        logger.info(app.getHealthStatusOutput(args));
    }

}
