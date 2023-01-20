package com.ws.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mise")
public class Mise {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "prixmise")
    private double prixmise;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "enchere_id")
    private int enchere_id;
    public Mise() {
        super();
    }
    public Mise(double prixmise, int user_id, int enchere_id) {
        super();
        this.prixmise = prixmise;
        this.user_id = user_id;
        this.enchere_id = enchere_id;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getPrixmise() {
        return prixmise;
    }
    public void setPrixmise(double prixmise) {
        this.prixmise = prixmise;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getEnchere_id() {
        return enchere_id;
    }
    public void setEnchere_id(int enchere_id) {
        this.enchere_id = enchere_id;
    }
    
}
