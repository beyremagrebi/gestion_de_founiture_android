package com.rahma.gestiondefourniture.ui.demande;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.adapter.DemandeAdapter;
import com.rahma.gestiondefourniture.data.DataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DemandesFragment extends Fragment {

    RecyclerView rv;
    DemandeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demandes, container, false);

        rv = view.findViewById(R.id.recyclerViewDemandes);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new DemandeAdapter(getContext(), DataStore.demandes);
        rv.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fabAddDemande);
        fab.setOnClickListener(v ->
                startActivity(new Intent(getContext(), DemandeFormActivity.class))
        );

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
