package com.example.latihan_database_sqlite.presentation.ui.fragment;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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

public class FavoriteFragment extends Fragment {

    private static final String CHANNEL_ID = "favorite_count_channel";
    private static final int NOTIFICATION_ID = 101;

    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private MahasiswaAdapter adapter;
    private List<Mahasiswa> favoriteList = new ArrayList<>();
    private MahasiswaViewModel viewModel;

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_favorite);
        tvEmpty = view.findViewById(R.id.tv_empty_favorite);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new MahasiswaAdapter(favoriteList, mahasiswa -> {
            // Opsional: Hapus dari favorit saat item diklik
            SharedPreferences prefs = requireContext().getSharedPreferences("favorite_mahasiswa", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(mahasiswa.getNim());
            editor.apply();

            favoriteList.remove(mahasiswa);
            adapter.notifyDataSetChanged();

            if (favoriteList.isEmpty()) {
                tvEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            // Update notifikasi setelah hapus favorit
            showFavoriteCountNotification(favoriteList.size());
        });

        recyclerView.setAdapter(adapter);

        // Buat channel notifikasi
        createNotificationChannel();

        // Ambil data lengkap dari ViewModel
        viewModel = new ViewModelProvider(this).get(MahasiswaViewModel.class);

        viewModel.getMahasiswaList().observe(getViewLifecycleOwner(), list -> {
            loadFavoriteMahasiswa(list);
        });

        return view;
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void loadFavoriteMahasiswa(List<Mahasiswa> allMahasiswa) {
        SharedPreferences prefs = requireContext().getSharedPreferences("favorite_mahasiswa", Context.MODE_PRIVATE);

        favoriteList.clear();

        for (Mahasiswa m : allMahasiswa) {
            if (prefs.getBoolean(m.getNim(), false)) {
                favoriteList.add(m);
            }
        }

        if (favoriteList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        adapter.notifyDataSetChanged();

        // Tampilkan notifikasi jumlah favorit
        showFavoriteCountNotification(favoriteList.size());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Favorite Count Channel";
            String description = "Channel untuk notifikasi jumlah favorit";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void showFavoriteCountNotification(int count) {
        String contentText = count > 0 ? "Anda memiliki " + count + " data mahasiswa favorit." : "Tidak ada data favorit.";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_favorite) // Pastikan ikon ini ada di drawable
                .setContentTitle("Jumlah Data Favorit")
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
