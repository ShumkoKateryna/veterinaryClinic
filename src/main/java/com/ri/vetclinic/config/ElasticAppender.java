package com.ri.vetclinic.config;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Setter
@Getter
public class ElasticAppender extends AppenderBase<ILoggingEvent> {

    private String elasticUrl;
    private String username;
    private String password;

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;


    @Override
    public void start() {
        super.start();
        httpClient = HttpClients.createDefault();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void stop() {
        super.stop();
        try {
            httpClient.close();
        } catch (Exception ignored) {}
    }
    @Override
    protected void append(ILoggingEvent event) {
        try {
            LogEntry entry = new LogEntry(event);
            String json = objectMapper.writeValueAsString(entry);

            String dateIndex = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
            String fullUrl = elasticUrl.replace("/your-index/", "/spring-logs-" + dateIndex + "/");

            HttpPost post = new HttpPost(fullUrl);
            post.setHeader("Content-Type", "application/json");

            if (username != null && password != null) {
                String auth = Base64.getEncoder()
                        .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
                post.setHeader("Authorization", "Basic " + auth);
            }

            post.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
            httpClient.execute(post).close();

        } catch (Exception e) {
            System.err.println("Error sending log to Elasticsearch: " + e.getMessage());
        }
    }

    public static class LogEntry {
        public String timestamp;
        public String level;
        public String thread;
        public String logger;
        public String message;

        public LogEntry(ILoggingEvent event) {
            this.timestamp = new Date(event.getTimeStamp()).toString();
            this.level = event.getLevel().toString();
            this.thread = event.getThreadName();
            this.logger = event.getLoggerName();
            this.message = event.getFormattedMessage();
        }
    }
}