package com.raf.rezervacioni_servis.controller;

import com.raf.rezervacioni_servis.dto.*;
import com.raf.rezervacioni_servis.secutiry.CheckSecurity;
import com.raf.rezervacioni_servis.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/create")
    @CheckSecurity(roles = {"manager"})
    public ResponseEntity createHotel(@RequestHeader("Authorization") String authorization,
                                      @RequestBody @Valid HotelCreateDto hotelCreateDto) {
        return new ResponseEntity<>(hotelService.addHotel(hotelCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @CheckSecurity(roles = {"manager"})
    public ResponseEntity<HotelDto> updateHotel(@RequestHeader("Authorization") String authorization,
                                                @PathVariable("id") Long id
            , @RequestBody @Valid HotelUpdateDto hotelUpdateDto) {
        return new ResponseEntity<>(hotelService.updateHotel(id, hotelUpdateDto), HttpStatus.OK);
    }

    @PostMapping("/recenzija")
    @CheckSecurity(roles = {"client"})
    public ResponseEntity oceni(@RequestHeader("Authorization") String authorization,
                                @RequestBody @Valid OcenaCreateDto ocenaCreateDto) {
        return new ResponseEntity<>(hotelService.addOcena(ocenaCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization,
                                    @PathVariable("id") Long id) {
        hotelService.deleteOcena(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/soba")
    @CheckSecurity(roles = {"manager"})
    public ResponseEntity createSoba(@RequestHeader("Authorization") String authorization,
                                     @RequestBody @Valid SobaCreateDto sobaCreateDto) {
        return new ResponseEntity<>(hotelService.addSoba(sobaCreateDto), HttpStatus.CREATED);
    }

}
