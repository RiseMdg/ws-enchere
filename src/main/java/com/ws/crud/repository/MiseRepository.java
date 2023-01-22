package com.ws.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ws.crud.model.Mise;

@Repository
public interface MiseRepository extends JpaRepository<Mise, Long>{
    @Query(value="Select * from mise where user_id = ?1",nativeQuery = true)
    List<Mise> miseByUser_id(long user_id);

    @Query(value="Select prixminimal from enchere where id = ?1",nativeQuery = true)
    double prixMinimal(int enchere_id);

    @Query(value="select MAX(prixmise) from mise where enchere_id = ?1",nativeQuery = true)
    double miseMax(int enchere_id);
}
