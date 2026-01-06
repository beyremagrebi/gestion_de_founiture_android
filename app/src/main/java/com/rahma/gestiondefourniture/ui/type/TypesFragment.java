package com.rahma.gestiondefourniture.ui.type;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;

import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.adapter.TypeAdapter;
import com.rahma.gestiondefourniture.data.DataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TypesFragment extends Fragment {

    RecyclerView rv;
    TypeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_types, container, false);

        rv = view.findViewById(R.id.recyclerViewTypes);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = view.findViewById(R.id.fabAddType);
        fab.setOnClickListener(v ->
                startActivity(new Intent(getContext(), TypeFormActivity.class))
        );

        adapter = new TypeAdapter(getContext(), DataStore.types, new TypeAdapter.OnItemClickListener() {
            @Override
            public void onEdit(int position) {
                startActivity(new Intent(getContext(), TypeFormActivity.class)
                        .putExtra("typeId", position));
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDelete(int position) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Supprimer le type")
                        .setMessage("Êtes-vous sûr de vouloir supprimer ce type ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            DataStore.types.remove(position);
                            adapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("Non", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });

        rv.setAdapter(adapter);

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
