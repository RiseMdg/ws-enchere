package com.ws.crud.payload.request;

public class Rencherir {
    private double prixmise;
    private int enchere_id;
    private int user_id;
    public Rencherir() {
        super();
    }
    public Rencherir(double prixmise, int enchere_id, int user_id) {
        super();
        this.prixmise = prixmise;
        this.enchere_id = enchere_id;
        this.user_id = user_id;
    }
    public double getPrixmise() {
        return prixmise;
    }
    public void setPrixmise(double prixmise) {
        this.prixmise = prixmise;
    }
    public int getEnchere_id() {
        return enchere_id;
    }
    public void setEnchere_id(int enchere_id) {
        this.enchere_id = enchere_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
