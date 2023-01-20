package com.ws.crud.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ws.crud.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPassword(String password);
    Boolean existsByEmail(String email);
    
    @Query(value="BEGIN update users set solde = solde - ?1 where id = ?2 ; update users set solde = solde + ?1 where id = ?3 ; COMMIT;",nativeQuery = true)
    @Transactional
    @Modifying
    void transaction(double montant,long user_id_1,long user_id_2);

    @Query(value="update users set solde = solde + ?1 where id = ?2",nativeQuery = true)
    @Transactional
    @Modifying
    void recharger(double montant,int user_id);
}
