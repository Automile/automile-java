![Automile](https://content.automile.com/automile_logo_symbol_64x64.png "Automile 291 Alma Street, Palo Alto, California 943 01, US")

# Official Automile REST API for Java
Automile offers a simple, smart, cutting-edge telematics solution for businesses to track and manage their business vehicles. Automile is a next-gen IoT solution and the overall experience is unmatched. Business of all sizes love to use Automile to get fleet intelligence whether it is understanding driving behavior, recording vehicle defects and expenses, tracking vehicles real time or securing vehicles from un-authorized use. 

Automile gives developers a simple way to build services and applications through its unique application program interface (API).  Our simple REST based API support more than 400 core features empowering developers to access more data and enabling tighter integration to build apps for the connected ecosystem. 

API information can be found at https://api.automile.com. If you need any help, we are here to help. Simply email us at support@automile.com or chat with us.

The latest OpenAPI (fka Swagger) specification may be found at: https://api.automile.com/swagger/docs/v1

:yum:

**This library allows you to quickly and easily use the Automile API via PHP.**

**This SDK is currently in beta. If you need help:**

* **Use the [Issue Tracker](https://github.com/Automile/automile-php/issues) to report bugs or missing functionality in this library.**
* **Send an email to [support@automile.com](support@automile.com) to request help with our API or your account.**

## Prerequisites

- Java >= 1.8

## Gradle

To install the package via [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html), run the following command:

```
gradlew install
```

Add installed dependency to your project via Gradle
```
compile('com.automile:client:1.0.0')

```
Add installed dependency to your project via Maven
```
<dependency>
    <groupId>com.automile</groupId>
    <artifactId>client</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quickstart

First, let's initialize the client.

It's recommended to store the authentication token for futher use,
otherwise the client would have to retrieve new token each time upon initialization.

Java should be allowed to read and write into the token storage directory for the client to function properly.

```java
public class Test {
   public static void main(String[] args) {
       //Using username+pass
       AutomileClient cl1 = AutomileClient.builder().
               username("username").
               password("password").
               clientId("clientId").
               clientSecret("clientSecret").build();
       cl1.authorize();
       
       //Or using stored token data
       
       //authentication token json data
       String jsondata = "";//read data from file
       AutomileClient cl2 = AutomileClient.builder().build();
       cl2.authorize(jsondata);
   }
}
```

If something's not right, please report the issue to the [Issue Tracker](https://github.com/Automile/automile-java/issues),
and we'll get to it as soon as possible. 

**Note:** Automile is currently accepting username and password authentication for users belonging to private clients you are creating.

## Methods
* [Company](#company-methods)
* [Company Contact](#company-contact-methods)
* [Company Vehicle](#company-vehicle-methods)  
* [Contact](#contact-methods)  
* [Vehicle](#vehicle-methods)  
* [Contact Vehicle](#contact-vehicle-methods)
* [Expense Report](#expense-report-methods)
* [Expense Report Row](#expense-report-row-methods)
* [Expense Report Row Content](#expense-report-row-content-methods)
* [IMEI Config](#imei-config-methods)
* [IMEI Event](#imei-event-methods)
* [Place](#place-methods)
* [Publish Subscribe](#publish-subscribe-methods)
* [Report](#report-methods)
* [Task](#task-methods)
* [Task Message](#task-message-methods)
* [Trigger Message History](#trigger-message-history-methods)
* [Trigger](#trigger-methods)
* [User](#user-methods)
* [Trip](#trip-methods)
* [User Device](#user-device-methods)
* [Vehicle Defect Comment](#vehicle-defect-comment-methods)
* [Vehicle Defect Type](#vehicle-defect-type-methods)
* [Vehicle Geofence](#vehicle-geofence-methods)
* [Geofence](#geofence-methods)
* [Vehicle Health](#vehicle-health-methods)
* [Vehicle Inspection](#vehicle-inspection-methods)
* [Vehicle Place](#vehicle-place-methods)
* [Organization](#organization-methods)

### Company Methods

#### Create company
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CompanyCreateModel model = new CompanyCreateModel();
    cl.createCompany(model);
}
```
#### Get companies
```java 
public void test(AutomileClient cl){
    cl.getCompanies();
}
```
#### Get company
```java 
public void test(AutomileClient cl){
    cl.getCompany(1);
}
```
#### Edit company
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CompanyEditModel model = new CompanyEditModel();
    cl.editCompany(1, model);
}
```
#### Delete company
```java 
public void test(AutomileClient cl){
    cl.deleteCompany(1);
}
```
  
### Company Contact Methods

#### Create company contact
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CompanyContactCreateModel model = new CompanyContactCreateModel();
    cl.createCompanyContact(model);
}
```
#### Get companies contacts
```java 
public void test(AutomileClient cl){
    cl.getCompanyContacts();
}
```
#### Get company contacts
```java 
public void test(AutomileClient cl){
    cl.getCompanyContact(1);
}
```
#### Edit company contact
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CompanyContactEditModel model = new CompanyContactEditModel();
    cl.editCompanyContact(1, model);
}
```
#### Delete company contact
```java 
public void test(AutomileClient cl){
    cl.deleteCompanyContact(1);
}
```
  
### Company Vehicle Methods

#### Create company vehicle
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CompanyVehicleCreateModel model = new CompanyVehicleCreateModel();
    cl.createCompanyVehicle(model);
}
```
#### Get company vehicles
```java 
  public void test(AutomileClient cl){
      cl.getCompanyVehicles();
  }
```
#### Get company vehicle
```java 
  public void test(AutomileClient cl){
      cl.getCompanyVehicle(1);
  }
```
#### Edit company vehicle
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CompanyVehicleEditModel model = new CompanyVehicleEditModel();
    cl.editCompanyVehicle(1, model);
}
```
#### Delete company vehicle
```java 
public void test(AutomileClient cl){
    cl.deleteCompanyVehicle(1);
}
```

### Contact Methods

#### Create contact
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    Contact2CreateModel model = new Contact2CreateModel();
    cl.createContact2(model);
}
```
#### Get contacts
```java 
public void test(AutomileClient cl){
    cl.getContacts2();
    }
```
#### Get contact
```java 
public void test(AutomileClient cl){
    cl.getContact2(1);
}
```
#### Edit contact
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    Contact2EditModel model = new Contact2EditModel();
    cl.editContact2(1, model);
}
```
#### Delete contact
```java 
public void test(AutomileClient cl){
    cl.deleteContact2(1);
}
```
#### Get me
```java 
public void test(AutomileClient cl){
    cl.getContact2Me();
}
```
#### Get profile image
```java 
public void test(AutomileClient cl){
    cl.getContact2MeImage();
}
```
#### Upload profile image
```java 
public void test(AutomileClient cl, byte[] data){
    cl.editContact2UploadImage(data);
}
```
#### Remove profile image
```java 
public void test(AutomileClient cl){
    cl.editContact2RemoveImage();
}
```
#### Update the default vehicle
```java 
public void test(AutomileClient cl){
    cl.editContact2MeUpdateDefaultVehicle(1);
    //or
    cl.editContact2UpdateDefaultVehicle(1);
}
```
#### Add and remove custom categories
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    CustomCategoryPostModel model = new CustomCategoryPostModel();
    cl.editContact2CustomCategories(1, model);
}
```
#### Get a custom category
```java 
public void test(AutomileClient cl){
    cl.getContacts2CustomCategory(1, 1);
}
```
#### Get a all custom categories
```java 
public void test(AutomileClient cl){
    cl.getContacts2CustomCategories(1);
}
```

