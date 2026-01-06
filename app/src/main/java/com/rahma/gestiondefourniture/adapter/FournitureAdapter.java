package com.rahma.gestiondefourniture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.model.Fourniture;
import java.util.ArrayList;

public class FournitureAdapter extends RecyclerView.Adapter<FournitureAdapter.FournitureViewHolder> {

    private final Context context;
    private final ArrayList<Fourniture> fournitures;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEdit(int position);
        void onDelete(int position);
    }

    public FournitureAdapter(Context context, ArrayList<Fourniture> fournitures, OnItemClickListener listener) {
        this.context = context;
        this.fournitures = fournitures;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FournitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fourniture, parent, false);
        return new FournitureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FournitureViewHolder holder, int position) {
        Fourniture f = fournitures.get(position);
        holder.tvName.setText(f.nom);
        holder.tvType.setText(f.type.libelle);

        holder.itemView.setOnClickListener(v -> listener.onEdit(position));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onDelete(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return fournitures.size();
    }

    static class FournitureViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType;
        public FournitureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFournitureName);
            tvType = itemView.findViewById(R.id.tvFournitureType);
        }
    }
}
