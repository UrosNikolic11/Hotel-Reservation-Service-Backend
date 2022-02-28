package com.raf.rezervacioni_servis.controller;

import com.raf.rezervacioni_servis.dto.RezervacijaCreateDto;
import com.raf.rezervacioni_servis.dto.RezervacijaDto;
import com.raf.rezervacioni_servis.secutiry.CheckSecurity;
import com.raf.rezervacioni_servis.service.RezervacijaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

    private RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @PostMapping
    @CheckSecurity(roles = {"manager", "client"})
    public ResponseEntity create(@RequestHeader("Authorization") String authorization,
                                 @RequestBody @Valid RezervacijaCreateDto rezervacijaCreateDto) {
        return new ResponseEntity<>(rezervacijaService.addRezervacija(rezervacijaCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"manager", "client"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization,
                                    @PathVariable("id") Long id) {
        rezervacijaService.deleteRezervacija(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @CheckSecurity(roles = {"manager", "admin"})
    public ResponseEntity<Page<RezervacijaDto>> getRez(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(rezervacijaService.findRezervacija(pageable), HttpStatus.OK);
    }
}
