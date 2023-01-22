package com.ws.crud.payload.request;

public class UserId {
    private int user_id;
    
    public UserId() {
        super();
    }

    public UserId(int user_id) {
        super();
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
