package com.odev.cv;

import java.util.ArrayList;
import java.util.List;

public class Resume {
    private String fullName;
    private String title;
    private String email;
    private String phone;
    private String location;
    private String imagePath;
    private List<Experience> experiences;

    public Resume(String fullName, String title, String email, String phone, String location, String imagePath) {
        this.fullName = fullName;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.imagePath = imagePath;
        this.experiences = new ArrayList<>();
    }

    public void addExperience(Experience exp) {
        if (this.experiences.size() < 3) {
            this.experiences.add(exp);
        }
    }

    public String getFullName() { return fullName; }
    public String getTitle() { return title; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getImagePath() { return imagePath; }
    public List<Experience> getExperiences() { return experiences; }
}