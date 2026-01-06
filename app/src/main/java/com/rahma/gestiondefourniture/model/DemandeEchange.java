package com.rahma.gestiondefourniture.model;

public class DemandeEchange {

    public int id;
    public User user;
    public Fourniture fournitureSouhaitee;
    public Fourniture fournitureOfferte;
    public String statut;


    public DemandeEchange(int id, User user, Fourniture fournitureSouhaitee, Fourniture fournitureOfferte) {
        this.id = id;
        this.user = user;
        this.fournitureSouhaitee = fournitureSouhaitee;
        this.fournitureOfferte = fournitureOfferte;
        this.statut = "EN_ATTENTE";
    }
}
