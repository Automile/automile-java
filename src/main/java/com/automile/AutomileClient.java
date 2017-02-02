package com.automile;

import com.automile.model.response.AuthResponse;
import com.google.common.net.HttpHeaders;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.automile.AutomileConfig.getHttpClient;
import static com.automile.AutomileConfig.getMapper;
import static org.apache.commons.lang3.CharEncoding.UTF_8;

@Slf4j
public class AutomileClient {

    @Getter
    final AutomileService automileService = new AutomileService();

    String clientId;
    String clientSecret;
    String username;
    String password;

    @Getter
    AuthResponse authResponse;

    LocalDateTime authorizationDate;

    @Builder
    public AutomileClient(String clientId, String clientSecret, String username, String password) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    public void authorize() {
        if (StringUtils.isAnyEmpty(clientId, clientSecret, username, password)) {
            throw new AutomileException("Please configure all parameters");
        }
        String auth = Base64.encodeBase64String((clientId + ":" + clientSecret).getBytes());
        HttpUriRequest request = RequestBuilder.post(AutomileConfig.AUTH_URL)
                .addParameters(
                        new BasicNameValuePair("username", username),
                        new BasicNameValuePair("password", password),
                        new BasicNameValuePair("grant_type", "password")
                )
                .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + auth)
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded").
                        build();

        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("Authorization status code {}", statusCode);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, UTF_8);
            log.debug(responseString);

            if (statusCode != HttpStatus.SC_OK) {
                String error = automileService.getError(responseString);
                throw new AutomileException(String.format("Request failed. Status %s, Error %s", statusCode, error));
            }

            this.authResponse = getMapper().readValue(responseString, AuthResponse.class);
            this.authorizationDate = LocalDateTime.now();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            authorizationDate = null;
            throw new AutomileException(e);
        } finally {
            IOUtils.closeQuietly(response);
        }
    }

}
