package com.example.admin_login_backend.entity;

public class DisabledSlot {
    private String sala;
    private String date;
    private String timeSlot;
    private boolean disabled;

    public DisabledSlot() {}

    public DisabledSlot(String sala, String date, String timeSlot, boolean disabled) {
        this.sala = sala;
        this.date = date;
        this.timeSlot = timeSlot;
        this.disabled = disabled;
    }

    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {        this.sala = sala;    }
    public String getDate() {        return date;    }
    public void setDate(String date) {        this.date = date;    }
    public String getTimeSlot() {        return timeSlot;    }
    public void setTimeSlot(String timeSlot) {        this.timeSlot = timeSlot;    }
    public boolean isDisabled() {        return disabled;    }
    public void setDisabled(boolean disabled) {        this.disabled = disabled;}
}