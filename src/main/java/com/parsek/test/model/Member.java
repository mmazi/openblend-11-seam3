package com.parsek.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Member implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @NotNull @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    private String name;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^(?:(?:(?:[^@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff\\.]|\\x5c(?=[@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff]))(?:[^@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff\\.]|(?<=\\x5c)[@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff]|\\x5c(?=[@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff])|\\.(?=[^\\.])){1,62}(?:[^@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff\\.]|(?<=\\x5c)[@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff])|[^@,\"\\[\\]\\x5c\\x00-\\x20\\x7f-\\xff\\.]{1,2})|\"(?:[^\"]|(?<=\\x5c)\"){1,62}\")@(?:(?!.{64})(?:[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.?|[a-zA-Z0-9]\\.?)+\\.(?:xn--[a-zA-Z0-9]+|[a-zA-Z]{2,6})|\\[(?:[0-1]?\\d?\\d|2[0-4]\\d|25[0-5])(?:\\.(?:[0-1]?\\d?\\d|2[0-4]\\d|25[0-5])){3}\\])$", message = "Not a valid email address.")
    private String email;

    @NotNull
    private String phoneNumber;
    private Country country;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
