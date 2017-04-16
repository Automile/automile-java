package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationPermissionBooleans {
    Boolean organizationNodeViewVehicle;
    Boolean organizationNodeCreateEditVehicle;
    Boolean organizationNodeViewGeofence;
    Boolean organizationNodeCreateEditGeofence;
    Boolean organizationNodeViewPOI;
    Boolean organizationNodeCreateEditPOI;
    Boolean organizationNodeViewTrigger;
    Boolean organizationNodeCreateEditTrigger;
    Boolean organizationNodeViewTrip;
    Boolean organizationNodeEditTrip;
    Boolean organizationNodeViewTripIfDriver;
    Boolean organizationNodeEditTripIfDriver;
    Boolean organizationNodeView;
    Boolean organizationNodeViewContact;
    Boolean organizationNodeCreateEditContact;
    Boolean organizationNodeViewSchedule;
    Boolean organizationNodeCreateEditSchedule;
    Boolean organizationNodeViewExpense;
    Boolean organizationNodeCreateEditExpense;
    Boolean organizationView;
    Boolean organizationCreateEditDelete;
}