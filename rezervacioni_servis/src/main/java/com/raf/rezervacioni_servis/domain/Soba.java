package com.raf.rezervacioni_servis.domain;

import javax.persistence.*;

@Entity
@Table(name = "soba")
public class Soba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soba_id")
    private Long id;
    @Column(name = "hotel_id")
    private Long hotelId;
    @Column(name = "tip_id")
    private Long tipId;
    @Column(name = "br_sobe")
    private Integer brSobe;

    public Soba() {
    }

    public Integer getBrSobe() {
        return brSobe;
    }

    public void setBrSobe(Integer brSobe) {
        this.brSobe = brSobe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }
}
