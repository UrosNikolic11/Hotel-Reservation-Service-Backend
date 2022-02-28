package com.raf.korisnicki_servis.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "korisnik")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_id")
    private Long id;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "email")
    private String email;
    @Column(name = "datum_rodjenja")
    private Date datumRodjenja;
    @Column(name = "aktivan")
    private String aktivan;
    @Column(name = "role")
    private String role;
    @Column(name = "datum_zaposlenja")
    private Date datumZaposlenja;
    @Column(name = "broj_rezervacija")
    private Integer brRezervacija;
    @Column(name = "broj_pasosa")
    private String brPasosa;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getAktivan() {
        return aktivan;
    }

    public void setAktivan(String aktivan) {
        this.aktivan = aktivan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public int getBrRezervacija() {
        return brRezervacija;
    }

    public void setBrRezervacija(Integer brRezervacija) {
        this.brRezervacija = brRezervacija;
    }

    public String getBrPasosa() {
        return brPasosa;
    }

    public void setBrPasosa(String brPasosa) {
        this.brPasosa = brPasosa;
    }
}
