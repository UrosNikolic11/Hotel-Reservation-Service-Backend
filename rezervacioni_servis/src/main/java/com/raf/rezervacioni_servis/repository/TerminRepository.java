package com.raf.rezervacioni_servis.repository;

import com.raf.rezervacioni_servis.domain.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {
    Termin findTerminById(Long id);
}
