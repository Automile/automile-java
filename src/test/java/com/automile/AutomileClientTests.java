package com.automile;

import com.automile.model.response.AuthResponse;
import com.google.common.io.Resources;
import com.google.common.net.HttpHeaders;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.apache.http.HttpVersion.HTTP_1_1;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AutomileClientTests {

    private HttpEntity entity;
    private AutomileClient ac;
    private CloseableHttpResponse response;

    @Before
    public void before() throws IOException {
        entity = mock(HttpEntity.class);
        response = mock(CloseableHttpResponse.class);
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        when(entity.isStreaming()).thenReturn(Boolean.TRUE);
        when(response.getEntity()).thenReturn(entity);
        when(client.execute(any())).thenReturn(response);
        when(response.getStatusLine()).thenReturn(new BasicStatusLine(HTTP_1_1, HttpStatus.SC_OK, ""));

        ac = AutomileClient.builder().username("u").password("p").clientId("cId").clientSecret("cs").build();
        AutomileConfig.setHttpClient(client);
    }

    @Test(expected = AutomileException.class)
    public void testAuthorizeMissedClientError() throws IOException {
        ac = AutomileClient.builder().username("username").password("password").build();
        ac.authorize();
    }

    @Test(expected = AutomileException.class)
    public void testAuthorizeInvalidCredentialsError() throws IOException {
        mockStatus(HttpStatus.SC_BAD_REQUEST);
        mockContent("authorize/Invalid.json");
        ac.authorize();
    }

    @Test
    public void testAuthorizeOk() throws IOException {
        mockContent("authorize/Ok.json");
        ac.authorize();
        AuthResponse authResponse = ac.getAuthResponse();
        assertNotNull(authResponse);
        assertTrue(authResponse.getAccessToken().startsWith("EVpKLYJ4-d4YAGwHIONX"));
        assertEquals("bearer", authResponse.getTokenType());
        assertTrue(authResponse.getRefreshToken().startsWith("3zf4mW37QBw38dPD1VH"));
        assertEquals(7775999, authResponse.getExpiresIn());
    }

    private void mockContentType(String contentType) {
        when(entity.getContentType()).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, contentType));
    }

    private void mockContent(String resourceName) throws IOException {
        when(entity.getContent()).thenReturn(getFileInputStream(resourceName));
    }

    private void mockStatus(int status) throws IOException {
        when(response.getStatusLine()).thenReturn(new BasicStatusLine(HTTP_1_1, status, ""));
    }

    private FileInputStream getFileInputStream(String resourceName) throws FileNotFoundException {
        return new FileInputStream(new File(Resources.getResource(resourceName).getPath()));
    }
}
