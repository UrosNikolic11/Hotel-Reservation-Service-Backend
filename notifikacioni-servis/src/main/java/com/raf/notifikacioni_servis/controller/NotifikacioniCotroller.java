package com.raf.notifikacioni_servis.controller;


import com.raf.notifikacioni_servis.domain.Notifikacija;
import com.raf.notifikacioni_servis.dto.NotifikacijaUpdateDto;
import com.raf.notifikacioni_servis.secutiry.CheckSecurity;
import com.raf.notifikacioni_servis.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifikacija")
public class NotifikacioniCotroller {

    private NotificationService notificationService;

    public NotifikacioniCotroller(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<Notifikacija>> getNot(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(notificationService.findNot(pageable), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @CheckSecurity(roles = {"manager", "client"})
    public ResponseEntity<Page<Notifikacija>> getNotByUser3(@RequestHeader("Authorization") String authorization,
                                                     @PathVariable("userId") Long id, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findNotByUser(id,pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization,
                                    @PathVariable("id") Long id) {
        notificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Notifikacija> update(@RequestHeader("Authorization") String authorization,
                                               @PathVariable("id") Long id, NotifikacijaUpdateDto notifikacijaUpdateDto) {
        return new ResponseEntity<>(notificationService.update(id, notifikacijaUpdateDto), HttpStatus.OK);
    }

}
