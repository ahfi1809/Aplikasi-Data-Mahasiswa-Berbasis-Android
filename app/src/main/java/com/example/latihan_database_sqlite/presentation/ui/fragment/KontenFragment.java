package com.example.latihan_database_sqlite.presentation.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_database_sqlite.R;
import com.example.latihan_database_sqlite.data.entity.Mahasiswa;
import com.example.latihan_database_sqlite.presentation.adapter.MahasiswaAdapter;
import com.example.latihan_database_sqlite.presentation.viewmodel.MahasiswaViewModel;

import java.util.ArrayList;
import java.util.List;

public class KontenFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private MahasiswaAdapter adapter;
    private MahasiswaViewModel viewModel;
    private List<Mahasiswa> mahasiswaList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konten, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MahasiswaAdapter(mahasiswaList, mahasiswa -> {
            SharedPreferences prefs = requireContext().getSharedPreferences("favorite_mahasiswa", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(mahasiswa.getNim(), true);
            editor.apply();

            Toast.makeText(getContext(), "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MahasiswaViewModel.class);

        viewModel.getMahasiswaList().observe(getViewLifecycleOwner(), list -> {
            mahasiswaList.clear();
            mahasiswaList.addAll(list);
            adapter.notifyDataSetChanged();
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchMahasiswa(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchMahasiswa(newText);
                return false;
            }
        });

        return view;
    }
}
