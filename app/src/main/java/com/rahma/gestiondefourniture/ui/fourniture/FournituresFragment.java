package com.rahma.gestiondefourniture.ui.fourniture;

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
import com.rahma.gestiondefourniture.adapter.FournitureAdapter;
import com.rahma.gestiondefourniture.data.DataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FournituresFragment extends Fragment {

    RecyclerView rv;
    FournitureAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fournitures, container, false);

        rv = view.findViewById(R.id.recyclerViewFournitures);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = view.findViewById(R.id.fabAddFourniture);
        fab.setOnClickListener(v ->
                startActivity(new Intent(getContext(), FournitureFormActivity.class))
        );

        adapter = new FournitureAdapter(getContext(), DataStore.fournitures, new FournitureAdapter.OnItemClickListener() {
            @Override
            public void onEdit(int position) {
                startActivity(new Intent(getContext(), FournitureFormActivity.class)
                        .putExtra("fournitureId", position));
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDelete(int position) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Supprimer la fourniture")
                        .setMessage("Êtes-vous sûr de vouloir supprimer cette fourniture ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            DataStore.fournitures.remove(position);
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
