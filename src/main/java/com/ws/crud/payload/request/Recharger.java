package com.ws.crud.payload.request;

public class Recharger {
    private double solde;
    private int user_id;
    public Recharger() {
        super();
    }
    public Recharger(double solde, int user_id) {
        this.solde = solde;
        this.user_id = user_id;
    }
    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
