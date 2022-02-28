package com.raf.rezervacioni_servis.service;

import com.raf.rezervacioni_servis.dto.*;


public interface HotelService {
    HotelDto addHotel(HotelCreateDto hotelCreateDto);
    HotelDto updateHotel(Long id, HotelUpdateDto hotelUpdateDto);
    OcenaDto addOcena(OcenaCreateDto ocenaCreateDto);
    void deleteOcena(Long id);
    SobaDto addSoba(SobaCreateDto sobaCreateDto);
}
