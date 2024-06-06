package com.example.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Table(name = "Locuri")
public class Loc extends Entitate{
    @Column(name = "rand")
    private Integer rand;
    @Column(name = "loja")
    private Integer loja;
    @Column(name = "pret")
    private Integer pret;
    @Column(name = "stare")
    private Integer stare;

    public Loc(Integer id, Integer rand, Integer loja, Integer pret, Integer stare) {
        this.setId(id);
        this.rand = rand;
        this.loja = loja;
        this.pret = pret;
        this.stare = stare;
    }

    public Loc() {
    }

    public Integer getRand() {
        return rand;
    }

    public void setRand(Integer rand) {
        this.rand = rand;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public Integer getPret() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public Integer getStare() {
        return stare;
    }

    public void setStare(Integer stare) {
        this.stare = stare;
    }
}
