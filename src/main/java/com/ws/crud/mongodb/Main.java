package com.ws.crud.mongodb;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException{
        Mongo mongo = new Mongo();
        mongo.loginAdmin(null, null);
    }
}