### Vehicle Methods

#### Create vehicle
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    Vehicle2CreateModel model = new Vehicle2CreateModel();
    cl.createVehicle2(model);
}
```
#### Get vehicles
```java 
public void test(AutomileClient cl){
    cl.getVehicles2();
}
```
#### Get vehicle
```java 
public void test(AutomileClient cl){
    cl.getVehicle2(1);
}
```
#### Edit vehicle
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    Vehicle2EditModel model = new Vehicle2EditModel();
    cl.editVehicle2(1, model);
}
```
#### Delete vehicle
```java 
public void test(AutomileClient cl){
    cl.deleteVehicle2(1);
}
```
#### Get vehicle information
```java 
public void test(AutomileClient cl){
    cl.getVehicle2VehicleInformation("identifier", 1);
}
```  
#### Get position and status of all vehicles that the user has access to
```java 
public void test(AutomileClient cl){
    cl.getVehicles2Status();
}
```
#### Check-in to a vehicle
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleCheckInModel model = new VehicleCheckInModel();
    cl.editVehicle2Checkin(model);
}
```  
#### Check-out from a vehicle
```java 
public void test(AutomileClient cl){
    cl.editVehicle2Checkout();
}
```

### Contact Vehicle Methods

#### Create a new vehicle contact and associates it with user
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ContactVehicleDetailModel model = new ContactVehicleDetailModel();
    cl.createContactVehicle(model);
}
```
#### Get all vehicle contacts that the logged in user has access to
```java 
public void test(AutomileClient cl){
    cl.getContactVehicles();
}
```
#### Get contacts by vehicle id
```java 
public void test(AutomileClient cl){
    cl.getContactVehicle(1);
}
```
#### Update the given contact vehicle id
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ContactVehicleEditModel model = new ContactVehicleEditModel();
    cl.editContactVehicle(1, model);
}
```
#### Delete contact vehicle
```java 
public void test(AutomileClient cl){
    cl.deleteContactVehicle(1);
}
```

### Expense Report Methods

#### Create an expense report
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ExpenseReportCreateModel model = new ExpenseReportCreateModel();
    cl.createExpenseReport(model);
}
```
#### Get a list of expense reports
```java 
public void test(AutomileClient cl){
    cl.getExpenseReports(1, 1);
}
```
#### Get an expense report
```java 
public void test(AutomileClient cl){
    cl.getExpenseReport(1);
}
```
#### Update the given expense report
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ExpenseReportEditModel model = new ExpenseReportEditModel();
    cl.editExpenseReport(1, model);
}
```
#### Delete expense report
```java 
public void test(AutomileClient cl){
    cl.deleteExpenseReport(1);
}
```
#### Email expense report in pdf format
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    EmailExpenseReportModel model = new EmailExpenseReportModel();
    cl.emailExpenseReportExport(1, model);
}
```
#### Email expense reports in pdf format
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    EmailExpenseReportsModel model = new EmailExpenseReportsModel();
    cl.emailExpenseReportExport(model);
}
```
#### Carry out optical character recognization on image fragments and return the response
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    OCRRequest model = new OCRRequest();
    cl.createExpenseReportOCR(model);
}
```

