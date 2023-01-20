package com.ws.crud.payload.response;

public class Statistique {
    private String nom;
    private int nombre;
    
    public Statistique() {
        super();
    }

    public Statistique(String nom, int nombre) {
        super();
        this.nom = nom;
        this.nombre = nombre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

}
