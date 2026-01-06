package com.rahma.gestiondefourniture.data;

import com.rahma.gestiondefourniture.model.*;

import java.util.ArrayList;

public class DataStore {


    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Fourniture> fournitures = new ArrayList<>();
    public static ArrayList<TypeFourniture> types = new ArrayList<>();
    public static ArrayList<DemandeEchange> demandes = new ArrayList<>();


    private static int userId = 1;
    private static int typeId = 1;
    private static int fournitureId = 1;
    private static int demandeId = 1;


    public static User addUser(String nom, String email) {
        User user = new User(userId++, nom, email);
        users.add(user);
        return user;
    }


    public static TypeFourniture addType(String libelle) {
        TypeFourniture type = new TypeFourniture(typeId++, libelle);
        types.add(type);
        return type;
    }



    public static Fourniture addFourniture(String nom, TypeFourniture type, User proprietaire) {
        Fourniture f = new Fourniture(fournitureId++, nom, type, proprietaire);
        fournitures.add(f);
        return f;
    }


    public static void addDemande(User user, Fourniture fournitureSouhaitee, Fourniture fournitureOfferte) {
        DemandeEchange d = new DemandeEchange(demandeId++, user, fournitureSouhaitee, fournitureOfferte);
        demandes.add(d);
    }
    public static void initData() {
        if (!users.isEmpty()) return;

        User u1 = addUser("Rahma", "rahma@gmail.com");
        User u2 = addUser("Ali", "ali@gmail.com");

        TypeFourniture t1 = addType("Cahier");
        TypeFourniture t2 = addType("Stylo");

        Fourniture f1 = addFourniture("Cahier 100 pages", t1, u1);
        Fourniture f2 = addFourniture("Stylo bleu", t2, u2);

        addDemande(u2, f1, f2);
    }

}