### Expense Report Row Methods

#### Create an expense report row
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ExpenseReportRowModel model = new ExpenseReportRowModel();
    cl.createExpenseReportRow(model);
}
```
#### Get an expense report rows
```java 
public void test(AutomileClient cl){
    cl.getExpenseReportRow(1);
}
```
#### Update the given expense report row
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ExpenseReportRowEditModel model = new ExpenseReportRowEditModel();
    cl.editExpenseReportRow(1, model);
}
```
#### Delete expense report row
```java 
public void test(AutomileClient cl){
    cl.deleteExpenseReportRow(1);
}
```

### Expense Report Row Content Methods

#### Create an expense report row content
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ExpenseReportRowContentCreateModel model = new ExpenseReportRowContentCreateModel();
    cl.createExpenseReportRowContent(model);
}
```
#### Get a list of expense report row contents
```java 
public void test(AutomileClient cl){
    cl.getExpenseReportRowContents(1);
}
```
#### Get an expense report row content
```java 
public void test(AutomileClient cl){
    cl.getExpenseReporRowContent(1);
}
```
#### Update the given expense report row content
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ExpenseReportRowContentEditModel model = new ExpenseReportRowContentEditModel();
    cl.editExpenseReportRowContent(1, model);
}
```
#### Delete expense report row content
```java 
public void test(AutomileClient cl){
    cl.deleteExpenseReport(1);
}
```

