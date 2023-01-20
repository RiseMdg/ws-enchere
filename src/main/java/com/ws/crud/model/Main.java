package com.ws.crud.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());

        cal.add(Calendar.HOUR, 5);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);
    }
}
