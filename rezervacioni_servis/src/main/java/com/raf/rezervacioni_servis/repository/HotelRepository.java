package com.raf.rezervacioni_servis.repository;

import com.raf.rezervacioni_servis.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findHotelById(Long id);
    Hotel findHotelByIme(String ime);
    Hotel findHotelByImeAndGrad(String ime, String grad);
}
