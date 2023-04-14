package com.mobdeve.s13.kho.denise.schedulecalendar;

public class FirestoreEvent {

    private String Host;
    private String User;
    private String LNameTxt;
    private String LDateTxt;
    private String LLocationTxt;
    private int LPrio;

    public FirestoreEvent() {
        //empty
    }


    public FirestoreEvent(String Host,String User,String LNameTxt, String LDateTxt, String LLocationTxt, int LPrio) {
        this.Host=Host;
        this.User=User;
        this.LNameTxt = LNameTxt;
        this.LDateTxt = LDateTxt;
        this.LLocationTxt = LLocationTxt;
        this.LPrio = LPrio;
    }
    public String getHost()
    {
        return Host;
    }

    public void setUser(String User)
    {
        this.User=User;
    }
    public String getUser()
    {
        return User;
    }

    public String getLNameTxt() {
        return LNameTxt;
    }

    public void setLNameTxt(String LNameTxt) {
        this.LNameTxt = LNameTxt;
    }

    public String getLDateTxt() {
        return LDateTxt;
    }

    public void setLDateTxt(String LDateTxt) {
        this.LDateTxt = LDateTxt;
    }

    public String getLLocationTxt() {
        return LLocationTxt;
    }

    public void setLLocationTxt(String LLocationTxt) {
        this.LLocationTxt = LLocationTxt;
    }

    public int getLPrio() {
        return LPrio;
    }

    public void setLPrio(int LPrio) {
        this.LPrio = LPrio;
    }
}

