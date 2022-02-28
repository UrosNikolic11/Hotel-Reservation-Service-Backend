package com.raf.rezervacioni_servis.dto;

public class SobaDto {
    private Long id;
    private Long hotel_id;
    private Long tip_id;
    private Integer brSobe;

    public SobaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Long getTip_id() {
        return tip_id;
    }

    public void setTip_id(Long tip_id) {
        this.tip_id = tip_id;
    }

    public Integer getBrSobe() {
        return brSobe;
    }

    public void setBrSobe(Integer brSobe) {
        this.brSobe = brSobe;
    }
}
