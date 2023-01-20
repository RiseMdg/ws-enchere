package com.ws.crud.payload.request;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class AjouterEnchere {
    private int duree;
    private String produit;
    private double prixminimal;
    private String description;
    private int category_id;
    private int user_id;
    private MultipartFile image;

    public AjouterEnchere() {
        super();
    }

    public AjouterEnchere(int duree, String produit, double prixminimal,
            String description, int category_id, int user_id, MultipartFile image) {
        super();
        this.duree = duree;
        this.produit = produit;
        this.prixminimal = prixminimal;
        this.description = description;
        this.category_id = category_id;
        this.user_id = user_id;
        this.image = image;
    }

    public Timestamp getDatedebut() {
        Timestamp datedebut = new Timestamp(new Date().getTime());
        System.out.println("Date de Debut : " + datedebut);
        return datedebut;
    }

    public Timestamp getDateFin() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getDatedebut().getTime());
        cal.add(Calendar.HOUR, duree);
        Timestamp dateFin = new Timestamp(cal.getTime().getTime());
        return dateFin;
    }

    public void setDuree(int duree) {
        this.duree = duree;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
    
}
