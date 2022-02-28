package com.raf.korisnicki_servis.dto;

public class RankUpdateDto {
    private String ime;
    private Integer botLimit;
    private Integer topLimit;
    private Float popust;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getBotLimit() {
        return botLimit;
    }

    public void setBotLimit(Integer botLimit) {
        this.botLimit = botLimit;
    }

    public Integer getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(Integer topLimit) {
        this.topLimit = topLimit;
    }

    public Float getPopust() {
        return popust;
    }

    public void setPopust(Float popust) {
        this.popust = popust;
    }
}
