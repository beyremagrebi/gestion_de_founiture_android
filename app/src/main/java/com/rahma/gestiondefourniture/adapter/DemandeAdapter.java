package com.rahma.gestiondefourniture.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.data.DataStore;
import com.rahma.gestiondefourniture.model.DemandeEchange;
import com.rahma.gestiondefourniture.ui.demande.DemandeFormActivity;

import java.util.ArrayList;

public class DemandeAdapter extends RecyclerView.Adapter<DemandeAdapter.DemandeViewHolder> {

    private final Context context;
    private final ArrayList<DemandeEchange> demandes;

    public DemandeAdapter(Context context, ArrayList<DemandeEchange> demandes) {
        this.context = context;
        this.demandes = demandes;
    }

    @NonNull
    @Override
    public DemandeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_demande, parent, false);
        return new DemandeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DemandeViewHolder holder, int position) {
        DemandeEchange d = demandes.get(position);
        holder.tvUser.setText("Utilisateur : " + d.user.nom);
        holder.tvSouhaitee.setText("Souhaite : " + d.fournitureSouhaitee.nom);
        holder.tvOfferte.setText("Offre : " + d.fournitureOfferte.nom);
        holder.tvStatut.setText("Statut : " + d.statut);

        // ---------------- Disable Accept/Refuse buttons if treated ----------------
        if (d.statut.equals("ACCEPTEE") || d.statut.equals("REFUSEE")) {
            holder.btnAccept.setEnabled(false);
            holder.btnRefuse.setEnabled(false);
            holder.btnAccept.setAlpha(0.5f);  // visually grayed
            holder.btnRefuse.setAlpha(0.5f);
        } else {
            holder.btnAccept.setEnabled(true);
            holder.btnRefuse.setEnabled(true);
            holder.btnAccept.setAlpha(1f);
            holder.btnRefuse.setAlpha(1f);
        }

        // ---------------- Tap card → edit demande ----------------
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, DemandeFormActivity.class);
            i.putExtra("demandeId", d.id);
            context.startActivity(i);
        });

        // ---------------- Accept ----------------
        holder.btnAccept.setOnClickListener(v -> {
            if (!d.statut.equals("EN_ATTENTE")) return;
            new AlertDialog.Builder(context)
                    .setTitle("Accepter la demande")
                    .setMessage("Voulez-vous accepter cette demande ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        d.statut = "ACCEPTEE";
                        notifyItemChanged(position); // update button state
                    })
                    .setNegativeButton("Non", null)
                    .show();
        });

        // ---------------- Refuse ----------------
        holder.btnRefuse.setOnClickListener(v -> {
            if (!d.statut.equals("EN_ATTENTE")) return;
            new AlertDialog.Builder(context)
                    .setTitle("Refuser la demande")
                    .setMessage("Voulez-vous refuser cette demande ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        d.statut = "REFUSEE";
                        notifyItemChanged(position); // update button state
                    })
                    .setNegativeButton("Non", null)
                    .show();
        });

        // ---------------- Long press → delete ----------------
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Supprimer la demande")
                    .setMessage("Êtes-vous sûr de vouloir supprimer cette demande ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        demandes.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, demandes.size());
                    })
                    .setNegativeButton("Non", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return demandes.size();
    }

    static class DemandeViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser, tvSouhaitee, tvOfferte, tvStatut;
        Button btnAccept, btnRefuse;

        public DemandeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvDemandeUser);
            tvSouhaitee = itemView.findViewById(R.id.tvDemandeFournitureSouhaitee);
            tvOfferte = itemView.findViewById(R.id.tvDemandeFournitureOfferte);
            tvStatut = itemView.findViewById(R.id.tvDemandeStatut);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnRefuse = itemView.findViewById(R.id.btnRefuse);
        }
    }
}
