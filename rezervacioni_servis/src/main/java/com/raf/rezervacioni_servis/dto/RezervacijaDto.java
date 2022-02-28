package com.raf.rezervacioni_servis.dto;

public class RezervacijaDto {
    private Long id;
    private Long sobaId;
    private Long terminId;
    private Long korisnikId;
    private Float cena;

    public RezervacijaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSobaId() {
        return sobaId;
    }

    public void setSobaId(Long sobaId) {
        this.sobaId = sobaId;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }
}
