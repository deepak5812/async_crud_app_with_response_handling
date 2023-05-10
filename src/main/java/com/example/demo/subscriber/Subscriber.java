package com.example.demo.subscriber;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "spam_ctrl_db8")
public class Subscriber {
    @Id
    private int msisdn;

    private String mode;

    // while "timestamp" is no "reserved keyword" in postgres, it is in standard SQL
    // : https://www.postgresql.org/docs/current/sql-keywords-appendix.html
    @Generated(value = GenerationTime.INSERT)
    private String timestamp;

    public Subscriber() {
        // default constructor
    }

    public Subscriber(int msisdn, String mode) {
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
