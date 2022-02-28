package com.raf.korisnicki_servis.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.Date;

public class UserUpdateDto {
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    private String telefon;
    private Date datumRodjenja;
    private Date datumZaposlenja;
    private String brPasosa;
    private Integer brRezervacija;

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public String getBrPasosa() {
        return brPasosa;
    }

    public void setBrPasosa(String brPasosa) {
        this.brPasosa = brPasosa;
    }

    public Integer getBrRezervacija() {
        return brRezervacija;
    }

    public void setBrRezervacija(Integer brRezervacija) {
        this.brRezervacija = brRezervacija;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
