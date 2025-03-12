package com.przedtop.bank.system.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UserRequestDataModel {

    private Long id;
    @NotNull(message = "")
    private String lastName;
    private String name;
    @JsonProperty("idNo")
    private int identificationNumber;
    private String password;
    private String login;

    @Override
    public String toString() {
        return "UserUpdateDataModel{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }
}
