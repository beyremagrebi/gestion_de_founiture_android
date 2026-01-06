package com.rahma.gestiondefourniture.model;

public class User {
    public int id;
    public String nom;
    public String email;

    public User(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }
}