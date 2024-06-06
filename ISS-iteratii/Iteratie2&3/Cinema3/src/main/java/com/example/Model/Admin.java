package com.example.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Admin")
public class Admin extends Entitate {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
