package com.raf.korisnicki_servis.repository;

import com.raf.korisnicki_servis.domain.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    Rank findRankByIme(String ime);
    List<Rank> findAll();
}
