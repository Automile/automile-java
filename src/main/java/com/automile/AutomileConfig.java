package com.automile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    static final String BASE_URL_V1 = BASE_URL + "/v1";
    static final String AUTH_URL = BASE_URL + "/OAuth2/Token";
    static final String CREATE_URL = BASE_URL_V1 + "/%s/%s";
    static final String LIST_URL = BASE_URL_V1 + "/%s/%s";
    static final String EDIT_URL = BASE_URL_V1 + "/%s/%s/%s";
    static final String GET_BY_ID_URL = BASE_URL_V1 + "/%s/%s/%s";
    static final String DELETE_URL = BASE_URL_V1 + "/%s/%s/%s";

    private static final int TIMEOUT = 30;

    private static final RequestConfig config = RequestConfig.custom()
            .setSocketTimeout(TIMEOUT * 1000)
            .setConnectTimeout(TIMEOUT * 1000)
            .setConnectionRequestTimeout(TIMEOUT * 1000)
            .build();

    private static ObjectMapper MAPPER = new ObjectMapper();
    private static CloseableHttpClient HTTP_CLIENT = HttpClients.custom().setDefaultRequestConfig(config).build();

    static {
        MAPPER
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(INDENT_OUTPUT, true)
                //converts someFieldName to SomeFieldName
                .setPropertyNamingStrategy(PropertyNamingStrategy.UpperCamelCaseStrategy.UPPER_CAMEL_CASE)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .registerModule(new JavaTimeModule());

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
