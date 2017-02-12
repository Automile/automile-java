package com.automile;

import com.automile.model.AuthResponse;
import com.automile.model.CompanyContactDetailModel;
import com.automile.model.CompanyDetailModel;
import com.automile.model.CompanyModel;
import com.automile.model.CompanyVehicleModel;
import com.automile.model.Contact2DetailModel;
import com.automile.model.Contact2Model;
import com.automile.model.Vehicle2DetailModel;
import com.automile.model.Vehicle2Model;
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
import java.time.LocalDateTime;
import java.util.List;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpVersion.HTTP_1_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
        mockStatus(SC_BAD_REQUEST);
        mockContent("authorize/Invalid.json");
        ac.authorize();
    }

    @Test
    public void testAuthorizeOk() throws IOException {
        mockContent("authorize/Ok.json");
        ac.authorize();
        AuthResponse authResponse = ac.getAutomileService().getAuthResponse();
        assertNotNull(authResponse);
        assertTrue(authResponse.getAccessToken().startsWith("EVpKLYJ4-d4YAGwHIONX"));
        assertEquals("bearer", authResponse.getTokenType());
        assertTrue(authResponse.getRefreshToken().startsWith("3zf4mW37QBw38dPD1VH"));
        assertEquals(7775999, authResponse.getExpiresIn());
    }

    @Test
    public void testCompanyList() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerCompany/list.json");
        List<CompanyModel> companies = ac.getCompanies();
        assertEquals(7, companies.size());
    }

    @Test
    public void testCompanyGetById() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerCompany/getById.json");
        CompanyDetailModel company = ac.getCompany(1);
        assertEquals("yyy", company.getRegisteredCompanyName());
        assertEquals("xxx", company.getRegistrationNumber());
    }

    @Test
    public void testCompanyContactsList() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerCompanyContacts/list.json");
        List<CompanyContactDetailModel> companyContacts = ac.getCompanyContacts();
        assertEquals(8, companyContacts.size());
    }

    @Test
    public void testCompanyContactGetById() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerCompanyContacts/getById.json");
        CompanyContactDetailModel companyContact = ac.getCompanyContact(1);
        assertTrue(companyContact.getCompanyContactId().equals(13925));
    }

    @Test
    public void testVehicles2List() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerVehicles2/list.json");
        List<Vehicle2Model> vehicles2 = ac.getVehicles2();
        assertEquals(4, vehicles2.size());
    }

    @Test
    public void testVehicle2GetById() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerVehicles2/getById.json");
        Vehicle2DetailModel vehicle2 = ac.getVehicle2(1);
        assertEquals("WA1DGAFE5FD019516", vehicle2.getVehicleIdentificationNumber());
    }

    @Test
    public void testCompanyVehiclesList() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerCompanyVehicles/list.json");
        List<CompanyVehicleModel> companyVehicles = ac.getCompanyVehicles();
        assertEquals(1, companyVehicles.size());
    }

    @Test
    public void testCompanyVehicleGetById() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerCompanyVehicles/getById.json");
        CompanyVehicleModel companyVehicle = ac.getCompanyVehicle(1);
        assertTrue(companyVehicle.getCompanyVehicleId().equals(16982));
        assertTrue(companyVehicle.getCompanyId().equals(4295));
        assertTrue(companyVehicle.getVehicleId().equals(33553));
    }

    @Test
    public void testContracts2List() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerContracts2/list.json");
        List<Contact2Model> contacts2 = ac.getContacts2();
        assertEquals(3, contacts2.size());
    }

    @Test
    public void testContractGetById() throws IOException {
        mockAuthorized();
        mockContent("ResourceOwnerContracts2/getById.json");
        Contact2DetailModel contact2 = ac.getContact2(1);
        assertTrue(contact2.getContactId().equals(19110));
        assertEquals("qwe2", contact2.getFirstName());
        assertEquals("qwe2", contact2.getLastName());
    }

    private void mockAuthorized() {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("123");
        authResponse.setRefreshToken("123");
        authResponse.setExpiresIn(10000);
        authResponse.setExpirationDate(LocalDateTime.now().plusSeconds(10000));
        authResponse.setTokenType("bearer");
        ac.getAutomileService().setAuthResponse(authResponse);
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
