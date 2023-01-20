package com.ws.crud.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.ws.crud.repository.ImageRepository;

@Entity
@Table(name = "enchere")
public class Enchere {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "datedebut")
    private Timestamp datedebut;
    @Column(name = "datefin")
    private Timestamp datefin;
    @Column(name = "produit")
    private String produit;
    @Column(name = "prixminimal")
    private double prixminimal;
    @Column(name = "description")
    private String description;
    @Column(name = "category_id")
    private int category_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "statut")
    private int statut;
    @Transient
    private List<String> images;
    @Transient
    @Autowired
    ImageRepository imageRepository;
    public Enchere() {
        super();
    }
    public Enchere(Timestamp datedebut, Timestamp datefin, String produit, double prixminimal, String description,
            int category_id, int user_id, int statut) {
        super();
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.produit = produit;
        this.prixminimal = prixminimal;
        this.description = description;
        this.category_id = category_id;
        this.user_id = user_id;
        this.statut = statut;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Timestamp getDatedebut() {
        return datedebut;
    }
    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }
    public Timestamp getDatefin() {
        return datefin;
    }
    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }
    public String getProduit() {
        return produit;
    }
    public void setProduit(String produit) {
        this.produit = produit;
    }
    public double getPrixminimal() {
        return prixminimal;
    }
    public void setPrixminimal(double prixminimal) {
        this.prixminimal = prixminimal;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getStatut() {
        String etat = null;
        if(statut == 0){
            etat = "Encours";
        }else if(statut == 1) {
            etat = "Termine";
        }
        return etat;
    }
    public void setStatut(int statut) {
        this.statut = statut;
    }    
    public List<String> getImages(){
        return images;
    }
    public void setImages(List<String> images){
        this.images = images;
    }
}
