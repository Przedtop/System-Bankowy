package com.przedtop.bank.system.controllers.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class UserRequestDataModel {

    private Long id;
    @NotNull(message = "Last name is required.")
    private String lastName;
    @NotNull(message = "Name is required.")
    private String name;
    @JsonAlias("idNo")
    @JsonProperty("identificationNumber")
    @NotNull(message = "Identification number is required.")
    private Long identificationNumber;
    @NotNull(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    @NotNull(message = "Login is required.")
    @Size(min = 3, max = 50, message = "Login must be between 3 and 50 characters")
    private String login;
    @Null(message = "Role is assigned automatically.")
    private String role;

    public String properUsage() {
        char separator = '"';
        return "Proper usage: " +
                "\n" + separator + "lastName" + separator + ": value, " +
                "\n" + separator + "name" + separator + ": value, " +
                "\n" + separator + "identificationNumber" + separator + ": value, " +
                "\n" + separator + "password" + separator + ": value (6-20 characters), " +
                "\n" + separator + "login" + separator + ": value (3-50 characters)";
    }

    @Override
    public String toString() {
        return "UserUpdateDataModel{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentificationNumber(Long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}