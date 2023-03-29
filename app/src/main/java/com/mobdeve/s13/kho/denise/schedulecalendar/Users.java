package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.media.Image;

public class Users {
    private long id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Image ImageUri;

    public Users()
    {}
    public Users(long id,String username,String password,String email,String mobile,Image ImageUri)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.mobile=mobile;
        this.ImageUri=ImageUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setImageUri(Image imageUri) {
        ImageUri = imageUri;
    }

    public Image getImageUri() {
        return ImageUri;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
