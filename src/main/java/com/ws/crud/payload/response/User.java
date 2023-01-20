package com.ws.crud.payload.response;

public class User {
    
    private long id;

    private String username;

    private String email;
    
    private String Token;

    public User() {
        super();
    }

	public User(long id, String username, String email, String token) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        Token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
