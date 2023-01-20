package com.ws.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rechargement")
public class Rechargement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "montant")
    private double montant;
    @Column(name = "etat")
    private int etat;
    @Column(name = "user_id")
    private int user_id;

    public Rechargement() {
        super();
    }
    
    public Rechargement(double montant, int etat, int user_id) {
        super();
        this.montant = montant;
        this.etat = etat;
        this.user_id = user_id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public double getMontant() {
        return montant;
    }


    public void setMontant(double montant) {
        this.montant = montant;
    }


    public int getEtat() {
        return etat;
    }


    public void setEtat(int etat) {
        this.etat = etat;
    }


    public int getUser_id() {
        return user_id;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
}