### IMEI Config Methods

#### Create IMEI config
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    IMEIConfigCreateModel model = new IMEIConfigCreateModel();
    cl.createIMEIConfig(model);
}
```
#### Get IMEI configs
```java 
public void test(AutomileClient cl){
    cl.getIMEIConfigs(true);
}
```
#### Get IMEI config
```java 
public void test(AutomileClient cl){
    cl.getIMEIConfig(1, true);
}
```
#### Edit IMEI config
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    IMEIConfigEditModel model = new IMEIConfigEditModel();
    cl.editIMEIConfig(1, model);
}
```
#### Delete IMEI config
```java 
public void test(AutomileClient cl){
    cl.deleteIMEIConfig(1);
}
```

### IMEI Event Methods

#### Get IMEI events
```java 
public void test(AutomileClient cl){
    cl.getIMEIEvents();
}
```
#### Get MIL IMEI event
```java 
public void test(AutomileClient cl){
    cl.getMILIMEIEvent(1);
}
```
#### Get GTC IMEI event
```java 
public void test(AutomileClient cl){
    cl.getDTCIMEIEvent(1);
}
```
#### Get IMEI event status
```java 
public void test(AutomileClient cl){
    cl.getIMEIEventStatus(1);
}
```

### Place Methods

#### Create place
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    Place2CreateModel model = new Place2CreateModel();
    cl.createPlace2(model);
}
```
#### Get places
```java 
public void test(AutomileClient cl){
    cl.getPlaces();
}
```
#### Get place
```java 
public void test(AutomileClient cl){
    cl.getPlace(1);
}
```
#### Edit place
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    Place2EditModel model = new Place2EditModel();
    cl.editPlace2(1, model);
}
```
#### Delete place
```java 
public void test(AutomileClient cl){
    cl.deletePlace(1);
}
```
  
### Publish Subscribe Methods

#### Create an a publish subscribe record
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    PublishSubscribeCreateModel model = new PublishSubscribeCreateModel();
    cl.createPublishSubscribe(model);
}
```
#### Get all publish subscribe records 
```java 
public void test(AutomileClient cl){
    cl.getPublishSubscribes();
}
```
#### Get the publish subscribe by record id
```java 
public void test(AutomileClient cl){
    cl.getPublishSubscribe(1);
}
```
#### Edit the given publish subscribe record
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    PublishSubscribeEditModel model = new PublishSubscribeEditModel();
    cl.editPublishSubscribe(1, model);
}
```
#### Delete the given publish subscribe record
```java 
public void test(AutomileClient cl){
    cl.deletePublishSubscribe(1);
}
```
#### Publish a test trip start to the publish subscribe endpoint
```java 
public void test(AutomileClient cl){
    cl.testTripStart(1, 1);
}
```
#### Publish a test trip end to the publish subscribe endpoint
```java 
public void test(AutomileClient cl){
    cl.testTripEnd(1, 1);
}
```
#### Publish a created vehicle to the publish subscribe endpoint
```java 
public void test(AutomileClient cl){
    cl.testVehicleCreated(1, 1);
}
```
#### Publish a modified vehicle to the publish subscribe endpoint
```java 
public void test(AutomileClient cl){
    cl.testVehicleModified(1, 1);
}
```
#### Publish a created driver / contact to the publish subscribe endpoint
```java 
public void test(AutomileClient cl){
    cl.testContactCreated(1, 1);
}
```
#### The contact end was published to your publish subscribe record endpoint
```java 
public void test(AutomileClient cl){
    cl.testContactModified(1, 1);
}
```

