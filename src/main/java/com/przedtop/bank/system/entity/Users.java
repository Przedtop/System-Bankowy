package com.przedtop.bank.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "identificationNumber")
    private int identificationNumber;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return "Users{" +
                "id='" + Id + '\'' +
                ", imie='" + name + '\'' +
                ", nazwisko='" + lastName + '\'' +
                ", pesel='" + identificationNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int pesel) {
        this.identificationNumber = pesel;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nazwisko) {
        this.lastName = nazwisko;
    }

    public String getName() {
        return name;
    }

    public void setName(String imie) {
        this.name = imie;
    }
}
