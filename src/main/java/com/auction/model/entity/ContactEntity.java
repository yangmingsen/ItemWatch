package com.auction.model.entity;

public class ContactEntity {
    private Long id;
    private String from;
    private String to;
    private String subject;
    private String message;
    private String date;

    public ContactEntity() {    }

    public ContactEntity(String from, String to,
                     String subject, String message, String date) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
