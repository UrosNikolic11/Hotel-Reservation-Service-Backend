package com.raf.rezervacioni_servis.repository;

import com.raf.rezervacioni_servis.domain.Soba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SobaRepository extends JpaRepository<Soba, Long> {
    List<Soba> findSobasByHotelIdAndTipIdOrderByBrSobeDesc(Long hotelId, Long tipId);
    Soba findSobaById(Long id);
}
