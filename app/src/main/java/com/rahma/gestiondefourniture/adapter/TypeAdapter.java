package com.rahma.gestiondefourniture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rahma.gestiondefourniture.R;
import com.rahma.gestiondefourniture.model.TypeFourniture;
import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private final Context context;
    private final ArrayList<TypeFourniture> types;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEdit(int position);
        void onDelete(int position);
    }

    public TypeAdapter(Context context, ArrayList<TypeFourniture> types, OnItemClickListener listener) {
        this.context = context;
        this.types = types;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        TypeFourniture type = types.get(position);
        holder.tvName.setText(type.libelle);

        holder.itemView.setOnClickListener(v -> listener.onEdit(position));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onDelete(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    static class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTypeName);
        }
    }
}
