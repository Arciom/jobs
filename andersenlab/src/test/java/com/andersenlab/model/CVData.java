package com.andersenlab.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;

@XStreamAlias("CvData")
public class CVData {
    private String name;
    private String email;
    private String place;
    private File cv;

    public CVData withName(String name) {
        this.name = name;
        return this;
    }

    public CVData withEmail(String email) {
        this.email = email;
        return this;
    }

    public CVData withPlace(String place) {
        this.place = place;
        return this;
    }

    public CVData withCv(File cv) {
        this.cv = cv;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPlace() {
        return place;
    }

    public File getCv() { return cv; }

    @Override
    public String toString() {
        return "CVData{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", place='" + place + '\'' +
                ", cv=" + cv +
                '}';
    }
}
