package com.secangkirkopipanas.cstest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;

public class URLChecker {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private HttpGet httpGet;
    private CloseableHttpResponse response;

    public CloseableHttpResponse getResponse(String url) throws IOException {
        httpGet = new HttpGet(url);
        return httpclient.execute(httpGet);
    }

    public String healthStatus(String url, int interval, int maxtries, String strChecker) throws IOException {

        int statusCode = 0;
        long responseTime = 0;
        String statusStr = "";
        String responseBody = "";

        StringBuilder sBuilder = new StringBuilder();

        logger.debug("Checking {}", url);
        for (int i = 0; i < maxtries; i++) {
            try {
                long startTime = System.currentTimeMillis();
                response = getResponse(url);
                long endTime = System.currentTimeMillis();
                statusCode = response.getStatusLine().getStatusCode();
                responseTime = endTime - startTime;
            } catch (UnknownHostException exp) {
                statusCode = 404;
            }

            if (statusCode == 200) {
                byte[] responseBodyByte = EntityUtils.toByteArray(response.getEntity());
                responseBody = new String(responseBodyByte);
                logger.debug("Status Code: {}", statusCode);
                logger.debug("Response Time: {}", responseTime + "ms");

                if (strChecker != null && !strChecker.trim().equalsIgnoreCase("")) {
                    if (responseBody.contains(strChecker)) {
                        statusStr = "GREEN";
                    } else {
                        statusStr = "BLUE";
                    }
                } else {
                    statusStr = "GREEN";
                }

            } else if (statusCode == 401) {
                statusStr = "UNAUTHORIZED";
            } else if (statusCode == 403) {
                statusStr = "FORBIDDEN";
            } else {
                statusStr = "NOT AVAILABLE";
            }

            sBuilder = sBuilder
                    .append(Instant.now().toEpochMilli())
                    .append(",")
                    .append(statusStr)
                    .append(",")
                    .append(url)
                    .append(",")
                    .append(responseTime + "ms")
                    .append("\n");

            if (statusCode != 200 && statusCode != 401 && statusCode != 403) {
                try {
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException exp) {
                    System.err.println("Unexpected exception: " + exp.getMessage());
                    System.exit(3);
                }

            } else {
                break;
            }
        }

        response.close();
        return sBuilder.toString();
    }

    public String healthStatus(String url, int interval, int maxtries) throws IOException {
        return healthStatus(url, interval, maxtries, null);
    }

    public String healthStatus(List<String> urls, int interval, int maxtries, String strChecker) throws IOException {
        String output = "";
        for (String url : urls) {
            String healthStatus = healthStatus(url, interval, maxtries, strChecker);
            output += healthStatus;
        }
        return output;
    }

    public String healthStatus(List<String> urls, int interval, int maxtries) throws IOException {
        return healthStatus(urls, interval, maxtries, null);
    }

    public String healthStatus(List<String> urls) throws IOException {
        return healthStatus(urls, 1, 3);
    }

}
