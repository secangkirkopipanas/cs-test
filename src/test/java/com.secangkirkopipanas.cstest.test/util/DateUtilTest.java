package com.secangkirkopipanas.cstest.test.util;

import com.secangkirkopipanas.cstest.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DateUtilTest {

    private Long timestamp = new Long("1522833815000");
    private String timezone = "Asia/Singapore";

    @Test
    public void testFormat1() throws IOException {
        Assert.assertEquals("04/04/2018", DateUtil.format(timestamp, "dd/MM/yyyy"));
    }

    @Test
    public void testFormat2() throws IOException {
        Assert.assertEquals("04/04/2018 17:23", DateUtil.format(timestamp, "dd/MM/yyyy HH:mm", timezone));
    }

}
