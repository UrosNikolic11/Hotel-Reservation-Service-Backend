package com.raf.notifikacioni_servis.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notifikacija")
public class Notifikacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notifikacija_id")
    private Long id;

    @Column(name = "tip")
    private String textType;

    @Column(name = "text")
    private String text;

    @Column(name = "datum_kreiranja")
    private Date datumKreiranje;

    @Column(name = "email")
    private String email;



    public Notifikacija() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatumKreiranje() {
        return datumKreiranje;
    }

    public void setDatumKreiranje(Date datumKreiranje) {
        this.datumKreiranje = datumKreiranje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
