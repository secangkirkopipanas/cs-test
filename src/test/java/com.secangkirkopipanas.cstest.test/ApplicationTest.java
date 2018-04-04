package com.secangkirkopipanas.cstest.test;

import com.secangkirkopipanas.cstest.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ApplicationTest {

    private Application app = new Application();
    private static String appHome;

    @BeforeClass
    public static void init() {
        Path currentRelativePath = Paths.get("");
        appHome = currentRelativePath.toAbsolutePath().toString();
        System.setProperty("app.home", appHome);
    }

    @Test
    public void testGetHealthStatusOutput() {
        String[] args1 = new String[] { "-u", "http://www.google.com" };
        Assert.assertTrue(app.getHealthStatusOutput(args1).contains("GREEN,http://www.google.com"));

        String[] args2 = new String[] { "-u", "http://www.yahoo.com", "-i", "1", "-m", "2" };
        Assert.assertTrue(app.getHealthStatusOutput(args2).contains("GREEN,http://www.yahoo.com"));
    }

    @Test
    public void testMain() throws FileNotFoundException {

        String[] args1 = new String[] { "-u", "http://www.google.com" };
        app.main(args1);

        Scanner fileScanner = new Scanner(new FileReader(appHome + "/" + "logs/cs-test.log"));
        StringBuilder sb = new StringBuilder();
        while(fileScanner.hasNext()) {
            sb.append(fileScanner.next());
        }
        fileScanner.close();
        String body = sb.toString();
        System.out.println(body);

        Assert.assertTrue(body.contains("GREEN,http://www.google.com"));
    }

}
