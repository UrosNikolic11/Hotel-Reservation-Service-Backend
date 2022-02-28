package com.raf.korisnicki_servis.dto;

public class PlusReservationDto {
    private Long userId;

    public PlusReservationDto(Long userId) {
        this.userId = userId;
    }

    public PlusReservationDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
