package com.przedtop.bank.system.controllers.model;

public class UserRequestDataModel {

    private Long id;
    private String nazwisko;
    private String imie;
    private int pesel;
    private String haslo;
    private String login;

    @Override
    public String toString() {
        return "UserUpdateDataModel{" +
                "id='" + id + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", pesel='" + pesel + '\'' +
                ", haslo='" + haslo + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }


}
