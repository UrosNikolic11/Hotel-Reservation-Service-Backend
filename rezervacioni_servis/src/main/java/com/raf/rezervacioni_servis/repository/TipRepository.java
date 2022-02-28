package com.raf.rezervacioni_servis.repository;

import com.raf.rezervacioni_servis.domain.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {
    Tip findTipByNaziv(String naziv);
    Tip findTipById(Long id);
}
