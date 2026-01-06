package com.rahma.gestiondefourniture.ui.type;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;
import com.rahma.gestiondefourniture.model.TypeFourniture;

public class TypeFormActivity extends AppCompatActivity {

    EditText edtLibelle;
    int typeId = -1;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_type_form);

        edtLibelle = findViewById(R.id.edtLibelle);

        if (getIntent().hasExtra("typeId")) {
            typeId = getIntent().getIntExtra("typeId", -1);
            TypeFourniture t = DataStore.types.get(typeId);
            edtLibelle.setText(t.libelle);
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            if (edtLibelle.getText().toString().isEmpty()) {
                edtLibelle.setError("Champ obligatoire");
                return;
            }

            if (typeId == -1) {
                DataStore.types.add(
                        new TypeFourniture(
                                DataStore.types.size() + 1,
                                edtLibelle.getText().toString()
                        )
                );
            } else {
                DataStore.types.get(typeId).libelle =
                        edtLibelle.getText().toString();
            }
            finish();
        });
    }
}
