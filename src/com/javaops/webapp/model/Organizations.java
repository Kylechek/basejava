package com.javaops.webapp.model;

import java.time.YearMonth;

public class Organizations {
    private final Link homePage;
    private final String title;
    private final String description;
    private final YearMonth startDate;
    private final YearMonth endDate;

    public Organizations(Link homePage, String title, String description, YearMonth startDate, YearMonth endDate) {
        this.homePage = homePage;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}