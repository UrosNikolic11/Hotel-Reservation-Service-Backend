package com.raf.korisnicki_servis.dto;

public class RankDto {
    private String name;
    private Integer topLimit;
    private Integer botLimit;
    private Float popust;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(Integer topLimit) {
        this.topLimit = topLimit;
    }

    public Integer getBotLimit() {
        return botLimit;
    }

    public void setBotLimit(Integer botLimit) {
        this.botLimit = botLimit;
    }

    public Float getPopust() {
        return popust;
    }

    public void setPopust(Float popust) {
        this.popust = popust;
    }
}
