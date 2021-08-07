package com.example.reminder.model;

public class Email1 {
    private int id;

    private String send_to;
    private String subject;
    private String masseg;
    private String date;

    public Email1() {
    }

    public Email1(int id, String send_to, String subject, String masseg, String date) {
        this.id = id;
        this.send_to = send_to;
        this.subject = subject;
        this.masseg = masseg;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getSend_to() {
        return send_to;
    }

    public void setSend_to(String send_to) {
        this.send_to = send_to;
    }




    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMasseg() {
        return masseg;
    }

    public void setMasseg(String masseg) {
        this.masseg = masseg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
