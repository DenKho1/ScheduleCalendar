package com.mobdeve.s13.kho.denise.schedulecalendar;

import com.google.firebase.firestore.Exclude;

public class FirestoreInvite {

    private String Guest;

    private String Host;

    private String EventID;

    private String EventName;

    private String EventDate;

    private String EventLocation;
    private String Status;

    public FirestoreInvite() {
        //empty
    }

    public FirestoreInvite(String Guest, String Host, String EventID, String EventName, String EventDate, String EventLocation, String Status) {
        this.Guest = Guest;
        this.Host = Host;
        this.EventID = EventID;
        this.EventName = EventName;
        this.EventDate = EventDate;
        this.EventLocation = EventLocation;
        this.Status = Status;
    }

    public String getGuest() {return Guest;}

    public String getHost() {return Host;}

    public String getEventID() {return EventID;}

    public String getEventName() {return EventName;}

    public String getEventDate() {return EventDate;}

    public String getEventLocation() {return EventLocation;}

    public String getStatus() {return Status;}
}
