package com.automile;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class AutomileConfig {

    static final String BASE_URL = "https://api.automile.se";
    static final String VERSION = "v1";
    static final String AUTH_URL = BASE_URL + "/OAuth2/Token";
    private static final int TIMEOUT = 30;

    private static final RequestConfig config = RequestConfig.custom()
            .setSocketTimeout(TIMEOUT * 1000)
            .setConnectTimeout(TIMEOUT * 1000)
            .setConnectionRequestTimeout(TIMEOUT * 1000)
            .build();

    private static ObjectMapper MAPPER = new ObjectMapper();
    private static CloseableHttpClient HTTP_CLIENT = HttpClients.custom().setDefaultRequestConfig(config).build();

    static {
        MAPPER.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(INDENT_OUTPUT, true);
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static CloseableHttpClient getHttpClient() {
        return HTTP_CLIENT;
    }

    public static void setHttpClient(CloseableHttpClient client) {
        HTTP_CLIENT = client;
    }
}
