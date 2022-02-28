package com.raf.rezervacioni_servis.domain;

import javax.persistence.*;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervacija_id")
    private Long id;
    @Column(name = "soba_id")
    private Long soba_id;
    @Column(name = "termin_id")
    private Long termin_id;
    @Column(name = "korisnik_id")
    private Long klijent_id;
    @Column(name = "cena")
    private Float cena;

    public Rezervacija() {
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoba_id() {
        return soba_id;
    }

    public void setSoba_id(Long soba_id) {
        this.soba_id = soba_id;
    }

    public Long getTermin_id() {
        return termin_id;
    }

    public void setTermin_id(Long termin_id) {
        this.termin_id = termin_id;
    }

    public Long getKlijent_id() {
        return klijent_id;
    }

    public void setKlijent_id(Long klijent_id) {
        this.klijent_id = klijent_id;
    }
}
