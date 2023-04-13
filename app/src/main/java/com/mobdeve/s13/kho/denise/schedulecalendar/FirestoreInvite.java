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

    public void setGuest(String value) {this.Guest = value;}

    public String getHost() {return Host;}

    public void setHost(String value) {this.Host = value;}

    public String getEventID() {return EventID;}

    public void setEventID(String value) {this.EventID = value;}

    public String getEventName() {return EventName;}

    public void setEventName(String value) {this.EventName = value;}

    public String getEventDate() {return EventDate;}

    public void setEventDate(String value) {this.EventDate = value;}

    public String getEventLocation() {return EventLocation;}

    public void setEventLocation(String value) {this.EventDate = value;}

    public String getStatus() {return Status;}

    public void setStatus(String value) {this.Status = value;}
}
