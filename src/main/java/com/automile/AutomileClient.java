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
import com.automile.model.ContactModel;
import com.automile.model.ContactVehicleCreateModel;
import com.automile.model.ContactVehicleDetailModel;
import com.automile.model.ContactVehicleEditModel;
import com.automile.model.ExpenseReportCreateModel;
import com.automile.model.ExpenseReportEditModel;
import com.automile.model.ExpenseReportModel;
import com.automile.model.ExpenseReportRowContentCreateModel;
import com.automile.model.ExpenseReportRowContentEditModel;
import com.automile.model.ExpenseReportRowContentModel;
import com.automile.model.ExpenseReportRowCreateModel;
import com.automile.model.ExpenseReportRowEditModel;
import com.automile.model.ExpenseReportRowModel;
import com.automile.model.IMEIConfigCreateModel;
import com.automile.model.IMEIConfigDetailModel;
import com.automile.model.IMEIConfigEditModel;
import com.automile.model.IMEIConfigModel;
import com.automile.model.Place2CreateModel;
import com.automile.model.Place2EditModel;
import com.automile.model.PlaceCreateModel;
import com.automile.model.PlaceEditModel;
import com.automile.model.PlaceModel;
import com.automile.model.PublishSubscribeCreateModel;
import com.automile.model.PublishSubscribeEditModel;
import com.automile.model.PublishSubscribeModel;
import com.automile.model.TaskCreateModel;
import com.automile.model.TaskDetailModel;
import com.automile.model.TaskEditModel;
import com.automile.model.TaskMessageCreateModel;
import com.automile.model.TaskMessageEditModel;
import com.automile.model.TaskMessageModel;
import com.automile.model.TaskModel;
import com.automile.model.TriggerCreateModel;
import com.automile.model.TriggerDetailModel;
import com.automile.model.TriggerEditModel;
import com.automile.model.TriggerMessageHistoryModel;
import com.automile.model.TriggerModel;
import com.automile.model.UserDeviceCreateModel;
import com.automile.model.UserDeviceEditModel;
import com.automile.model.UserDeviceModel;
import com.automile.model.Vehicle2CreateModel;
import com.automile.model.Vehicle2DetailModel;
import com.automile.model.Vehicle2EditModel;
import com.automile.model.Vehicle2Model;
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

import static com.automile.AutomileConfig.CREATE_URL;
import static com.automile.AutomileConfig.DELETE_URL;
import static com.automile.AutomileConfig.EDIT_URL;
import static com.automile.AutomileConfig.GET_BY_ID_URL;
import static com.automile.AutomileConfig.LIST_URL;
import static com.automile.AutomileConfig.PUBLISH_SUBSCRIBE_TEST_URL;
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
        return automileService.createCall(model, CompanyDetailModel.class, format(CREATE_URL, "resourceowner", "companies"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "companies", id));
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
        return automileService.createCall(model, CompanyContactDetailModel.class, format(CREATE_URL, "resourceowner", "companycontact"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "companycontact", id));
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
        return automileService.createCall(model, CompanyVehicleModel.class, format(CREATE_URL, "resourceowner", "companyvehicle"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "companyvehicle", id));
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
        return automileService.createCall(model, Contact2Model.class, format(CREATE_URL, "resourceowner", "contacts2"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "contacts2", id));
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
     * Creates a new vehicle
     *
     * @param model
     * @return
     */
    public Vehicle2Model createVehicle2(Vehicle2CreateModel model) {
        return automileService.createCall(model, Vehicle2Model.class, format(CREATE_URL, "resourceowner", "vehicles2"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "vehicles2", id));
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
     * Creates a new vehicle contact and associates it with user
     *
     * @param model
     * @return
     */
    public ContactVehicleDetailModel createContactVehicle(ContactVehicleCreateModel model) {
        return automileService.createCall(model, ContactVehicleDetailModel.class, format(CREATE_URL, "resourceowner", "contactvehicle"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "contactvehicle", id));
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
        return automileService.createCall(model, ExpenseReportModel.class, format(CREATE_URL, "resourceowner", "expensereport"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "expensereport", id));
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
     * Create an expense report row
     *
     * @param model
     * @return
     */
    public ExpenseReportRowModel createExpenseReportRow(ExpenseReportRowCreateModel model) {
        return automileService.createCall(model, ExpenseReportRowModel.class, format(CREATE_URL, "resourceowner", "expensereportrow"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "expensereportrow", id));
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
     * Create an expense report row
     *
     * @param model
     * @return
     */
    public ExpenseReportRowContentModel createExpenseReportRowContent(ExpenseReportRowContentCreateModel model) {
        return automileService.createCall(model, ExpenseReportRowContentModel.class, format(CREATE_URL, "resourceowner", "expensereportrowcontent"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "expensereportrowcontent", id));
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
        return automileService.createCall(model, IMEIConfigModel.class, format(CREATE_URL, "resourceowner", "imeiconfigs"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "imeiconfigs", id));
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
     * Creates a new place
     *
     * @param model
     * @return
     */
    public PlaceModel createPlace(PlaceCreateModel model) {
        return automileService.createCall(model, PlaceModel.class, format(CREATE_URL, "resourceowner", "place"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "place", id));
    }

    /**
     * Removes the given company
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
        return automileService.createCall(model, PlaceModel.class, format(CREATE_URL, "resourceowner", "place2"));
    }

    /**
     * Updates the given place with new model
     *
     * @param id
     * @param model
     */
    public void editPlace2(int id, Place2EditModel model) {
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "place2", id));
    }


    /**
     * Create an a publish subscribe record
     *
     * @param model
     * @return
     */
    public PublishSubscribeModel createPublishSubscribe(PublishSubscribeCreateModel model) {
        return automileService.createCall(model, PublishSubscribeModel.class, format(CREATE_URL, "resourceowner", "publishsubscribe"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "publishsubscribe", id));
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
     * Create a task
     *
     * @param model
     * @return
     */
    public TaskDetailModel createTask(TaskCreateModel model) {
        return automileService.createCall(model, TaskDetailModel.class, format(CREATE_URL, "resourceowner", "task"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "task", id));
    }

    /**
     * Create a task message
     *
     * @param model
     * @return
     */
    public TaskMessageModel createTaskMessage(TaskMessageCreateModel model) {
        return automileService.createCall(model, TaskMessageModel.class, format(CREATE_URL, "resourceowner", "taskmessage"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "taskmessage", id));
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
        return automileService.createCall(model, TriggerDetailModel.class, format(CREATE_URL, "resourceowner", "triggers"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "triggers", id));
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
     * Creates a new user device
     *
     * @param model
     * @return
     */
    public UserDeviceModel createUserDevice(UserDeviceCreateModel model) {
        return automileService.createCall(model, UserDeviceModel.class, format(CREATE_URL, "resourceowner", "userdevice"));
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
        automileService.editCall(model, format(EDIT_URL, "resourceowner", "userdevice", id));
    }

    /**
     * Removes the given user device
     *
     * @param id
     */
    public void deleteUserDevice(int id) {
        automileService.deleteCall(format(DELETE_URL, "resourceowner", "userdevice", id));
    }

}