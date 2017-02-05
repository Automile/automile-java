package com.automile;

import com.automile.model.AuthResponse;
import com.automile.model.CompanyContactCreateModel;
import com.automile.model.CompanyContactDetailModel;
import com.automile.model.CompanyContactEditModel;
import com.automile.model.CompanyCreateModel;
import com.automile.model.CompanyDetailModel;
import com.automile.model.CompanyEditModel;
import com.automile.model.CompanyModel;
import com.automile.model.CompanyVehicleCreateModel;
import com.automile.model.CompanyVehicleEditModel;
import com.automile.model.CompanyVehicleModel;
import com.automile.model.Contact2CreateModel;
import com.automile.model.Contact2DetailModel;
import com.automile.model.Contact2EditModel;
import com.automile.model.Contact2Model;
import com.google.common.net.HttpHeaders;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.automile.AutomileConfig.CREATE_URL;
import static com.automile.AutomileConfig.DELETE_URL;
import static com.automile.AutomileConfig.EDIT_URL;
import static com.automile.AutomileConfig.GET_BY_ID_URL;
import static com.automile.AutomileConfig.LIST_URL;
import static com.automile.AutomileConfig.getHttpClient;
import static com.automile.AutomileConfig.getMapper;
import static java.lang.String.format;

@Slf4j
public class AutomileClient {

    @Getter
    final AutomileService automileService = new AutomileService();

    String clientId;
    String clientSecret;
    String username;
    String password;


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
            String responseString = automileService.getResponseString(response);
            AuthResponse authResponse = getMapper().readValue(responseString, AuthResponse.class);
            authResponse.setExpirationDate(LocalDateTime.now().plusSeconds(authResponse.getExpiresIn()));
            automileService.setAuthResponse(authResponse);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        } finally {
            IOUtils.closeQuietly(response);
        }
    }

    public CompanyModel createCompany(CompanyCreateModel model) {
        return automileService.createCall(model, CompanyModel.class, format(CREATE_URL, "resourceowner", "companies"));
    }

    public List<CompanyModel> getCompanies() {
        return automileService.listCall(CompanyModel.class, format(LIST_URL, "resourceowner", "companies"));
    }

    public CompanyDetailModel getCompany(Integer id) {
        return automileService.getByIdCall(CompanyDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "companies", id));
    }

    public CompanyModel editCompany(Integer id, CompanyEditModel model) {
        return automileService.editCall(model, CompanyModel.class, format(EDIT_URL, "resourceowner", "companies", id));
    }

    public void deleteCompany(Integer id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "companies", id));
    }

    public CompanyContactDetailModel createCompanyContract(CompanyContactCreateModel model) {
        return automileService.createCall(model, CompanyContactDetailModel.class, format(CREATE_URL, "resourceowner", "companycontact"));
    }

    public List<CompanyContactDetailModel> getCompanyContracts() {
        return automileService.listCall(CompanyContactDetailModel.class, format(LIST_URL, "resourceowner", "companycontact"));
    }

    public CompanyContactDetailModel getCompanyContract(Integer id) {
        return automileService.getByIdCall(CompanyContactDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "companycontact", id));
    }

    public CompanyContactDetailModel editCompanyContract(Integer id, CompanyContactEditModel model) {
        return automileService.editCall(model, CompanyContactDetailModel.class, format(EDIT_URL, "resourceowner", "companycontact", id));
    }

    public void deleteCompanyContract(Integer id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "companycontact", id));
    }

    public CompanyVehicleModel createCompanyVehicle(CompanyVehicleCreateModel model) {
        return automileService.createCall(model, CompanyVehicleModel.class, format(CREATE_URL, "resourceowner", "companyvehicle"));
    }

    public List<CompanyVehicleModel> getCompanyVehicles() {
        return automileService.listCall(CompanyVehicleModel.class, format(LIST_URL, "resourceowner", "companyvehicle"));
    }

    public CompanyVehicleModel getCompanyVehicle(Integer id) {
        return automileService.getByIdCall(CompanyVehicleModel.class, format(GET_BY_ID_URL, "resourceowner", "companyvehicle", id));
    }

    public CompanyVehicleModel editCompanyVehicle(Integer id, CompanyVehicleEditModel model) {
        return automileService.editCall(model, CompanyVehicleModel.class, format(EDIT_URL, "resourceowner", "companyvehicle", id));
    }

    public void deleteCompanyVehicle(Integer id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "companyvehicle", id));
    }

    public Contact2Model createContact2(Contact2CreateModel model) {
        return automileService.createCall(model, Contact2Model.class, format(CREATE_URL, "resourceowner", "contacts2"));
    }

    public List<Contact2Model> getContacts2() {
        return automileService.listCall(Contact2Model.class, format(LIST_URL, "resourceowner", "contacts2"));
    }

    public Contact2DetailModel getContact2(Integer id) {
        return automileService.getByIdCall(Contact2DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts2", id));
    }

    public Contact2Model editContact2(Integer id, Contact2EditModel model) {
        return automileService.editCall(model, Contact2Model.class, format(EDIT_URL, "resourceowner", "contacts2", id));
    }

    public void deleteContact2(Integer id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "contacts2", id));
    }

}
