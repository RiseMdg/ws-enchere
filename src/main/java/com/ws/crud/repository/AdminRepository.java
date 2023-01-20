package com.ws.crud.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ws.crud.model.Admins;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long>{
    Optional<Admins> findByEmail(String email);
    Optional<Admins> findByPassword(String password);
    Boolean existsByEmail(String email);
}
