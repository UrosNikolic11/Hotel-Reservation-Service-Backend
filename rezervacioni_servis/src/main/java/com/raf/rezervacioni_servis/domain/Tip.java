package com.raf.rezervacioni_servis.domain;

import javax.persistence.*;

@Entity
@Table(name = "tip_soba")
public class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private Long id;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "cena_po_danu")
    private Float cena;
    @Column(name = "odakle")
    private Integer odakle;
    @Column(name = "dokle")
    private Integer dokle;

    public Tip() {
    }

    public Integer getOdakle() {
        return odakle;
    }

    public void setOdakle(Integer odakle) {
        this.odakle = odakle;
    }

    public Integer getDokle() {
        return dokle;
    }

    public void setDokle(Integer dokle) {
        this.dokle = dokle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }
}
