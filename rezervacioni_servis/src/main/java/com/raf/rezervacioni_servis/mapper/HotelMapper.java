package com.raf.rezervacioni_servis.mapper;

import com.raf.rezervacioni_servis.domain.Hotel;
import com.raf.rezervacioni_servis.dto.HotelCreateDto;
import com.raf.rezervacioni_servis.dto.HotelDto;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public HotelMapper() {
    }

    public HotelDto hotelToHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setIme(hotel.getIme());
        hotelDto.setGrad(hotel.getGrad());
        hotelDto.setOpis(hotel.getOpis());
        hotelDto.setManager_id(hotel.getManager_id());
        return hotelDto;
    }

    public Hotel hotelCreateDtoToHotel(HotelCreateDto hotelCreateDto){
        Hotel hotel = new Hotel();
        hotel.setGrad(hotelCreateDto.getGrad());
        hotel.setIme(hotelCreateDto.getIme());
        hotel.setOpis(hotelCreateDto.getOpis());
        return hotel;
    }
}
