package com.rahma.gestiondefourniture.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;
import com.rahma.gestiondefourniture.ui.home.HomeFragment;
import com.rahma.gestiondefourniture.ui.user.UserFragment;
import com.rahma.gestiondefourniture.ui.type.TypesFragment;
import com.rahma.gestiondefourniture.ui.fourniture.FournituresFragment;
import com.rahma.gestiondefourniture.ui.demande.DemandesFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        DataStore.initData();
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            if(item.getItemId() == R.id.nav_home){
                selected = new HomeFragment();
            }
            else if (item.getItemId() == R.id.nav_users) {
                selected = new UserFragment();
            } else if (item.getItemId() == R.id.nav_types) {
                selected = new TypesFragment();
            } else if (item.getItemId() == R.id.nav_fournitures) {
                selected = new FournituresFragment();
            } else if (item.getItemId() == R.id.nav_demandes) {
                selected = new DemandesFragment();
            }
            if (selected != null)
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, selected)
                        .commit();
            return true;
        });
    }
}