### Report Methods

#### Get a trip summary report
```java 
public void test(AutomileClient cl){
    cl.getReportsTripSummary("dateperiod");
}
```
#### Get a trip summary report filtered by vehicle
```java 
public void test(AutomileClient cl){
    cl.getReportsTripSummary("dateperiod", 1);
}
```
#### Get vehicles summary report
```java 
public void test(AutomileClient cl){
    cl.getReportsVehiclesSummary("dateperiod");
}
```
####  Get vehicles summary report filtered by vehicle
```java 
public void test(AutomileClient cl){
    cl.getReportVehicleSummary("dateperiod", 1);
}
```
#### Export a trip report in pdf format
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    EmailTripReportModel model = new EmailTripReportModel();
    cl.emailTripReport(1, model);
}
```

### Task Methods

#### Create task
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TaskCreateModel model = new TaskCreateModel();
    cl.createTask(model);
}
```
#### Get tasks
```java 
public void test(AutomileClient cl){
    cl.getTasks();
}
```
#### Get task details
```java 
public void test(AutomileClient cl){
    cl.getTask(1);
}
```
#### Edit task
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TaskEditModel model = new TaskEditModel();
    cl.editTask(1, model);
}
```

### Task Message Methods

#### Create task message
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TaskMessageModel model = new TaskMessageModel();
    cl.createTaskMessage(model);
}
```
#### Get task message
```java 
public void test(AutomileClient cl){
    cl.getTaskMessage(1);
}
```
#### Edit task message
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TaskMessageEditModel model = new TaskMessageEditModel();
    cl.editTaskMessage(1, model);
}
```

### Trigger Message History Methods

#### Get all trigger messages that the logged in user has access to
```java 
public void test(AutomileClient cl){
    cl.getTriggerMessageHistories();
}
```
#### Get trigger messages by trigger id
```java 
public void test(AutomileClient cl){
    cl.getTriggerMessageHistories(1);
}
```

### Trigger Methods

#### Create trigger
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TriggerCreateModel model = new TriggerCreateModel();
    cl.createTrigger(model);
}
```
#### Get triggers
```java 
public void test(AutomileClient cl){
    cl.getTriggers();
}
```
#### Get trigger
```java 
public void test(AutomileClient cl){
    cl.getTrigger(1);
}
```
#### Edit trigger
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TriggerEditModel model = new TriggerEditModel();
    cl.editTrigger(1, model);
}
```
#### Mute trigger
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TriggerEditMutedUntilModel model = new TriggerEditMutedUntilModel();
    cl.editTriggerMute(1, model);
}
```
#### Unmute trigger
```java 
public void test(AutomileClient cl){
    cl.editTriggerUnmute(1);
}
```
####  Move all users push triggers to userdevice
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    MovePushTriggers model = new MovePushTriggers();
    cl.editTriggerMovePush(model);
}
```
#### Delete trigger
```java 
public void test(AutomileClient cl){
    cl.deleteTrigger(1);
}
```
  
### User Methods

#### Change the password
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ChangePasswordModel model = new ChangePasswordModel();
    cl.editUserChangePassword(model);
}
```
#### Change the username
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ChangeUserNameModel model = new ChangeUserNameModel();
    cl.editUserChangeUsername(model);
}
```
#### Get user
```java 
public void test(AutomileClient cl){
    cl.getUser();
}
```
####  Check if the user has a password set
```java 
public void test(AutomileClient cl){
    cl.getUserExistingPassword();
}
```
#### Reset the password by SMS for the current user logged in
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    ResetPasswordUserModel model = new ResetPasswordUserModel();
    cl.editUserResetPassword(model);
}
```
### Trip Methods

