package com.ws.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ws.crud.model.Rechargement;

@Repository
public interface RechargementRepository extends JpaRepository<Rechargement, Long>{
    @Query(value="Select * from rechargement where etat = 0",nativeQuery = true)
    List<Rechargement> rechargementsNotAccepted();
    
    @Query(value="update rechargement set etat = 1 where id = ?1",nativeQuery = true)
    @Transactional
    @Modifying
    void updateEtatRechargement(int rechargement_id);

    @Query(value="Select montant from rechargement where id = ?1",nativeQuery = true)
    double getMontant(int rechargement_id);
}
