package com.secangkirkopipanas.cstest.test.util;

import com.secangkirkopipanas.cstest.util.DateUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DateUtilTest {

    private Long timestamp = new Long("1522833815000");
    private String timezone = "Asia/Singapore";

    private static String appHome;

    @BeforeClass
    public static void init() {
        Path currentRelativePath = Paths.get("");
        appHome = currentRelativePath.toAbsolutePath().toString();
        System.setProperty("app.home", appHome);
    }

    @Test
    public void testFormat1() throws IOException {
        Assert.assertEquals("04/04/2018", DateUtil.format(timestamp, "dd/MM/yyyy"));
    }

    @Test
    public void testFormat2() throws IOException {
        Assert.assertEquals("04/04/2018 17:23", DateUtil.format(timestamp, "dd/MM/yyyy HH:mm", timezone));
    }

}
