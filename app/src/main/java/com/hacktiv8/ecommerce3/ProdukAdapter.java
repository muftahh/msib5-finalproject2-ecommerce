package com.hacktiv8.ecommerce3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProdukAdapter extends FirebaseRecyclerAdapter<Barang, ProdukAdapter.viewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    public ProdukAdapter(@NonNull FirebaseRecyclerOptions<Barang> options, RecyclerViewInterface recyclerViewInterface) {
        super(options);
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Barang model) {
        if (position < getItemCount()) {
            holder.listNamaProduk.setText(model.getmNamaBarang());
            holder.listHargaProduk.setText(model.getmHargaBarang());
        } else {
            // Handle the case where the position is invalid
        }
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new viewHolder(view, recyclerViewInterface);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView listNamaProduk, listHargaProduk;
        ImageButton btnDetailProduk;

        public viewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            listNamaProduk = itemView.findViewById(R.id.listNamaProduk);
            listHargaProduk = itemView.findViewById(R.id.listHargaProduk);
            btnDetailProduk = itemView.findViewById(R.id.btnDetailProduk);

            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int position = getBindingAdapterPosition();

                    if (position != RecyclerView.NO_POSITION && position < getItemCount()) {
                        recyclerViewInterface.onDetailClick(position);
                    }
                }
            });
        }

    }

}
