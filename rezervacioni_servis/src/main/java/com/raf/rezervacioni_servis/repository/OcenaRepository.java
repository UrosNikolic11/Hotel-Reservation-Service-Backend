package com.raf.rezervacioni_servis.repository;

import com.raf.rezervacioni_servis.domain.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcenaRepository extends JpaRepository<Ocena, Long> {

}
