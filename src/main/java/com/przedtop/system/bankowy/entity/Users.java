package com.przedtop.system.bankowy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "pesel")
    private int pesel;
    @Column(name = "login")
    private String login;
    @Column(name = "haslo")
    private String haslo;

    @Override
    public String toString() {
        return "Users{" +
                "id='" + Id + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
}
