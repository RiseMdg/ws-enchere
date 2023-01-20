package com.ws.crud.payload.response;

public class Admin {
    
    private long id;

    private String email;
    
    private String Token;

    public Admin() {
        super();
    }

	public Admin(long id , String email, String token) {
        super();
        this.id = id;
        this.email = email;
        Token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getMessage() {
		// TODO Auto-generated method stub
		return "Succesfull";
	}
}
