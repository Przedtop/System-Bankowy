package com.przedtop.system.bankowy.controllers.model;


public class UserRequestDataModel {


    private String nazwisko;
    private String imie;
    private int pesel;
    private String haslo;
    private String login;

    @Override
    public String toString() {
        return "UserRequestDataModel{" +
                "nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", pesel=" + pesel +
                ", haslo='" + haslo + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
    public String getImie() {
        return imie;
    }

    public int getPesel() {
        return pesel;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getLogin() {
        return login;
    }

}
