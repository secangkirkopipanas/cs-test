package com.secangkirkopipanas.cstest.test;

import com.secangkirkopipanas.cstest.URLChecker;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class URLCheckerTest {

    private URLChecker urlChecker = new URLChecker();

    private String url1 = "http://www.google.com";
    private String url2 = "http://www.yahoo.com";

    @Test
    public void testGetResponse() throws IOException {
        CloseableHttpResponse httpResponse = urlChecker.getResponse(url1);
        Assert.assertNotNull(httpResponse);
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        Assert.assertTrue(EntityUtils.toByteArray(httpResponse.getEntity()).length > 0);
        httpResponse.close();
    }

    @Test
    public void testHealthStatus() throws IOException, InterruptedException {
        // URLChecker.healthStatus() test with single URL
        Assert.assertTrue(urlChecker.healthStatus(url1, 1, 1).contains("GREEN"));
        Assert.assertTrue(urlChecker.healthStatus(url1, 1, 1, "Component Status: GREEN").contains("BLUE"));
        Assert.assertTrue(urlChecker.healthStatus(url1, 1, 1, "<style>").contains("GREEN"));

        // URLChecker.healthStatus() test with multiple URLs
        List<String> urls = new ArrayList<>();
        urls.add(url1);
        urls.add(url2);
        Assert.assertTrue(urlChecker.healthStatus(urls, 1, 1).contains("GREEN,http://www.yahoo.com"));
        Assert.assertTrue(urlChecker.healthStatus(urls, 1, 1).contains("GREEN,http://www.google.com"));
        Assert.assertTrue(urlChecker.healthStatus(urls, 1, 1, "Component Status: GREEN").contains("BLUE,http://www.yahoo.com"));
        Assert.assertTrue(urlChecker.healthStatus(urls, 1, 1, "Component Status: GREEN").contains("BLUE,http://www.google.com"));
        Assert.assertTrue(urlChecker.healthStatus(urls, 1, 1, "<style>").contains("GREEN,http://www.yahoo.com"));
        Assert.assertTrue(urlChecker.healthStatus(urls, 1, 1, "<style>").contains("GREEN,http://www.google.com"));
    }
}
