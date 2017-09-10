package com.itechart.careers.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;

@XStreamAlias("CvData")
public class CVData {
    private String name;
    private String email;
    private String phone;
    private File cv;
    private String message;

    public CVData withName(String name) {
        this.name = name;
        return this;
    }

    public CVData withEmail(String email) {
        this.email = email;
        return this;
    }

    public CVData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CVData withCv(File cv) {
        this.cv = cv;
        return this;
    }

    public CVData withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() { return phone;  }

    public File getCv() { return cv; }

    public String getMessage() { return message;  }

    @Override
    public String toString() {
        return "CVData{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cv=" + cv +
                ", message='" + message + '\'' +
                '}';
    }
}
