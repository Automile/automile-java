package com.automile;

import com.automile.model.*;
import com.google.common.collect.Lists;
import com.google.common.net.HttpHeaders;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.automile.AutomileConfig.CREATE_URL;
import static com.automile.AutomileConfig.DELETE_URL;
import static com.automile.AutomileConfig.EDIT_URL;
import static com.automile.AutomileConfig.GET_BY_ID_URL;
import static com.automile.AutomileConfig.GET_CONTACT2_CUSTOM_CATEGORIES_URL;
import static com.automile.AutomileConfig.GET_CONTACT2_CUSTOM_CATEGORY_URL;
import static com.automile.AutomileConfig.GET_CONTACT3_CUSTOM_CATEGORIES_URL;
import static com.automile.AutomileConfig.GET_CONTACT3_CUSTOM_CATEGORY_URL;
import static com.automile.AutomileConfig.IMEI_EVENTS_URL;
import static com.automile.AutomileConfig.LIST_URL;
import static com.automile.AutomileConfig.PUBLISH_SUBSCRIBE_TEST_URL;
import static com.automile.AutomileConfig.TRIPS_GET_PID_URL;
import static com.automile.AutomileConfig.TRIPS_GET_URL;
import static com.automile.AutomileConfig.USER_ACTION_URL;
import static com.automile.AutomileConfig.USER_URL;
import static com.automile.AutomileConfig.VEHICLES2_ACTION_URL;
import static com.automile.AutomileConfig.VEHICLE_HEALTH_GET_OVER_PERIOD_URL;
import static com.automile.AutomileConfig.getHttpClient;
import static com.automile.AutomileConfig.getMapper;
import static java.lang.String.format;