#### Get all trips
```java 
public void test(AutomileClient cl){
    cl.getTrips(1, 1, true);
}
```
#### Get trip
```java 
public void test(AutomileClient cl){
    cl.getTrip(1);
}
```
#### Edit trip
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TripEditModel model = new TripEditModel();
    cl.editTrip(1, model);
}
```
#### Get the trip start and stop geo locations
```java 
public void test(AutomileClient cl){
    cl.getTripGeoStartEnd(1);
}
```
#### Get the vehicle speed if it's reported by the vehicle
```java 
public void test(AutomileClient cl){
    cl.getTripSpeed(1);
}
```
#### Get the vehicle engine rpm if it's reported by the vehicle
```java 
public void test(AutomileClient cl){
    cl.getTripRPM(1);
}
```
#### Get the vehicle ambient (outside) temperature if it's reported by the vehicle
```java 
public void test(AutomileClient cl){
    cl.getTripAmbientTemperature(1);
}
```
#### Get the vehicle current fuel level if it's reported by the vehicle
```java 
public void test(AutomileClient cl){
    cl.getTripFuelLevelInput(1);
}
```
#### Get the engine coolant temperature if it's reported by the vehicle
```java 
public void test(AutomileClient cl){
    cl.getTripEngineCoolantTemperature(1);
}
```
#### This will get the raw PID data if the vehicle has reported that it is being
```java 
public void test(AutomileClient cl){
    cl.getTripPID(1);
}
```
#### Get the gps locations for the vehicle, includes position and heading
```java 
public void test(AutomileClient cl){
    cl.getTripGeo(1, false, 1);
}
```
#### Gets Google static map URL with the trip route as a polyline
```java 
public void test(AutomileClient cl){
    cl.getTripGoogleUrlToStaticMapEncodedPolyline(1, 1, 1);
}
```
#### Updates the last trip with trip notes
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TripAddNoteModel model = new TripAddNoteModel();
    cl.editTripAddNotesToLastTrip(1, model);
}
```
#### Updates the given trip with given contactid
```java 
public void test(AutomileClient cl){
    cl.editTripSetDriverOnTrip(1, 1);
}
```
#### Mark trips as synchronized, synchronized trips will not be returned when fetching trips
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    TripSynchronized model = new TripSynchronized();
    cl.editTripSynchronized(1, model);
}
```
#### Get the details about the trip including driving events, speeding and idling
```java 
public void test(AutomileClient cl){
    cl.getTripDetails(1);
}
```
#### Get the advanced details about the trip including driving events, speeding, idling, speed and rpm data series
```java 
public void test(AutomileClient cl){
    cl.getTripAdvanced(1);
}
```

## User Device Methods

#### Create user device
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    UserDeviceCreateModel model = new UserDeviceCreateModel();
    cl.createUserDevice(model);
}
```
#### Get user devices
```java 
public void test(AutomileClient cl){
    cl.getUserDevices();
}
```
#### Get user device
```java 
public void test(AutomileClient cl){
    cl.getUserDevice(1);
}
```
#### Edit user device
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    UserDeviceEditModel model = new UserDeviceEditModel();
    cl.editUserDevice(1, model);
}
```
#### Delete user device
```java 
public void test(AutomileClient cl){
    cl.deleteUserDevice(1);
}
```

### Vehicle Defect Comment Methods

#### Create vehicle defect comment
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleDefectCommentsCreateModel model = new VehicleDefectCommentsCreateModel();
    cl.createVehicleDefectComment(model);
}
```
#### Get vehicle defect comments
```java 
public void test(AutomileClient cl){
    cl.getVehicleDefectComments();
}
```
#### Get vehicle defect comment
```java 
public void test(AutomileClient cl){
    cl.getVehicleDefectComment(1);
}
```
#### Edit vehicle defect comment
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleDefectCommentsEditModel model = new VehicleDefectCommentsEditModel();
    cl.editVehicleDefectComment(1, model);
}
```

### Vehicle Defect Type Methods

#### Get vehicle defect types
```java 
public void test(AutomileClient cl){
    cl.getVehicleDefectTypes();
}
```

### Vehicle Geofence Methods

#### Associate a vehicle with a geofence
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleGeofenceCreateModel model = new VehicleGeofenceCreateModel();
    cl.createVehicleGeofence(model);
}
```
#### Get vehicle geofences
```java 
public void test(AutomileClient cl){
    cl.getVehicleGeofences(1);
}
```
#### Get vehicle geofence
```java 
public void test(AutomileClient cl){
    cl.getVehicleGeofence(1);
}
```
#### Edit vehicle geofence
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleGeofenceEditModel model = new VehicleGeofenceEditModel();
    cl.editVehicleGeofence(1, model);
}
```
#### Remove association between a vehicle and geofence
```java 
public void test(AutomileClient cl){
    cl.deleteVehicleGeofence(1);
}
```

### Geofence Methods

#### Creates a new geofence
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    GeofenceCreateModel model = new GeofenceCreateModel();
    cl.createGeofence(model);
}
```
#### Get geofences
```java 
public void test(AutomileClient cl){
    cl.getGeofences();
}
```
#### Get geofence
```java 
public void test(AutomileClient cl){
    cl.getGeofence(1);
}
```
#### Edit geofence
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    GeofenceEditModel model = new GeofenceEditModel();
    cl.editGeofence(1, model);
}
```
#### Remove the given geofence
```java 
public void test(AutomileClient cl){
    cl.deleteGeofence(1);
}
```

### Vehicle Health Methods

#### Get health indicators for a vehicle
```java 
public void test(AutomileClient cl){
    cl.getVehicleHealth(1);
}
```
#### Get health indicators for a vehicle over a period of time
```java 
public void test(AutomileClient cl){
    cl.getVehicleHealth(1, "datePeriod");
}
```

### Vehicle Inspection Methods

#### Create a new vehicle inspection
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleInspectionCreateModel model = new VehicleInspectionCreateModel();
    cl.createVehicleInspection(model);
}
```
#### Get all vehicle inspections that the user has access to
```java 
public void test(AutomileClient cl){
    cl.getVehicleInspections(1, 1, LocalDateTime.now(), LocalDateTime.now(), true);
}
```
#### Get a vehicle inspection
```java 
public void test(AutomileClient cl){
    cl.getVehicleInspection(1);
}
```
#### Export a vehicle inspection in pdf format via email
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleInspectionExportModel model = new VehicleInspectionExportModel();
    cl.emailVehicleInspectionExport(1, model);
}
```
#### Updates the given vehicle inspection with new model
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehicleInspectionEditModel model = new VehicleInspectionEditModel();
    cl.editVehicleInspection(1, model);
}
```

### Vehicle Place Methods

#### Associate a vehicle with a place
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehiclePlaceCreateModel model = new VehiclePlaceCreateModel();
    cl.createVehiclePlace(model);
}
```
#### Get relationships between the vehicles and the place
```java 
public void test(AutomileClient cl){
    cl.getVehiclePlaces(1);
}
```
#### Get vehicle place
```java 
public void test(AutomileClient cl){
    cl.getVehiclePlace(1);
}
```
#### Edit the relationship between the vehicle and the place
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    VehiclePlaceEditModel model = new VehiclePlaceEditModel();
    cl.editVehiclePlace(1, model);
}
```
#### Remove association between a vehicle and place
```java 
public void test(AutomileClient cl){
    cl.deleteVehiclePlace(1);
}
```

## Organization Methods

#### Get a the details of the organization the user is assoicates with
```java 
public void test(AutomileClient cl){
    cl.getOrganization();
}
```
#### Edit organization
```java 
public void test(AutomileClient cl){
    //init model and set fields using model.setXXX
    OrganizationEditModel model = new OrganizationEditModel();
    cl.editOrganization(1, model);
}
```
#### Get the organization hierarchy
```java 
public void test(AutomileClient cl){
    cl.getOrganizationHierarchy();
}
```