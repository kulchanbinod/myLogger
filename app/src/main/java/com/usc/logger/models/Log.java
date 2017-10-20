package com.usc.logger.models;

import java.util.UUID;

public class Log {

    private UUID mId;

    private String date;
    private String type;
    private String title;
    private String comment;
    private String duration;
    private String location;
    private String destination;

    public Log(){
        mId = UUID.randomUUID();
    }

    public Log(UUID id) {
        mId = id;
    }

    public Log(String title, String date, String destination, String duration, String comment, String type) {
        this(UUID.randomUUID());
        this.type = type;
        this.date = date;
        this.title = title;
        this.comment = comment;
        this.duration = duration;
        this.destination = destination;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
