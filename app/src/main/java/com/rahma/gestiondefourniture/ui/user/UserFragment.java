package com.rahma.gestiondefourniture.ui.user;

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
import com.rahma.gestiondefourniture.adapter.UserAdapter;
import com.rahma.gestiondefourniture.data.DataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserFragment extends Fragment {

    RecyclerView rv;
    UserAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        rv = view.findViewById(R.id.recyclerViewUsers);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = view.findViewById(R.id.fabAddUser);
        fab.setOnClickListener(v ->
                startActivity(new Intent(getContext(), UserFormActivity.class))
        );

        // ✅ Initialize adapter with DataStore.users directly
        adapter = new UserAdapter(getContext(), DataStore.users, new UserAdapter.OnItemClickListener() {
            @Override
            public void onEdit(int position) {
                startActivity(new Intent(getContext(), UserFormActivity.class)
                        .putExtra("userId", position));
            }


            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDelete(int position) {
                new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Supprimer l'utilisateur")
                        .setMessage("Êtes-vous sûr de vouloir supprimer cet utilisateur ?")
                        .setPositiveButton("Oui", (dialog, which) -> {

                            DataStore.users.remove(position);
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
