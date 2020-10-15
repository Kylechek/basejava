package com.javaops.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link homePage;
    private List<Position> positions = new ArrayList<>();


    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    @Override
    public String toString() {
        return homePage + ", " + positions;
    }

    public static class Position implements Serializable{
        private final String title;
        private final String description;
        private final YearMonth startDate;
        private final YearMonth endDate;

        public Position(String title, String description, YearMonth startDate, YearMonth endDate) {
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return title + description + startDate + endDate;
        }
    }
}
