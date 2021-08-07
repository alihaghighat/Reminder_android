package com.example.reminder.model;

public class Alarm {
    private int id;
    private int week;
    private String tilte;
    private String url;
    private String des;
    private String time;
    private int priority;

    public Alarm( int week, String tilte, String url, String des, String time, int priority) {

        this.week = week;
        this.tilte = tilte;
        this.url = url;
        this.des = des;
        this.time = time;
        this.priority = priority;
    }
    public Alarm() {
    }

    public Alarm(int id, int week, String tilte, String url, String des, String time, int priority) {
        this.id = id;
        this.week = week;
        this.tilte = tilte;
        this.url = url;
        this.des = des;
        this.time = time;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
