package com.raf.rezervacioni_servis.dto;

public class RezervacijaCreateDto {

    private Long terminId;
    private Long sobaId;
    private Long klijentId;


    public RezervacijaCreateDto() {
    }

    public Long getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Long klijentId) {
        this.klijentId = klijentId;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }

    public Long getSobaId() {
        return sobaId;
    }

    public void setSobaId(Long sobaId) {
        this.sobaId = sobaId;
    }
}

