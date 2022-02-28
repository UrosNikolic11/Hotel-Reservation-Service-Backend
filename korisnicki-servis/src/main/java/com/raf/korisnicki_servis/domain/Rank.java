package com.raf.korisnicki_servis.domain;

import javax.persistence.*;

@Entity
@Table(name = "rankovi")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long id;
    @Column(name = "naziv")
    private String ime;
    @Column(name = "bot_limit")
    private Integer botLimit;
    @Column(name = "top_limit")
    private Integer topLimit;
    @Column(name = "popust")
    private Float popust;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
