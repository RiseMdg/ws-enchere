package com.ws.crud.payload.request;

public class ValiderRechargement {
    private int rechargement_id;
    private int user_id;
    
    public ValiderRechargement() {
        super();
    }

    public ValiderRechargement(int rechargement_id, int user_id) {
        super();
        this.rechargement_id = rechargement_id;
        this.user_id = user_id;
    }

    public int getRechargement_id() {
        return rechargement_id;
    }

    public void setRechargement_id(int rechargement_id) {
        this.rechargement_id = rechargement_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
