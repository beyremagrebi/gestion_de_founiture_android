package com.rahma.gestiondefourniture.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;

public class HomeFragment extends Fragment {

    TextView tvUsers, tvTypes, tvFournitures, tvDemandes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvUsers = view.findViewById(R.id.tvUsersCount);
        tvTypes = view.findViewById(R.id.tvTypesCount);
        tvFournitures = view.findViewById(R.id.tvFournituresCount);
        tvDemandes = view.findViewById(R.id.tvDemandesCount);

        updateCounts();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCounts();
    }

    private void updateCounts() {
        tvUsers.setText(String.valueOf(DataStore.users.size()));
        tvTypes.setText(String.valueOf(DataStore.types.size()));
        tvFournitures.setText(String.valueOf(DataStore.fournitures.size()));
        tvDemandes.setText(String.valueOf(DataStore.demandes.size()));
    }
}
