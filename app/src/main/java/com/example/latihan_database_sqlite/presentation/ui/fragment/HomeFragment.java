package com.example.latihan_database_sqlite.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.latihan_database_sqlite.R;
import com.example.latihan_database_sqlite.data.entity.Mahasiswa;
import com.example.latihan_database_sqlite.data.repository.MahasiswaRepository;

public class HomeFragment extends Fragment {

    private EditText etNim, etNama, etNomorHp;
    private Button btnSimpan;
    private MahasiswaRepository mahasiswaRepository;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inisialisasi View
        etNim = view.findViewById(R.id.et_nim);
        etNama = view.findViewById(R.id.et_nama);
        etNomorHp = view.findViewById(R.id.et_nomor_hp);
        btnSimpan = view.findViewById(R.id.btn_simpan);

        // Inisialisasi Repository
        mahasiswaRepository = new MahasiswaRepository(requireContext());

        // Event klik tombol simpan
        btnSimpan.setOnClickListener(v -> {
            String nim = etNim.getText().toString().trim();
            String nama = etNama.getText().toString().trim();
            String nomorHp = etNomorHp.getText().toString().trim();

            if (!nim.isEmpty() && !nama.isEmpty() && !nomorHp.isEmpty()) {
                // Buat objek Mahasiswa
                Mahasiswa mahasiswa = new Mahasiswa(nim, nama, nomorHp);

                // Simpan ke database lewat repository
                mahasiswaRepository.insertMahasiswa(mahasiswa);

                // Tampilkan toast
                Toast.makeText(getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                // Bersihkan form input
                etNim.setText("");
                etNama.setText("");
                etNomorHp.setText("");
            } else {
                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