@Slf4j
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AutomileClient {

    @Getter
    @Setter
    AutomileService automileService = new AutomileService();

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

    public void authorize(String data) {
        try {
            AuthResponse authResponse = getMapper().readValue(data, AuthResponse.class);
            authResponse.setExpirationDate(LocalDateTime.now().plusSeconds(authResponse.getExpiresIn()));
            automileService.setAuthResponse(authResponse);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        }
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

    /**
     * Creates a new company and associates it with the user* @param model
     *
     * @return
     */
    public CompanyDetailModel createCompany(CompanyCreateModel model) {
        return automileService.postCall(model, CompanyDetailModel.class, format(CREATE_URL, "resourceowner", "companies"));
    }

    /**
     * Get a list of all companies that user is associated with
     *
     * @return
     */
    public List<CompanyModel> getCompanies() {
        return automileService.listCall(CompanyModel.class, format(LIST_URL, "resourceowner", "companies"));
    }

    /**
     * Get a company by id
     *
     * @param id
     * @return
     */
    public CompanyDetailModel getCompany(int id) {
        return automileService.getCall(CompanyDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "companies", id));
    }

    /**
     * Updates the given company id
     *
     * @param id
     * @param model
     */
    public void editCompany(int id, CompanyEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "companies", id));
    }

    /**
     * Removes the given company
     *
     * @param id
     */
    public void deleteCompany(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "companies", id));
    }

    /**
     * Creates a new company contact and associates it with user
     *
     * @param model
     * @return
     */
    public CompanyContactDetailModel createCompanyContact(CompanyContactCreateModel model) {
        return automileService.postCall(model, CompanyContactDetailModel.class, format(CREATE_URL, "resourceowner", "companycontact"));
    }

    /**
     * Get a list of all company contacts that user is associated with
     *
     * @return
     */
    public List<CompanyContactDetailModel> getCompanyContacts() {
        return automileService.listCall(CompanyContactDetailModel.class, format(LIST_URL, "resourceowner", "companycontact"));
    }

    /**
     * Get the company contact relationship
     *
     * @param id
     * @return
     */
    public CompanyContactDetailModel getCompanyContact(int id) {
        return automileService.getCall(CompanyContactDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "companycontact", id));
    }

    /**
     * Updates the given companycontact id
     *
     * @param id
     * @param model
     */
    public void editCompanyContact(int id, CompanyContactEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "companycontact", id));
    }

    /**
     * Removes the given company contact
     *
     * @param id
     */
    public void deleteCompanyContact(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "companycontact", id));
    }

    /**
     * Creates a new company vehicle and associates it with user through the company
     *
     * @param model
     * @return
     */
    public CompanyVehicleModel createCompanyVehicle(CompanyVehicleCreateModel model) {
        return automileService.postCall(model, CompanyVehicleModel.class, format(CREATE_URL, "resourceowner", "companyvehicle"));
    }

    /**
     * Get a list of all company vehicles that user is associated with
     *
     * @return
     */
    public List<CompanyVehicleModel> getCompanyVehicles() {
        return automileService.listCall(CompanyVehicleModel.class, format(LIST_URL, "resourceowner", "companyvehicle"));
    }

    /**
     * Get the company vehicle relationship
     *
     * @param id
     * @return
     */
    public CompanyVehicleModel getCompanyVehicle(int id) {
        return automileService.getCall(CompanyVehicleModel.class, format(GET_BY_ID_URL, "resourceowner", "companyvehicle", id));
    }

    /**
     * Updates the given company vehicle id
     *
     * @param id
     * @param model
     */
    public void editCompanyVehicle(int id, CompanyVehicleEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "companyvehicle", id));
    }

    /**
     * Removes the given company vehicle
     *
     * @param id
     */
    public void deleteCompanyVehicle(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "companyvehicle", id));
    }

    /**
     * Creates a new contact
     *
     * @param model
     * @return
     */
    public Contact2Model createContact2(Contact2CreateModel model) {
        return automileService.postCall(model, Contact2Model.class, format(CREATE_URL, "resourceowner", "contacts2"));
    }

    /**
     * Get a list of all contacts that user is associated with
     *
     * @return
     */
    public List<Contact2Model> getContacts2() {
        return automileService.listCall(Contact2Model.class, format(LIST_URL, "resourceowner", "contacts2"));
    }

    /**
     * Get a contact by id
     *
     * @param id
     * @return
     */
    public Contact2DetailModel getContact2(int id) {
        return automileService.getCall(Contact2DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts2", id));
    }

    /**
     * Updates the given contact id
     *
     * @param id
     * @param model
     */
    public void editContact2(int id, Contact2EditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "contacts2", id));
    }

    /**
     * Removes the given contact
     *
     * @param id
     */
    public void deleteContact2(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "contacts2", id));
    }

    /**
     * Get the contact representing myself
     *
     * @return
     */
    public Contact2DetailModel getContact2Me() {
        return automileService.getCall(Contact2DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts2", "me"));
    }

    /**
     * Get contact profile image
     *
     * @return
     */
    public Contact2DetailModel getContact2MeImage() {
        return automileService.getCall(Contact2DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts2", "me/image"));
    }

    /**
     * Upload contact profile image
     *
     * @param data
     * @return
     */
    public void editContact2UploadImage(byte[] data) {
        automileService.postCall(data, null, format(EDIT_URL, "resourceowner", "contacts2/me", "uploadimage"));
    }

    /**
     * Removes the users profile image
     *
     * @return
     */
    public void editContact2RemoveImage() {
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "contacts2/me", "removeimage"));
    }

    /**
     * Updates the default vehicle
     *
     * @param vehicleId
     * @return
     */
    public void editContact2MeUpdateDefaultVehicle(int vehicleId) {
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "contacts2/me/updatedefaultvehicle", vehicleId));
    }

    /**
     * Updates the default vehicle
     *
     * @param vehicleId
     * @return
     */
    public void editContact2UpdateDefaultVehicle(int vehicleId) {
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "contacts2", "updatedefaultvehicle", vehicleId));
    }

    /**
     * Add and remove custom categories
     *
     * @param model
     * @return
     */
    public void editContact2CustomCategories(CustomCategoryPostModel model) {
        automileService.postCall(model, null, format(CREATE_URL, "resourceowner", "contacts2/customcategory"));
    }

    /**
     * Get a custom category
     *
     * @return
     */
    public CustomCategoryModel getContacts2CustomCategory(int contactId, int customCategoryId) {
        return automileService.getCall(CustomCategoryModel.class, format(GET_CONTACT2_CUSTOM_CATEGORY_URL, contactId, customCategoryId));
    }

    /**
     * Get a all custom categories
     *
     * @return
     */
    public List<CustomCategoryModel> getContacts2CustomCategories(int contactId) {
        return automileService.listCall(CustomCategoryModel.class, format(GET_CONTACT2_CUSTOM_CATEGORIES_URL, contactId));
    }


    /**
     * Creates a new vehicle
     *
     * @param model
     * @return
     */
    public Vehicle2Model createVehicle2(Vehicle2CreateModel model) {
        return automileService.postCall(model, Vehicle2Model.class, format(CREATE_URL, "resourceowner", "vehicles2"));
    }

    /**
     * Get all vehicles that the user has access to
     *
     * @return
     */
    public List<Vehicle2Model> getVehicles2() {
        return automileService.listCall(Vehicle2Model.class, format(LIST_URL, "resourceowner", "vehicles2"));
    }

    /**
     * Get the details about the vehicle
     *
     * @param id
     * @return
     */
    public Vehicle2DetailModel getVehicle2(int id) {
        return automileService.getCall(Vehicle2DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "vehicles2", id));
    }

    /**
     * Updates the given vehicle with new model
     *
     * @param id
     * @param model
     */
    public void editVehicle2(int id, Vehicle2EditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "vehicles2", id));
    }

    /**
     * Removes the given vehicle
     *
     * @param id
     */
    public void deleteVehicle2(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "vehicles2", id));
    }

    /**
     * Returns the external information we have on the vehicle
     *
     * @param identifier
     * @param identifierType
     * @return
     */
    public TransportstyrelsenInfoModel getVehicle2VehicleInformation(String identifier, byte identifierType) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("identifier", identifier, params);
        automileService.addParam("identifierType", identifierType, params);
        return automileService.getCall(TransportstyrelsenInfoModel.class, format(VEHICLES2_ACTION_URL, "VehicleInformation"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Get position and status of all vehicles that the user has access to
     *
     * @return
     */
    public List<VehicleStatusModel> getVehicles2Status() {
        return automileService.listCall(VehicleStatusModel.class, format(VEHICLES2_ACTION_URL, "status"));
    }

    /**
     * Check-in to a vehicle
     */
    public void editVehicle2Checkin(VehicleCheckInModel model) {
        automileService.postCall(model, null, format(VEHICLES2_ACTION_URL, "checkin"));
    }

    /**
     * Check-out from a vehicle
     */
    public void editVehicle2Checkout() {
        automileService.postCall(null, null, format(VEHICLES2_ACTION_URL, "checkout"));
    }


    /**
     * Creates a new vehicle contact and associates it with user
     *
     * @param model
     * @return
     */
    public ContactVehicleDetailModel createContactVehicle(ContactVehicleCreateModel model) {
        return automileService.postCall(model, ContactVehicleDetailModel.class, format(CREATE_URL, "resourceowner", "contactvehicle"));
    }

    /**
     * Get all vehicle contacts that the logged in user has access to
     *
     * @return
     */
    public List<ContactVehicleDetailModel> getContactVehicles() {
        return automileService.listCall(ContactVehicleDetailModel.class, format(LIST_URL, "resourceowner", "contactvehicle"));
    }

    /**
     * Get contacts by vehicle id
     *
     * @param vehicleId
     * @return
     */
    public List<ContactModel> getContactVehicle(int vehicleId) {
        return automileService.listCall(ContactModel.class, format(GET_BY_ID_URL, "resourceowner", "contactvehicle", vehicleId));
    }

    /**
     * Updates the given contact vehicle id
     *
     * @param id
     * @param model
     */
    public void editContactVehicle(int id, ContactVehicleEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "contactvehicle", id));
    }

    /**
     * Removes the given contact vehicle
     *
     * @param id
     */
    public void deleteContactVehicle(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "contactvehicle", id));
    }

    /**
     * Create an expense report
     *
     * @param model
     * @return
     */
    public ExpenseReportModel createExpenseReport(ExpenseReportCreateModel model) {
        return automileService.postCall(model, ExpenseReportModel.class, format(CREATE_URL, "resourceowner", "expensereport"));
    }

    /**
     * Get a list of expense reports
     *
     * @param tripId
     * @param vehicleId
     * @return
     */
    public List<ExpenseReportModel> getExpenseReports(int tripId, int vehicleId) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("tripId", tripId, params);
        automileService.addParam("vehicleId", vehicleId, params);
        return automileService.listCall(ExpenseReportModel.class, format(LIST_URL, "resourceowner", "expensereport"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Get an expense report
     *
     * @param id
     * @return
     */
    public ExpenseReportModel getExpenseReport(int id) {
        return automileService.getCall(ExpenseReportModel.class, format(GET_BY_ID_URL, "resourceowner", "expensereport", id));
    }

    /**
     * Updates the given expense report
     *
     * @param id
     * @param model
     */
    public void editExpenseReport(int id, ExpenseReportEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "expensereport", id));
    }

    /**
     * Removes the given expense report
     *
     * @param id
     */
    public void deleteExpenseReport(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "expensereport", id));
    }

    /**
     * Export an expense report in pdf format
     *
     * @param id
     * @param model
     */
    public void emailExpenseReportExport(int id, EmailExpenseReportModel model) {
        automileService.postCall(model, null, format(EDIT_URL, "resourceowner", "expensereport/export", id));
    }

    /**
     * Export an expense reports in pdf format
     *
     * @param model
     */
    public void emailExpenseReportsExport(EmailExpenseReportsModel model) {
        automileService.postCall(model, null, format(CREATE_URL, "resourceowner", "expensereport/export"));
    }

    /**
     * Removes the given expense report rows
     *
     * @param id expenseReportId
     */
    public void deleteExpenseReportRows(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner/expensereport", id, "expenseReportItems"));
    }


    /**
     * Carry out optical character recognization on image fragments and return the response
     *
     * @param model
     */
    public void createExpenseReportOCR(OCRRequest model) {
        automileService.postCall(model, OCRResult.class, format(CREATE_URL, "resourceowner", "expensereport/ocr"));
    }

    /**
     * Create an expense report row
     *
     * @param model
     * @return
     */
    public ExpenseReportRowModel createExpenseReportRow(ExpenseReportRowCreateModel model) {
        return automileService.postCall(model, ExpenseReportRowModel.class, format(CREATE_URL, "resourceowner", "expensereportrow"));
    }

    /**
     * Get expense report row
     *
     * @param id
     * @return
     */
    public ExpenseReportRowModel getExpenseReportRow(int id) {
        return automileService.getCall(ExpenseReportRowModel.class, format(GET_BY_ID_URL, "resourceowner", "expensereportrow", id));
    }

    /**
     * Removes the given expense report row
     *
     * @param id
     * @param model
     */
    public void editExpenseReportRow(int id, ExpenseReportRowEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "expensereportrow", id));
    }

    /**
     * Removes the given expense report row
     *
     * @param id
     */
    public void deleteExpenseReportRow(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "expensereportrow", id));
    }


    /**
     * Create an expense report row content
     *
     * @param model
     * @return
     */
    public ExpenseReportRowContentModel createExpenseReportRowContent(ExpenseReportRowContentCreateModel model) {
        return automileService.postCall(model, ExpenseReportRowContentModel.class, format(CREATE_URL, "resourceowner", "expensereportrowcontent"));
    }

    /**
     * Get all expense report row content belonging to a expense report row
     *
     * @param expenseReportRowId
     * @return
     */
    public List<ExpenseReportRowContentModel> getExpenseReportRowContents(int expenseReportRowId) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("expenseReportRowId", expenseReportRowId, params);
        return automileService.listCall(ExpenseReportRowContentModel.class, format(LIST_URL, "resourceowner", "expensereportrowcontent"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Get expense report row content
     *
     * @param id
     * @return
     */
    public ExpenseReportRowContentModel getExpenseReporRowContent(int id) {
        return automileService.getCall(ExpenseReportRowContentModel.class, format(GET_BY_ID_URL, "resourceowner", "expensereportrowcontent", id));
    }


    /**
     * Updates the given expense report row content
     *
     * @param id
     * @param model
     */
    public void editExpenseReportRowContent(int id, ExpenseReportRowContentEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "expensereportrowcontent", id));
    }

    /**
     * Removes the given expense report row content
     *
     * @param id
     */
    public void deleteExpenseReportRowContent(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "expensereportrowcontent", id));
    }

    /**
     * Creates a new IMEIConfig and associates it with vehicle
     *
     * @param model
     * @return
     */
    public IMEIConfigModel createIMEIConfig(IMEIConfigCreateModel model) {
        return automileService.postCall(model, IMEIConfigModel.class, format(CREATE_URL, "resourceowner", "imeiconfigs"));
    }

    /**
     * Get all imeis(devices) that the logged in user has access to
     *
     * @param includeDeviceType
     * @return
     */
    public List<IMEIConfigModel> getIMEIConfigs(boolean includeDeviceType) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("includeDeviceType", includeDeviceType, params);
        return automileService.listCall(IMEIConfigModel.class, format(LIST_URL, "resourceowner", "imeiconfigs"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Get an IMEIConfig
     *
     * @param id
     * @param includeDeviceType
     * @return
     */
    public IMEIConfigDetailModel getIMEIConfig(int id, boolean includeDeviceType) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("includeDeviceType", includeDeviceType, params);
        return automileService.getCall(IMEIConfigDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "imeiconfigs", id),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Updates the given IMEIConfig id
     *
     * @param id
     * @param model
     */
    public void editIMEIConfig(int id, IMEIConfigEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "imeiconfigs", id));
    }

    /**
     * Removes the given IMEI config
     *
     * @param id
     */
    public void deleteIMEIConfig(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "imeiconfigs", id));
    }

    /**
     * Get a list of all IMEIEvents that user is associated with
     *
     * @return
     */
    public List<IMEIEventModel> getIMEIEvents() {
        return automileService.listCall(IMEIEventModel.class, format(LIST_URL, "resourceowner", "imeievents"));
    }

    /**
     * Gets MILIMEIevent for given imeiEventId
     *
     * @param id imeiEventId
     * @return
     */
    public IMEIEventMILModel getMILIMEIEvent(int id) {
        return automileService.getCall(IMEIEventMILModel.class, format(IMEI_EVENTS_URL, "mil", id));
    }

    /**
     * Gets DTCIMEIevent for given imeiEventId
     *
     * @param id imeiEventId
     * @return
     */
    public IMEIEventDTCModel getDTCIMEIEvent(int id) {
        return automileService.getCall(IMEIEventDTCModel.class, format(IMEI_EVENTS_URL, "dtc", id));
    }

    /**
     * Gets StatusIMEIevent for given imeiEventId
     *
     * @param id imeiEventId
     * @return
     */
    public IMEIEventStatusModel getIMEIEventStatus(int id) {
        return automileService.getCall(IMEIEventStatusModel.class, format(IMEI_EVENTS_URL, "status", id));
    }

    /**
     * Creates a new place
     *
     * @param model
     * @return
     */
    public PlaceModel createPlace(PlaceCreateModel model) {
        return automileService.postCall(model, PlaceModel.class, format(CREATE_URL, "resourceowner", "place"));
    }

    /**
     * Get places
     *
     * @return
     */
    public List<PlaceModel> getPlaces() {
        return automileService.listCall(PlaceModel.class, format(LIST_URL, "resourceowner", "place"));
    }

    /**
     * Get place
     *
     * @param id
     * @return
     */
    public PlaceModel getPlace(int id) {
        return automileService.getCall(PlaceModel.class, format(GET_BY_ID_URL, "resourceowner", "place", id));
    }

    /**
     * Updates the given place with new model
     *
     * @param id
     * @param model
     */
    public void editPlace(int id, PlaceEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "place", id));
    }

    /**
     * Removes the given place
     *
     * @param id
     */
    public void deletePlace(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "place", id));
    }

    /**
     * Creates a new place
     *
     * @param model
     * @return
     */
    public PlaceModel createPlace2(Place2CreateModel model) {
        return automileService.postCall(model, PlaceModel.class, format(CREATE_URL, "resourceowner", "place2"));
    }

    /**
     * Updates the given place with new model
     *
     * @param id
     * @param model
     */
    public void editPlace2(int id, Place2EditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "place2", id));
    }

    /**
     * Create an a publish subscribe record
     *
     * @param model
     * @return
     */
    public PublishSubscribeModel createPublishSubscribe(PublishSubscribeCreateModel model) {
        return automileService.postCall(model, PublishSubscribeModel.class, format(CREATE_URL, "resourceowner", "publishsubscribe"));
    }

    /**
     * Get all publish subscribe records the user has created
     *
     * @return
     */
    public List<PublishSubscribeModel> getPublishSubscribes() {
        return automileService.listCall(PublishSubscribeModel.class, format(LIST_URL, "resourceowner", "publishsubscribe"));
    }

    /**
     * Get the publish subscribe by record id
     *
     * @param id
     * @return
     */
    public PublishSubscribeModel getPublishSubscribe(int id) {
        return automileService.getCall(PublishSubscribeModel.class, format(GET_BY_ID_URL, "resourceowner", "publishsubscribe", id));
    }

    /**
     * Updates the given publish subscribe record
     *
     * @param id
     * @param model
     */
    public void editPublishSubscribe(int id, PublishSubscribeEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "publishsubscribe", id));
    }

    /**
     * Removes the given publish subscribe record
     *
     * @param id
     */
    public void deletePublishSubscribe(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "publishsubscribe", id));
    }

    /**
     * Publishes a test trip start to the publish subscribe endpoint
     *
     * @param publishSubscribeId
     * @param vehicleId
     */
    public void testTripStart(int publishSubscribeId, int vehicleId) {
        BasicNameValuePair vehicleIdParam = new BasicNameValuePair("vehicleId", String.valueOf(vehicleId));
        automileService.getCall(null, format(PUBLISH_SUBSCRIBE_TEST_URL, publishSubscribeId, "testtripstart"), vehicleIdParam);
    }

    /**
     * Publishes a test trip end to the publish subscribe endpoint
     *
     * @param publishSubscribeId
     * @param vehicleId
     */
    public void testTripEnd(int publishSubscribeId, int vehicleId) {
        BasicNameValuePair vehicleIdParam = new BasicNameValuePair("vehicleId", String.valueOf(vehicleId));
        automileService.getCall(null, format(PUBLISH_SUBSCRIBE_TEST_URL, publishSubscribeId, "testtripend"), vehicleIdParam);
    }

    /**
     * Publishes a created vehicle to the publish subscribe endpoint
     *
     * @param publishSubscribeId
     * @param vehicleId
     */
    public void testVehicleCreated(int publishSubscribeId, int vehicleId) {
        BasicNameValuePair vehicleIdParam = new BasicNameValuePair("vehicleId", String.valueOf(vehicleId));
        automileService.getCall(null, format(PUBLISH_SUBSCRIBE_TEST_URL, publishSubscribeId, "testvehiclecreated"), vehicleIdParam);
    }

    /**
     * Publishes a modified vehicle to the publish subscribe endpoint
     *
     * @param publishSubscribeId
     * @param vehicleId
     */
    public void testVehicleModified(int publishSubscribeId, int vehicleId) {
        BasicNameValuePair vehicleIdParam = new BasicNameValuePair("vehicleId", String.valueOf(vehicleId));
        automileService.getCall(null, format(PUBLISH_SUBSCRIBE_TEST_URL, publishSubscribeId, "testvehiclemodified"), vehicleIdParam);
    }

    /**
     * Publishes a created driver / contact to the publish subscribe endpoint
     *
     * @param publishSubscribeId
     * @param contactId
     */
    public void testContactCreated(int publishSubscribeId, int contactId) {
        BasicNameValuePair contactIdParam = new BasicNameValuePair("contactId", String.valueOf(contactId));
        automileService.getCall(null, format(PUBLISH_SUBSCRIBE_TEST_URL, publishSubscribeId, "testcontactcreated"), contactIdParam);
    }

    /**
     * The contact end was published to your publish subscribe record endpoint
     *
     * @param publishSubscribeId
     * @param contactId
     */
    public void testContactModified(int publishSubscribeId, int contactId) {
        BasicNameValuePair contactIdParam = new BasicNameValuePair("contactId", String.valueOf(contactId));
        automileService.getCall(null, format(PUBLISH_SUBSCRIBE_TEST_URL, publishSubscribeId, "testcontactmodified"), contactIdParam);
    }

    /**
     * Get a trip summary report
     *
     * @return
     */
    public List<TripSummaryReportModel> getReportsTripSummary(String dateperiod) {
        return automileService.listCall(TripSummaryReportModel.class, format(LIST_URL, "resourceowner/reports/tripsummary", dateperiod));
    }

    /**
     * Get a trip summary report filtered by vehicle
     *
     * @return
     */
    public List<TripSummaryReportModel> getReportsTripSummary(String dateperiod, int vehicleId) {
        return automileService.listCall(TripSummaryReportModel.class, format(GET_BY_ID_URL, "resourceowner/reports/tripsummary", dateperiod, vehicleId));
    }

    /**
     * Get vehicles summary report
     *
     * @return
     */
    public List<VehiclesSummaryModel> getReportsVehiclesSummary(String dateperiod) {
        return automileService.listCall(VehiclesSummaryModel.class, format(LIST_URL, "resourceowner/reports/vehiclessummary", dateperiod));
    }

    /**
     * Get a vehicle summary report
     *
     * @return
     */
    public VehicleSummaryModel getReportVehicleSummary(String dateperiod, int vehicleId) {
        return automileService.getCall(VehicleSummaryModel.class, format(GET_BY_ID_URL, "resourceowner/reports/vehiclesummary", dateperiod, vehicleId));
    }

    /**
     * Export a trip report in pdf format
     *
     * @param model
     */
    public void emailTripReport(EmailTripReportModel model) {
        automileService.postCall(model, null, format(EDIT_URL, "resourceowner", "reports", "emailtripreport"));
    }

    /**
     * Get Geofence log
     */
    public GeofenceReportModel geofenceLogReport(Integer vehicleId, Integer geofenceId, LocalDateTime fromDate, LocalDateTime toDate) {
        List<NameValuePair> params = Lists.newArrayList();
        Optional.ofNullable(vehicleId).ifPresent(p -> automileService.addParam("vehicleId", vehicleId, params));
        Optional.ofNullable(geofenceId).ifPresent(p -> automileService.addParam("geofenceId", geofenceId, params));
        Optional.ofNullable(fromDate).ifPresent(p -> automileService.addParam("fromDate", fromDate, params));
        Optional.ofNullable(toDate).ifPresent(p -> automileService.addParam("toDate", toDate, params));
        return automileService.getCall(GeofenceReportModel.class, format(GET_BY_ID_URL, "resourceowner", "reports", "geofencelog"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Create a task
     *
     * @param model
     * @return
     */
    public TaskDetailModel createTask(TaskCreateModel model) {
        return automileService.postCall(model, TaskDetailModel.class, format(CREATE_URL, "resourceowner", "task"));
    }

    /**
     * Get all tasks open and closed
     *
     * @return
     */
    public List<TaskModel> getTasks() {
        return automileService.listCall(TaskModel.class, format(LIST_URL, "resourceowner", "task"));
    }

    /**
     * Get task details
     *
     * @param id
     * @return
     */
    public TaskDetailModel getTask(int id) {
        return automileService.getCall(TaskDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "task", id));
    }

    /**
     * Update a task
     *
     * @param id
     * @param model
     */
    public void editTask(int id, TaskEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "task", id));
    }

    /**
     * Create a task message
     *
     * @param model
     * @return
     */
    public TaskMessageModel createTaskMessage(TaskMessageCreateModel model) {
        return automileService.postCall(model, TaskMessageModel.class, format(CREATE_URL, "resourceowner", "taskmessage"));
    }

    /**
     * Get a task message
     *
     * @param id
     * @return
     */
    public TaskMessageModel getTaskMessage(int id) {
        return automileService.getCall(TaskMessageModel.class, format(GET_BY_ID_URL, "resourceowner", "taskmessage", id));
    }

    /**
     * Update a task message
     *
     * @param id
     * @param model
     */
    public void editTaskMessage(int id, TaskMessageEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "taskmessage", id));
    }

    /**
     * Get all asset trackers that the user have access to
     *
     * @return
     */
    public List<TrackedAssetModelGET> getTrackers() {
        return automileService.listCall(TrackedAssetModelGET.class, format(LIST_URL, "resourceowner", "trackedasset"));
    }

    /**
     * Get all trigger messages that the logged in user has access to
     *
     * @return
     */
    public List<TriggerMessageHistoryModel> getTriggerMessageHistories() {
        return automileService.listCall(TriggerMessageHistoryModel.class, format(LIST_URL, "triggermessageshistory", "place"));
    }

    /**
     * Get trigger messages by trigger id
     *
     * @param id
     * @return
     */
    public List<TriggerMessageHistoryModel> getTriggerMessageHistories(int id) {
        return automileService.listCall(TriggerMessageHistoryModel.class, format(GET_BY_ID_URL, "resourceowner", "triggermessageshistory", id));
    }

    /**
     * Creates a new trigger
     *
     * @param model
     * @return
     */
    public TriggerDetailModel createTrigger(TriggerCreateModel model) {
        return automileService.postCall(model, TriggerDetailModel.class, format(CREATE_URL, "resourceowner", "triggers"));
    }

    /**
     * Get all triggers
     *
     * @return
     */
    public List<TriggerModel> getTriggers() {
        return automileService.listCall(TriggerModel.class, format(LIST_URL, "resourceowner", "triggers"));
    }

    /**
     * Get a trigger by id
     *
     * @param id
     * @return
     */
    public TriggerDetailModel getTrigger(int id) {
        return automileService.getCall(TriggerDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "triggers", id));
    }

    /**
     * Updates the given trigger id
     *
     * @param id
     * @param model
     */
    public void editTrigger(int id, TriggerEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "triggers", id));
    }

    /**
     * Mute the given trigger id
     *
     * @param id
     * @param model
     */
    public void editTriggerMute(int id, TriggerEditMutedUntilModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "triggers/mute", id));
    }

    /**
     * Unmute the given trigger id
     *
     * @param id
     */
    public void editTriggerUnmute(int id) {
        automileService.putCall("", format(EDIT_URL, "resourceowner", "triggers/unmute", id));
    }

    /**
     * Move all users push triggers to userdevice
     *
     * @param model
     */
    public void editTriggerMovePush(MovePushTriggers model) {
        automileService.postCall(model, null, format(CREATE_URL, "resourceowner", "triggers/movepush"));
    }

    /**
     * Removes the given trigger
     *
     * @param id
     */
    public void deleteTrigger(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "triggers", id));
    }

    /**
     * This get's the details of the current user that you are logged in as
     *
     * @return
     */
    public UserModel getUser() {
        return automileService.getCall(UserModel.class, USER_URL);
    }

    /**
     * Change the password of the current user that you are logged in as
     *
     * @param model
     */
    public void editUserChangePassword(ChangePasswordModel model) {
        automileService.putCall(model, format(USER_ACTION_URL, "changepassword"));
    }

    /**
     * Change the username of the current user that you are logged in as
     *
     * @param model
     */
    public void editUserChangeUsername(ChangeUserNameModel model) {
        automileService.putCall(model, format(USER_ACTION_URL, "changeusername"));
    }

    /**
     * Check if the user has a password set
     */
    public UserExistingPasswordModel getUserExistingPassword() {
        return automileService.getCall(UserExistingPasswordModel.class, format(USER_ACTION_URL, "userexistingpassword"));
    }

    /**
     * Reset the password by SMS for the current user logged in
     *
     * @param model
     */
    public void editUserResetPassword(ResetPasswordUserModel model) {
        automileService.postCall(model, null, format(USER_ACTION_URL, "resetpassword"));
    }

    /**
     * Get all trips that the user has access to
     *
     * @param lastNumberOfDays
     * @param vehicleId
     * @param isSynchronized
     * @return
     */
    public List<TripModel> getTrips(int lastNumberOfDays, Integer vehicleId, Boolean isSynchronized) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("lastNumberOfDays", lastNumberOfDays, params);
        automileService.addParam("vehicleId", vehicleId, params);
        automileService.addParam("synchronized", Optional.ofNullable(isSynchronized).orElse(Boolean.TRUE), params);
        return automileService.listCall(TripModel.class, format(LIST_URL, "resourceowner", "trips"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Get the details about the trip
     *
     * @param id
     * @return
     */
    public TripDetailModel getTrip(int id) {
        return automileService.getCall(TripDetailModel.class, format(GET_BY_ID_URL, "resourceowner", "trips", id));
    }

    /**
     * Updates the given trip with a trip type (category) and trip tag
     *
     * @param id
     * @param model
     */
    public void editTrip(int id, TripEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "trips", id));
    }

    /**
     * Get the trip start and stop geo locations
     *
     * @param id
     * @return
     */
    public TripStartEndGeoModel getTripGeoStartEnd(int id) {
        return automileService.getCall(TripStartEndGeoModel.class, format(TRIPS_GET_URL, id, "geostartend"));
    }

    /**
     * Get the vehicle speed if it's reported by the vehicle
     *
     * @param id
     * @return
     */
    public VehicleSpeedModel getTripSpeed(int id) {
        return automileService.getCall(VehicleSpeedModel.class, format(TRIPS_GET_URL, id, "speed"));
    }

    /**
     * Get the vehicle engine rpm if it's reported by the vehicle
     *
     * @param id
     * @return
     */
    public RPMModel getTripRPM(int id) {
        return automileService.getCall(RPMModel.class, format(TRIPS_GET_URL, id, "rpm"));
    }

    /**
     * Get the vehicle ambient (outside) temperature if it's reported by the vehicle
     *
     * @param id
     * @return
     */
    public AmbientAirTemperatureModel getTripAmbientTemperature(int id) {
        return automileService.getCall(AmbientAirTemperatureModel.class, format(TRIPS_GET_URL, id, "ambienttemperature"));
    }

    /**
     * Get the vehicle current fuel level if it's reported by the vehicle
     *
     * @param id
     * @return
     */
    public FuelLevelInputModel getTripFuelLevelInput(int id) {
        return automileService.getCall(FuelLevelInputModel.class, format(TRIPS_GET_URL, id, "fuellevelinput"));
    }

    /**
     * Get the engine coolant temperature if it's reported by the vehicle
     *
     * @param id
     * @return
     */
    public EngineCoolantTemperatureModel getTripEngineCoolantTemperature(int id) {
        return automileService.getCall(EngineCoolantTemperatureModel.class, format(TRIPS_GET_URL, id, "enginecoolanttemperature"));
    }

    /**
     * This will get the raw PID data if the vehicle has reported that it is being
     *
     * @param tripId
     * @param pidId
     * @return
     */
    public PIDModel getTripPID(int tripId, int pidId) {
        return automileService.getCall(PIDModel.class, format(TRIPS_GET_PID_URL, tripId, pidId));
    }

    /**
     * Get the gps locations for the vehicle, includes position and heading
     *
     * @param everyNthRecord
     * @param snapToRoad
     * @param id
     * @return
     */
    public TripGeoModel getTripGeo(Integer everyNthRecord, boolean snapToRoad, int id) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("everyNthRecord", everyNthRecord, params);
        automileService.addParam("snapToRoad", snapToRoad, params);
        return automileService.getCall(TripGeoModel.class, format(TRIPS_GET_URL, id, "geo"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Gets Google static map URL with the trip route as a polyline
     *
     * @param id
     * @param height
     * @param width
     * @return
     */
    public String getTripGoogleUrlToStaticMapEncodedPolyline(int id, int height, int width) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("height", height, params);
        automileService.addParam("width", width, params);
        return automileService.getCall(String.class, format(TRIPS_GET_URL, id, "googleurltostaticmapencodedpolyline"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Updates the last trip with trip notes
     *
     * @param model
     * @return
     */
    public void editTripAddNotesToLastTrip(TripAddNoteModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "trips", "addnotestolasttrip"));
    }

    /**
     * Updates the given trip with given contactid
     *
     * @param tripId
     * @param contactId
     * @return
     */
    public void editTripSetDriverOnTrip(int tripId, int contactId) {
        List<NameValuePair> params = Lists.newArrayList();
        automileService.addParam("tripId", tripId, params);
        automileService.addParam("contactId", contactId, params);
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "trips", "setdriverontrip"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Mark trips as synchronized, synchronized trips will not be returned when fetching trips
     *
     * @param model
     * @return
     */
    public void editTripSynchronized(TripSynchronized model) {
        automileService.postCall(model, null, format(EDIT_URL, "resourceowner", "trips", "synchronized"));
    }

    /**
     * Get the details about the trip including driving events, speeding and idling
     *
     * @param id
     * @return
     */
    public TripConcatenation getTripDetails(int id) {
        return automileService.getCall(TripConcatenation.class, format(TRIPS_GET_URL, id, "details"));
    }

    /**
     * Get the advanced details about the trip including driving events, speeding, idling, speed and rpm data series
     *
     * @param id
     * @return
     */
    public TripConcatenation getTripAdvanced(int id) {
        return automileService.getCall(TripConcatenation.class, format(TRIPS_GET_URL, id, "advanced"));
    }

    /**
     * Creates a new user device
     *
     * @param model
     * @return
     */
    public UserDeviceModel createUserDevice(UserDeviceCreateModel model) {
        return automileService.postCall(model, UserDeviceModel.class, format(CREATE_URL, "resourceowner", "userdevice"));
    }

    /**
     * Get a list of all user devices that the user is associated with
     *
     * @return
     */
    public List<UserDeviceModel> getUserDevices() {
        return automileService.listCall(UserDeviceModel.class, format(LIST_URL, "resourceowner", "userdevice"));
    }

    /**
     * Get a user device by id
     *
     * @param id
     * @return
     */
    public UserDeviceModel getUserDevice(int id) {
        return automileService.getCall(UserDeviceModel.class, format(GET_BY_ID_URL, "resourceowner", "userdevice", id));
    }

    /**
     * Updates the given user device with the new model
     *
     * @param id
     * @param model
     */
    public void editUserDevice(int id, UserDeviceEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "userdevice", id));
    }

    /**
     * Removes the given user device
     *
     * @param id
     */
    public void deleteUserDevice(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "userdevice", id));
    }

    /**
     * Creates a new vehicle defect comment
     *
     * @param model
     * @return
     */
    public VehicleDefectCommentsModel createVehicleDefectComment(VehicleDefectCommentsCreateModel model) {
        return automileService.postCall(model, VehicleDefectCommentsModel.class, format(CREATE_URL, "resourceowner", "vehicledefectcomments"));
    }

    /**
     * Get all vehicle defect comments that the user has access to
     *
     * @return
     */
    public List<VehicleDefectCommentsModel> getVehicleDefectComments() {
        return automileService.listCall(VehicleDefectCommentsModel.class, format(LIST_URL, "resourceowner", "vehicledefectcomments"));
    }

    /**
     * Get a vehicle defect comment by id
     *
     * @param id
     * @return
     */
    public VehicleDefectCommentsModel getVehicleDefectComment(int id) {
        return automileService.getCall(VehicleDefectCommentsModel.class, format(GET_BY_ID_URL, "resourceowner", "vehicledefectcomments", id));
    }

    /**
     * Update a vehicle defect comment
     *
     * @param id
     * @param model
     */
    public void editVehicleDefectComment(int id, VehicleDefectCommentsEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "vehicledefectcomments", "userdevice", id));
    }

    /**
     * Get all vehicle defect types
     *
     * @return
     */
    public List<VehicleDefectTypeModel> getVehicleDefectTypes() {
        return automileService.listCall(VehicleDefectTypeModel.class, format(LIST_URL, "resourceowner", "vehicledefecttypes"));
    }

    /**
     * Associate a vehicle with a geofence
     *
     * @param model
     * @return
     */
    public VehicleGeofenceModel createVehicleGeofence(VehicleGeofenceCreateModel model) {
        return automileService.postCall(model, VehicleGeofenceModel.class, format(CREATE_URL, "resourceowner", "vehiclegeofence"));
    }

    /**
     * Get vehicle geofences
     *
     * @param geofenceId
     * @return
     */
    public List<VehicleGeofenceModel> getVehicleGeofences(int geofenceId) {
        BasicNameValuePair geofenceIdParam = new BasicNameValuePair("geofenceId", String.valueOf(geofenceId));
        return automileService.listCall(VehicleGeofenceModel.class, format(LIST_URL, "resourceowner", "vehiclegeofence"), geofenceIdParam);
    }

    /**
     * Get vehicle geofence by id
     *
     * @param id
     * @return
     */
    public VehicleGeofenceModel getVehicleGeofence(int id) {
        return automileService.getCall(VehicleGeofenceModel.class, format(GET_BY_ID_URL, "resourceowner", "vehiclegeofence", id));
    }

    /**
     * Updates a vehicle geofence
     *
     * @param id
     * @param model
     */
    public void editVehicleGeofence(int id, VehicleGeofenceEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "vehiclegeofence", id));
    }

    /**
     * Removes association between a vehicle and geofence
     *
     * @param id
     */
    public void deleteVehicleGeofence(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "vehiclegeofence", id));
    }

    /**
     * Creates a new geofence
     *
     * @param model
     * @return
     */
    public GeofenceModel createGeofence(GeofenceCreateModel model) {
        return automileService.postCall(model, GeofenceModel.class, format(CREATE_URL, "resourceowner", "geofence"));
    }

    /**
     * Get a list of geofences user is associated with
     *
     * @return
     */
    public List<GeofenceModel> getGeofences() {
        return automileService.listCall(GeofenceModel.class, format(LIST_URL, "resourceowner", "geofence"));
    }

    /**
     * Get geofence by id
     *
     * @param id
     * @return
     */
    public GeofenceModel getGeofence(int id) {
        return automileService.getCall(GeofenceModel.class, format(GET_BY_ID_URL, "resourceowner", "geofence", id));
    }

    /**
     * Updates the given geofence with new model
     *
     * @param id
     * @param model
     */
    public void editGeofence(int id, GeofenceEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "geofence", id));
    }

    /**
     * Removes the given geofence
     *
     * @param id
     */
    public void deleteGeofence(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "geofence", id));
    }

    /**
     * Get health indicators for a vehicle
     *
     * @param vehicleId
     * @return
     */
    public VehicleHealth getVehicleHealth(int vehicleId) {
        return automileService.getCall(VehicleHealth.class, format(GET_BY_ID_URL, "resourceowner", "vehiclehealth", vehicleId));
    }

    /**
     * Get health indicators for a vehicle over a period of time
     *
     * @param vehicleId
     * @return
     */
    public VehicleHealth getVehicleHealth(int vehicleId, String datePeriod) {
        return automileService.getCall(VehicleHealth.class, format(VEHICLE_HEALTH_GET_OVER_PERIOD_URL, vehicleId, datePeriod));
    }

    /**
     * Creates a new vehicle inspection
     *
     * @param model
     * @return
     */
    public VehicleInspectionModel createVehicleInspection(VehicleInspectionCreateModel model) {
        return automileService.postCall(model, VehicleInspectionModel.class, format(CREATE_URL, "resourceowner", "vehicleinspection"));
    }

    /**
     * Get all vehicle inspections that the user has access to
     *
     * @param vehicleInspectionId
     * @param vehicleId
     * @param fromDate
     * @param toDate
     * @param excludeArchived
     * @return
     */
    public List<VehicleInspectionModel> getVehicleInspections(Integer vehicleInspectionId, Integer vehicleId,
                                                              LocalDateTime fromDate, LocalDateTime toDate, boolean excludeArchived) {
        List<NameValuePair> params = Lists.newArrayList();
        Optional.ofNullable(vehicleInspectionId).ifPresent(p -> automileService.addParam("vehicleInspectionId", vehicleInspectionId, params));
        Optional.ofNullable(vehicleId).ifPresent(p -> automileService.addParam("vehicleId", vehicleId, params));
        Optional.ofNullable(fromDate).ifPresent(p -> automileService.addParam("fromDate", fromDate, params));
        Optional.ofNullable(toDate).ifPresent(p -> automileService.addParam("toDate", toDate, params));
        automileService.addParam("excludeArchived", excludeArchived, params);
        return automileService.listCall(VehicleInspectionModel.class, format(LIST_URL, "resourceowner", "vehicleinspection"),
                params.toArray(new NameValuePair[params.size()]));
    }

    /**
     * Get a vehicle inspection
     *
     * @param id
     * @return
     */
    public VehicleInspectionModel getVehicleInspection(int id) {
        return automileService.getCall(VehicleInspectionModel.class, format(GET_BY_ID_URL, "resourceowner", "vehicleinspection", id));
    }

    /**
     * Export a vehicle inspection in pdf format via email
     *
     * @param id
     * @param model
     */
    public void emailVehicleInspectionExport(int id, VehicleInspectionExportModel model) {
        automileService.postCall(model, null, format(EDIT_URL, "resourceowner", "vehicleinspection/export", id));
    }

    /**
     * Updates the given vehicle inspection with new model
     *
     * @param id
     * @param model
     */
    public void editVehicleInspection(int id, VehicleInspectionEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "vehicleinspection", id));
    }

    /**
     * Associate a vehicle with a place
     *
     * @param model
     * @return
     */
    public VehiclePlaceModel createVehiclePlace(VehiclePlaceCreateModel model) {
        return automileService.postCall(model, VehiclePlaceModel.class, format(CREATE_URL, "resourceowner", "vehicleplace"));
    }

    /**
     * Get relationships between the vehicles and the place
     *
     * @param placeId
     * @return
     */
    public List<VehiclePlaceModel> getVehiclePlaces(int placeId) {
        BasicNameValuePair geofenceIdParam = new BasicNameValuePair("placeId", String.valueOf(placeId));
        return automileService.listCall(VehiclePlaceModel.class, format(LIST_URL, "resourceowner", "vehicleplace"), geofenceIdParam);
    }

    /**
     * Get vehicle place
     *
     * @param id
     * @return
     */
    public VehiclePlaceModel getVehiclePlace(int id) {
        return automileService.getCall(VehiclePlaceModel.class, format(GET_BY_ID_URL, "resourceowner", "vehicleplace", id));
    }

    /**
     * Edit the relationship between the vehicle and the place
     *
     * @param id
     * @param model
     */
    public void editVehiclePlace(int id, VehiclePlaceEditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "vehicleplace", id));
    }

    /**
     * Removes association between a vehicle and place
     *
     * @param id
     */
    public void deleteVehiclePlace(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "vehicleplace", id));
    }

    /**
     * Get a the details of the organization the user is assoicates with
     *
     * @return
     */
    public OrganizationModel getOrganization() {
        return automileService.getCall(OrganizationModel.class, format(LIST_URL, "resourceowner", "organization"));
    }

    /**
     * Updates the organization
     *
     * @param model
     */
    public void editOrganization(OrganizationEditModel model) {
        automileService.putCall(model, format(LIST_URL, "resourceowner", "organization"));
    }

    /**
     * Get the organization hierarchy
     *
     * @return
     */
    public OrganizationNodeModel getOrganizationHierarchy() {
        return automileService.getCall(OrganizationNodeModel.class, format(GET_BY_ID_URL, "resourceowner", "organization", "hierarchy"));
    }

    /**
     * Creates a new contact
     *
     * @param model
     * @return
     */
    public Contact3Model createContact3(Contact3CreateModel model) {
        return automileService.postCall(model, Contact3Model.class, format(CREATE_URL, "resourceowner", "contacts3"));
    }

    /**
     * Get a list of all contacts that user is associated with
     *
     * @return
     */
    public List<Contact3Model> getContacts3() {
        return automileService.listCall(Contact3Model.class, format(LIST_URL, "resourceowner", "contacts3"));
    }

    /**
     * Get a contact by id
     *
     * @param id
     * @return
     */
    public Contact3DetailModel getContact3(int id) {
        return automileService.getCall(Contact3DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts3", id));
    }

    /**
     * Updates the given contact id
     *
     * @param id
     * @param model
     */
    public void editContact3(int id, Contact3EditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "contacts3", id));
    }

    /**
     * Removes the given contact
     *
     * @param id
     */
    public void deleteContact3(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "contacts3", id));
    }

    /**
     * Get the contact representing myself
     *
     * @return
     */
    public Contact3DetailModel getContact3Me() {
        return automileService.getCall(Contact3DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts3", "me"));
    }

    /**
     * Get contact profile image
     *
     * @return
     */
    public Contact3DetailModel getContact3MeImage() {
        return automileService.getCall(Contact3DetailModel.class, format(GET_BY_ID_URL, "resourceowner", "contacts3", "me/image"));
    }

    /**
     * Upload contact profile image
     *
     * @param data
     * @return
     */
    public void editContact3UploadImage(byte[] data) {
        automileService.postCall(data, null, format(EDIT_URL, "resourceowner", "contacts3/me", "uploadimage"));
    }

    /**
     * Removes the users profile image
     *
     * @return
     */
    public void editContact3RemoveImage() {
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "contacts3/me", "removeimage"));
    }

    /**
     * Updates the default vehicle
     *
     * @param vehicleId
     * @return
     */
    public void editContact3MeUpdateDefaultVehicle(int vehicleId) {
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "contacts3/me/updatedefaultvehicle", vehicleId));
    }

    /**
     * Updates the default vehicle
     *
     * @param vehicleId
     * @return
     */
    public void editContact3UpdateDefaultVehicle(int vehicleId) {
        automileService.putCall(null, format(EDIT_URL, "resourceowner", "contacts3", "updatedefaultvehicle", vehicleId));
    }

    /**
     * Add and remove custom categories
     *
     * @param model
     * @return
     */
    public void editContact3CustomCategories(CustomCategoryPostModel model) {
        automileService.postCall(model, null, format(CREATE_URL, "resourceowner", "contacts3/customcategory"));
    }

    /**
     * Get a custom category
     *
     * @return
     */
    public CustomCategoryModel getContacts3CustomCategory(int contactId, int customCategoryId) {
        return automileService.getCall(CustomCategoryModel.class, format(GET_CONTACT3_CUSTOM_CATEGORY_URL, contactId, customCategoryId));
    }

    /**
     * Get a all custom categories
     *
     * @return
     */
    public List<CustomCategoryModel> getContacts3CustomCategories(int contactId) {
        return automileService.listCall(CustomCategoryModel.class, format(GET_CONTACT3_CUSTOM_CATEGORIES_URL, contactId));
    }

    /**
     * Creates a new geofence
     *
     * @param model
     * @return
     */
    public GeofenceModel2 createGeofence2(GeofenceCreate2Model model) {
        return automileService.postCall(model, GeofenceModel2.class, format(CREATE_URL, "resourceowner", "geofence2"));
    }

    /**
     * Get a list of geofences user is associated with
     *
     * @return
     */
    public List<GeofenceModel2> getGeofences2() {
        return automileService.listCall(GeofenceModel2.class, format(LIST_URL, "resourceowner", "geofence2"));
    }

    /**
     * Get geofence by id
     *
     * @param id
     * @return
     */
    public GeofenceModel getGeofence2(int id) {
        return automileService.getCall(GeofenceModel.class, format(GET_BY_ID_URL, "resourceowner", "geofence2", id));
    }

    /**
     * Updates the given geofence with new model
     *
     * @param id
     * @param model
     */
    public void editGeofence2(int id, GeofenceEditModel2 model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "geofence2", id));
    }

    /**
     * Removes the given geofence
     *
     * @param id
     */
    public void deleteGeofence2(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "geofence2", id));
    }

    /**
     * Creates a new place for organization
     *
     * @param model
     * @return
     */
    public PlaceModel3 createPlace2(Place3CreateModel model) {
        return automileService.postCall(model, PlaceModel3.class, format(CREATE_URL, "resourceowner", "place3"));
    }

    /**
     * Gets all places for organization
     *
     * @return
     */
    public List<PlaceModel3> getPlaces3() {
        return automileService.listCall(PlaceModel3.class, format(LIST_URL, "resourceowner", "place3"));
    }

    /**
     * Gets place by placeid
     *
     * @param id
     * @return
     */
    public PlaceModel3 getPlace3(int id) {
        return automileService.getCall(PlaceModel3.class, format(GET_BY_ID_URL, "resourceowner", "place3", id));
    }

    /**
     * Updates the given place with new model
     *
     * @param id
     * @param model
     */
    public void editPlace3(int id, Place3EditModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "place3", id));
    }

    /**
     * Removes the given place
     *
     * @param id
     */
    public void deletePlace3(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "place3", id));
    }

    /**
     * Creates a new trigger
     *
     * @param model
     * @return
     */
    public TriggerDetailModel2 createTrigger2(TriggerCreateModel2 model) {
        return automileService.postCall(model, TriggerDetailModel2.class, format(CREATE_URL, "resourceowner", "triggers2"));
    }

    /**
     * Get all triggers
     *
     * @return
     */
    public List<TriggerModel2> getTriggers2() {
        return automileService.listCall(TriggerModel2.class, format(LIST_URL, "resourceowner", "triggers2"));
    }

    /**
     * Get a trigger by id
     *
     * @param id
     * @return
     */
    public TriggerDetailModel2 getTrigger2(int id) {
        return automileService.getCall(TriggerDetailModel2.class, format(GET_BY_ID_URL, "resourceowner", "triggers2", id));
    }

    /**
     * Updates the given trigger id
     *
     * @param id
     * @param model
     */
    public void editTrigger2(int id, TriggerEditModel2 model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "triggers2", id));
    }

    /**
     * Mute the given trigger id
     *
     * @param id
     * @param model
     */
    public void editTrigger2Mute(int id, TriggerEditMutedUntilModel model) {
        automileService.putCall(model, format(EDIT_URL, "resourceowner", "triggers2/mute", id));
    }

    /**
     * Unmute the given trigger id
     *
     * @param id
     */
    public void editTrigger2Unmute(int id) {
        automileService.putCall("", format(EDIT_URL, "resourceowner", "triggers2/unmute", id));
    }

    /**
     * Move all users push triggers to userdevice
     *
     * @param model
     */
    public void editTrigger2MovePush(MovePushTriggers model) {
        automileService.postCall(model, null, format(CREATE_URL, "resourceowner", "triggers2/movepush"));
    }

    /**
     * Removes the given trigger
     *
     * @param id
     */
    public void deleteTrigger2(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "triggers2", id));
    }

}