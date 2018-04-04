package com.secangkirkopipanas.cstest;

import com.secangkirkopipanas.cstest.util.DateUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;

public class URLChecker {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private CloseableHttpClient httpclient;
    private CloseableHttpResponse response;

    public URLChecker() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Constants.RESPONSE_TIME_THRESHOLD_IN_MS).build();
        httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public String getStatusString(int statusCode, String strChecker) throws IOException {
        String statusStr;
        if (statusCode == 200) {
            byte[] responseBodyByte = EntityUtils.toByteArray(response.getEntity());
            String responseBody = new String(responseBodyByte);

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

        return statusStr;
    }

    public CloseableHttpResponse getResponse(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return httpclient.execute(httpGet);
    }

    public String healthStatus(String url, int interval, int maxtries, String strChecker) throws IOException, InterruptedException {

        int statusCode;
        long responseTime = 0;
        String statusStr;

        StringBuilder sBuilder = new StringBuilder();

        logger.debug("Checking {}", url);
        for (int i = 0; i < maxtries; i++) {
            try {
                long startTime = System.currentTimeMillis();
                response = getResponse(url);
                long endTime = System.currentTimeMillis();
                statusCode = response.getStatusLine().getStatusCode();
                responseTime = endTime - startTime;

                logger.debug("Status Code: {}", statusCode);
                logger.debug("Response Time: {}{}", responseTime, "ms");

            } catch (UnknownHostException exp) {
                statusCode = 404;
            }

            statusStr = getStatusString(statusCode, strChecker);

            sBuilder = sBuilder
                    .append(DateUtil.format(Instant.now().toEpochMilli(), Constants.DEFAULT_DATE_FORMAT, Constants.TIMEZONE))
                    .append(",")
                    .append(statusStr)
                    .append(",")
                    .append(url)
                    .append(",")
                    .append(responseTime)
                    .append("ms")
                    .append("\n");

            if (statusCode != 200 && statusCode != 401 && statusCode != 403) {
                Thread.sleep((long) interval * 1000);
            } else {
                break;
            }

            if (response != null) response.close();
        }

        return sBuilder.toString();
    }

    public String healthStatus(String url, int interval, int maxtries) throws IOException, InterruptedException {
        return healthStatus(url, interval, maxtries, null);
    }

    public String healthStatus(String url) throws IOException, InterruptedException {
        return healthStatus(url, 1, 3, null);
    }

    public String healthStatus(List<String> urls, int interval, int maxtries, String strChecker) throws IOException, InterruptedException {
        StringBuilder outputBuilder = new StringBuilder();
        for (String url : urls) {
            String healthStatus = healthStatus(url, interval, maxtries, strChecker);
            outputBuilder.append(healthStatus);
        }
        return outputBuilder.toString();
    }

    public String healthStatus(List<String> urls, int interval, int maxtries) throws IOException, InterruptedException {
        return healthStatus(urls, interval, maxtries, null);
    }

    public String healthStatus(List<String> urls) throws IOException, InterruptedException {
        return healthStatus(urls, 1, 3);
    }

}
