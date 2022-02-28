package com.raf.rezervacioni_servis.dto;

public class DiscountDto {
    private Float discount;

    public Float getDiscount() {
        return discount;
    }

    public DiscountDto() {
    }

    public DiscountDto(Float discount) {
        this.discount = discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
