package com.example.demo.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Entity
@Table(name="spam_ctrl_db8")
public class Subscribers {
    @Id
    @Column(name = "msisdn")
    private int msisdn;
    @Column(name = "mode")
    private String mode;

    @Column(name = "timestamp")
    @Generated(value = GenerationTime.INSERT)
    private String timestamp;

    public Subscribers() {
        // default constructor
    }


    public Subscribers(int msisdn, String mode) {
        this.msisdn = msisdn;
        this.mode = mode;
    }


    public int getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(int msisdn) {
        this.msisdn = msisdn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
