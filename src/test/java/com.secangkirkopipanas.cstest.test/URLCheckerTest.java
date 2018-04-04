package com.secangkirkopipanas.cstest.test;

import com.secangkirkopipanas.cstest.URLChecker;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class URLCheckerTest {

    private URLChecker urlChecker = new URLChecker();

    private String url = "http://www.google.com";

    @Test
    public void testGetResponse() throws IOException {
        CloseableHttpResponse httpResponse = urlChecker.getResponse(url);
        Assert.assertNotNull(httpResponse);
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        Assert.assertTrue(EntityUtils.toByteArray(httpResponse.getEntity()).length > 0);
        httpResponse.close();
    }

    @Test
    public void testHealthStatus() throws IOException, InterruptedException {
        // URLChecker.healthStatus() test with single URL
        Assert.assertTrue(urlChecker.healthStatus(url, 1, 1).contains("GREEN"));
        Assert.assertTrue(urlChecker.healthStatus(url, 1, 1, "Component Status: GREEN").contains("BLUE"));
        Assert.assertTrue(urlChecker.healthStatus(url, 1, 1, "<style>").contains("GREEN"));

        // FIXME
        // URLChecker.healthStatus() test with multiple URLs
        // Assert.assertTrue(urlChecker.healthStatus(url, 1, 1).contains("GREEN"));
        // Assert.assertTrue(urlChecker.healthStatus(url, 1, 1, "Component Status: GREEN").contains("BLUE"));
        // Assert.assertTrue(urlChecker.healthStatus(url, 1, 1, "<style>").contains("GREEN"));
    }
}
