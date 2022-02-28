package com.raf.rezervacioni_servis.dto;

public class SobaCreateDto {
    private String hotel;
    private Integer kolikoSoba;
    private String tip;

    public SobaCreateDto() {
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Integer getKolikoSoba() {
        return kolikoSoba;
    }

    public void setKolikoSoba(Integer kolikoSoba) {
        this.kolikoSoba = kolikoSoba;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
