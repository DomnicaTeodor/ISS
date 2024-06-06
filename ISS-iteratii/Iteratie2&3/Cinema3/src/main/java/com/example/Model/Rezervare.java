package com.example.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Rezervare")
public class Rezervare extends Entitate{
    @Column(name = "numeClient")
    private String numeClient;
    @Column(name = "prenumeClient")
    private String prenumeClient;
    @Column(name = "nrTelefon")
    private String telefonClient;
    @Column(name = "email")
    private String emailClient;

    public Rezervare() {

    }

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    public String getTelefonClient() {
        return telefonClient;
    }

    public void setTelefonClient(String telefonClient) {
        this.telefonClient = telefonClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Rezervare(String numeClient, String prenumeClient, String telefonClient, String emailClient) {
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.telefonClient = telefonClient;
        this.emailClient = emailClient;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }
}
