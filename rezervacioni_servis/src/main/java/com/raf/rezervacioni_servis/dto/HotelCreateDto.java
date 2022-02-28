package com.raf.rezervacioni_servis.dto;

import javax.validation.constraints.NotBlank;

public class HotelCreateDto {
    @NotBlank
    private String ime;
    private String opis;
    @NotBlank
    private String grad;
    private Long manager_id;

    public HotelCreateDto() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public Long getManager_id() {
        return manager_id;
    }

    public void setManager_id(Long manager_id) {
        this.manager_id = manager_id;
    }
}
