package com.example.latihan_database_sqlite.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_database_sqlite.R;
import com.example.latihan_database_sqlite.data.entity.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private final List<Mahasiswa> mahasiswaList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(Mahasiswa mahasiswa);
    }

    public MahasiswaAdapter(List<Mahasiswa> mahasiswaList, OnItemClickListener listener) {
        this.mahasiswaList = mahasiswaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        holder.bind(mahasiswaList.get(position));
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNim;
        private final TextView tvNama;
        private final TextView tvNomorHp;

        MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNomorHp = itemView.findViewById(R.id.tv_nomor_hp);
        }

        void bind(Mahasiswa mahasiswa) {
            tvNim.setText(mahasiswa.getNim());
            tvNama.setText(mahasiswa.getNama());
            tvNomorHp.setText(mahasiswa.getNomorHp());

            itemView.setOnClickListener(v -> listener.onItemClick(mahasiswa));
        }
    }
}
