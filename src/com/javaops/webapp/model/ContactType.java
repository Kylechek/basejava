package com.javaops.webapp.model;

public enum ContactType {
    TEL("Тел:"),
    SKYPE("Skype:"),
    EMAIL("Email"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
