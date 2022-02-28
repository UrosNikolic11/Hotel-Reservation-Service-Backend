package com.raf.rezervacioni_servis.dto;

public class OcenaCreateDto {
    private Float ocena;
    private String opis;
    private String imeHotela;
    private Long klijentId;

    public OcenaCreateDto() {
    }

    public Float getOcena() {
        return ocena;
    }

    public void setOcena(Float ocena) {
        this.ocena = ocena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getImeHotela() {
        return imeHotela;
    }

    public void setImeHotela(String imeHotela) {
        this.imeHotela = imeHotela;
    }

    public Long getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Long klijentId) {
        this.klijentId = klijentId;
    }
}
