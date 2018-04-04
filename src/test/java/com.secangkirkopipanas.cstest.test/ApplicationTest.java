package com.secangkirkopipanas.cstest.test;

import com.secangkirkopipanas.cstest.Application;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApplicationTest {

    private Application app = new Application();

    @Test
    public void testGetHealthStatusOutput() {
        String[] args1 = new String[] { "-u", "http://www.google.com" };
        Assert.assertTrue(app.getHealthStatusOutput(args1).contains("GREEN,http://www.google.com"));

        String[] args2 = new String[] { "-u", "http://www.yahoo.com", "-i", "1", "-m", "2" };
        Assert.assertTrue(app.getHealthStatusOutput(args2).contains("GREEN,http://www.yahoo.com"));
    }

}
