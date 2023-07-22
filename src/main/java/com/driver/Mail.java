package com.driver;

import java.util.Date;

public class Mail {
    Date date;
    String sender;
    String message;
    Mail prev;
    Mail next;

    public Mail(Date date, String sender, String message) {
        prev = null;
        next = null;
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}
