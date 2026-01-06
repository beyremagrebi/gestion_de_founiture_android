package com.rahma.gestiondefourniture.ui.demande;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;
import com.rahma.gestiondefourniture.model.DemandeEchange;
import com.rahma.gestiondefourniture.model.Fourniture;
import com.rahma.gestiondefourniture.model.User;

import java.util.ArrayList;

public class DemandeFormActivity extends AppCompatActivity {

    Spinner spUser, spFournitureSouhaitee, spFournitureOfferte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_form);

        spUser = findViewById(R.id.spUser);
        spFournitureSouhaitee = findViewById(R.id.spFournitureSouhaitee);
        spFournitureOfferte = findViewById(R.id.spFournitureOfferte);
        
        ArrayList<String> userNames = new ArrayList<>();
        for (User u : DataStore.users)
            userNames.add(u.nom);

        spUser.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                userNames
        ));
        
        ArrayList<String> fournitureNames = new ArrayList<>();
        for (Fourniture f : DataStore.fournitures)
            fournitureNames.add(f.nom);

        spFournitureSouhaitee.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fournitureNames
        ));

        // Fourniture offerte
        spFournitureOfferte.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fournitureNames
        ));

        findViewById(R.id.btnSave).setOnClickListener(v -> {

            if (DataStore.users.isEmpty() || DataStore.fournitures.size() < 2) {
                Toast.makeText(this, "Données manquantes", Toast.LENGTH_SHORT).show();
                return;
            }

            User u = DataStore.users.get(spUser.getSelectedItemPosition());
            Fourniture souhait = DataStore.fournitures.get(spFournitureSouhaitee.getSelectedItemPosition());
            Fourniture offerte = DataStore.fournitures.get(spFournitureOfferte.getSelectedItemPosition());

          
            if (souhait == offerte) {
                Toast.makeText(this, "La fourniture offerte ne peut pas être la même que celle souhaitée", Toast.LENGTH_SHORT).show();
                return;
            }
            
            DataStore.addDemande(u, souhait, offerte);

            Toast.makeText(this, "Demande envoyée", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
