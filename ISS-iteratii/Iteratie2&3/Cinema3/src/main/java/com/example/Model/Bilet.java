package com.example.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Bilet")
public class Bilet extends Entitate{
    @Column(name = "idLoc")
    private Integer idLoc;
    @Column(name = "idRezervare")
    private Integer idRezervare;

    public Integer getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(Integer idLoc) {
        this.idLoc = idLoc;
    }

    public Integer getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(Integer idRezervare) {
        this.idRezervare = idRezervare;
    }

    public Bilet() {

    }

    public Bilet(Integer idLoc, Integer idRezervare) {
        this.idLoc = idLoc;
        this.idRezervare = idRezervare;
    }
}
