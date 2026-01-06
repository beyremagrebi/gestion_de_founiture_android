package com.rahma.gestiondefourniture.model;

public class Fourniture {
    public int id;
    public String nom;
    public TypeFourniture type;
    public User proprietaire;

    public Fourniture(int id, String nom, TypeFourniture type, User proprietaire) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.proprietaire = proprietaire;
    }
}