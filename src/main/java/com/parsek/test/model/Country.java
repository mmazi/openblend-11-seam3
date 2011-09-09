package com.parsek.test.model;

/**
 * User: Matic<br/>
 * Date: 18.5.11<br/>
 */
public enum Country {
    SLV("sl"),
    USA("en"),
    ;

    private String language;

    Country(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
