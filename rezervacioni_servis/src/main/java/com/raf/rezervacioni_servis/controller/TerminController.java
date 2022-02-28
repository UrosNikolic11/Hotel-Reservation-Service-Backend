package com.raf.rezervacioni_servis.controller;

import com.raf.rezervacioni_servis.domain.Soba;
import com.raf.rezervacioni_servis.domain.Termin;
import com.raf.rezervacioni_servis.dto.RezervacijaDto;
import com.raf.rezervacioni_servis.dto.SlobodniDto;
import com.raf.rezervacioni_servis.dto.SobaDto;
import com.raf.rezervacioni_servis.secutiry.CheckSecurity;
import com.raf.rezervacioni_servis.service.TerminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/termini")
public class TerminController {
    private TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }


    @GetMapping
    @CheckSecurity(roles = {"admin", "manager", "client"})
    public ResponseEntity<Page<Termin>> getSviTermini(@RequestHeader("Authorization") String authorization,
                                                      Pageable pageable) {
        return new ResponseEntity<>(terminService.findTermin(pageable), HttpStatus.OK);
    }

    @GetMapping("/{hotelIme}/{grad}/{terminId}/{tip}")
    @CheckSecurity(roles = {"admin", "manager", "client"})
    public ResponseEntity<SlobodniDto> slobodni(@RequestHeader("Authorization") String authorization,
                                                @PathVariable("hotelIme") String hotelIme, @PathVariable("grad") String grad
    , @PathVariable("terminId") Long id,@PathVariable("tip") String tip){
        return new ResponseEntity<>(terminService.findSlobodni(hotelIme,grad,id,tip), HttpStatus.OK);
    }
}
