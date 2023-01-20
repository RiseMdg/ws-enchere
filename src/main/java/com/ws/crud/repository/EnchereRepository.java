package com.ws.crud.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ws.crud.model.Enchere;
import com.ws.crud.payload.response.InterfaceStatistique;
import com.ws.crud.payload.response.Statistique;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Long>{

    @Query(value="Select * from enchere where user_id = ?1",nativeQuery = true)
    List<Enchere> enchereByUser_id(long user_id);

    @Query(value="Select * from produitBeEnchere",nativeQuery = true)
    List<InterfaceStatistique> produitBeEnchere();

    @Query(value="Select * from categoryBeEnchere",nativeQuery = true)
    List<InterfaceStatistique> categoryBeEnchere();

    @Query(value="Select * from produitBeMise",nativeQuery = true)
    List<InterfaceStatistique> produitBeMise();

    @Query(value="Select * from categoryBeMise",nativeQuery = true)
    List<InterfaceStatistique> categoryBeMise();

    @Query(value="select datefin from enchere where statut = 0",nativeQuery = true)
    List<Timestamp> getTime();

    @Query(value="Update enchere set statut = 1 where datefin = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    void updateStatut(Timestamp datefin);
    
}
