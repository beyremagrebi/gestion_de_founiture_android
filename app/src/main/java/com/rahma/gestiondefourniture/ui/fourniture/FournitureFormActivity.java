package com.rahma.gestiondefourniture.ui.fourniture;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;
import com.rahma.gestiondefourniture.model.Fourniture;
import com.rahma.gestiondefourniture.model.TypeFourniture;
import com.rahma.gestiondefourniture.model.User;

import java.util.ArrayList;

public class FournitureFormActivity extends AppCompatActivity {

    EditText edtNom;
    Spinner spType, spUser;
    int fournitureId = -1;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_fourniture_form);

        edtNom = findViewById(R.id.edtNom);
        spType = findViewById(R.id.spType);
        spUser = findViewById(R.id.spUser);


        ArrayList<String> typeNames = new ArrayList<>();
        for (TypeFourniture t : DataStore.types)
            typeNames.add(t.libelle);

        spType.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                typeNames
        ));


        ArrayList<String> userNames = new ArrayList<>();
        for (User u : DataStore.users)
            userNames.add(u.nom);

        spUser.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                userNames
        ));


        if (getIntent().hasExtra("fournitureId")) {
            fournitureId = getIntent().getIntExtra("fournitureId", -1);
            Fourniture f = DataStore.fournitures.get(fournitureId);

            edtNom.setText(f.nom);
            spType.setSelection(DataStore.types.indexOf(f.type));
            spUser.setSelection(DataStore.users.indexOf(f.proprietaire));
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            if (edtNom.getText().toString().isEmpty()) {
                edtNom.setError("Champ obligatoire");
                return;
            }

            TypeFourniture type = DataStore.types.get(spType.getSelectedItemPosition());
            User user = DataStore.users.get(spUser.getSelectedItemPosition());

            if (fournitureId == -1) {
                DataStore.fournitures.add(
                        new Fourniture(
                                DataStore.fournitures.size() + 1,
                                edtNom.getText().toString(),
                                type,
                                user
                        )
                );
            } else {
                Fourniture f = DataStore.fournitures.get(fournitureId);
                f.nom = edtNom.getText().toString();
                f.type = type;
                f.proprietaire = user;
            }
            finish();
        });
    }
}
