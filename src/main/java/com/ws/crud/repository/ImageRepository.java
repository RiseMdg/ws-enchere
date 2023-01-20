package com.ws.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ws.crud.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
    @Query(value="Select filename from image where enchere_id = ?1",nativeQuery = true)
    List<String> images(long enchere_id);
}
