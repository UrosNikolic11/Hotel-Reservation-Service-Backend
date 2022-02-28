package com.raf.rezervacioni_servis.domain;

import javax.persistence.*;

@Entity
@Table(name = "ocena")
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocena_id")
    private Long id;
    @Column(name = "ocena")
    private Float ocena;
    @Column(name = "komentar")
    private String komentar;
    @Column(name = "klijent_id")
    private Long klijent_id;
    @Column(name = "hotel_id")
    private Long hotel_id;

    public Ocena() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getOcena() {
        return ocena;
    }

    public void setOcena(Float ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Long getKlijent_id() {
        return klijent_id;
    }

    public void setKlijent_id(Long klijent_id) {
        this.klijent_id = klijent_id;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }
}
