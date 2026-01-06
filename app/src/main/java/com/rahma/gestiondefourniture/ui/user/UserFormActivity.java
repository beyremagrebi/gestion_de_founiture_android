package com.rahma.gestiondefourniture.ui.user;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;
import com.rahma.gestiondefourniture.model.User;

public class UserFormActivity extends AppCompatActivity {

    EditText edtNom, edtEmail;
    int userId = -1;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_user_form);

        edtNom = findViewById(R.id.edtNom);
        edtEmail = findViewById(R.id.edtEmail);

        if (getIntent().hasExtra("userId")) {
            userId = getIntent().getIntExtra("userId", -1);
            User u = DataStore.users.get(userId);
            edtNom.setText(u.nom);
            edtEmail.setText(u.email);
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            if (userId == -1) {
                DataStore.users.add(
                        new User(
                                DataStore.users.size() + 1,
                                edtNom.getText().toString(),
                                edtEmail.getText().toString()
                        )
                );
            } else {
                User u = DataStore.users.get(userId);
                u.nom = edtNom.getText().toString();
                u.email = edtEmail.getText().toString();
            }
            finish();
        });
    }
}
