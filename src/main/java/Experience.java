package com.odev.cv;

public class Experience {
    private String companyName;
    private String title;
    private String startDate;
    private String endDate;
    private String description;

    public Experience(String companyName, String title, String startDate, String endDate, String description) {
        this.companyName = companyName;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getCompanyName() { return companyName; }
    public String getTitle() { return title; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getDescription() { return description; }
}