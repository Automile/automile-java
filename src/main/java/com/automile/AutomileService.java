package com.automile;

import com.automile.model.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import static com.automile.AutomileConfig.getHttpClient;
import static com.automile.AutomileConfig.getMapper;
import static java.lang.String.format;
import static org.apache.commons.lang3.CharEncoding.UTF_8;

@Slf4j
public class AutomileService {

    @Getter
    @Setter
    private AuthResponse authResponse;

    public void deleteCall(String url) {
        validateToken();
        HttpUriRequest request = RequestBuilder.delete(url)
                .addHeader(getOauthHeader()).build();
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(request);
            getResponseString(response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        } finally {
            IOUtils.closeQuietly(response);
        }
    }

    public <T> T getCall(Class<T> clazz, String url, NameValuePair... params) {
        validateToken();
        HttpUriRequest request = RequestBuilder.get(url)
                .addHeader(getOauthHeader()).addParameters(params).build();
        return executeAndConvert(clazz, request);
    }


    public <T> List<T> listCall(Class<T> clazz, String url, NameValuePair... params) {
        validateToken();
        HttpUriRequest request = RequestBuilder.get(url)
                .addHeader(getOauthHeader()).addParameters(params).build();
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(request);
            String responseString = getResponseString(response);
            JavaType collectionType = getMapper().getTypeFactory().constructCollectionType(List.class, clazz);
            ObjectReader objectReader = getMapper().readerFor(collectionType);
            return objectReader.readValue(responseString);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        } finally {
            IOUtils.closeQuietly(response);
        }
    }

    public <T> T postCall(Object model, Class<T> clazz, String url) {
        validateToken();
        HttpUriRequest request = RequestBuilder.post(url)
                .addHeader(getOauthHeader()).setEntity(getEntity(model)).build();
        return executeAndConvert(clazz, request);
    }

    public void putCall(Object model, String url, NameValuePair... params) {
        validateToken();
        HttpUriRequest request = RequestBuilder.put(url)
                .addHeader(getOauthHeader()).addParameters(params).setEntity(getEntity(model)).build();
        executeAndConvert(null, request);
    }

    public String getResponseString(CloseableHttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        log.info("Response status code {}", statusCode);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, UTF_8);
        log.debug(responseString);

        if (statusCode != HttpStatus.SC_OK) {
            String phrase = statusLine.getReasonPhrase();
            throw new AutomileException(format("Code: %s Phrase: %s Message: %s", statusCode, phrase, responseString));
        }
        return responseString;
    }


    private <T> T executeAndConvert(Class<T> clazz, HttpUriRequest request) {
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_CREATED) {
                log.info("Response status code {}", statusCode);
                Header location = response.getFirstHeader(org.apache.http.HttpHeaders.LOCATION);
                return getCall(clazz, location.getValue());
            }
            String responseString = getResponseString(response);
            if (StringUtils.isEmpty(responseString) || clazz == null) {
                return null;
            }
            return getMapper().readValue(responseString, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        } finally {
            IOUtils.closeQuietly(response);
        }
    }


    private StringEntity getEntity(Object model) {
        try {
            StringEntity entity = new StringEntity(getMapper().writeValueAsString(model));
            entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            return entity;
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            log.error(e.getMessage());
            throw new AutomileException(e);
        }
    }

    public void validateToken() {
        if (getAuthResponse() == null) {
            throw new AutomileException("Please authorize before");
        }
        if (getAuthResponse().getExpirationDate().isBefore(LocalDateTime.now())) {
            //TODO: add config possibility optionally refresh token
            throw new AutomileException("Authorization expired");
        }
    }

    public void addParam(String key, Object value, List<NameValuePair> params) {
        if (value != null) {
            params.add(new BasicNameValuePair(key, value.toString()));
        }
    }

    private BasicHeader getOauthHeader() {
        return new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authResponse.getAccessToken());
    }

}
