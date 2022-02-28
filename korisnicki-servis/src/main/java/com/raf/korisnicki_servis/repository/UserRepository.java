package com.raf.korisnicki_servis.repository;

import com.raf.korisnicki_servis.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);
    User findUserById(Long id);

}
