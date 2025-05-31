package com.example.latihan_database_sqlite.presentation.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.latihan_database_sqlite.R;
import com.example.latihan_database_sqlite.presentation.ui.fragment.FavoriteFragment;
import com.example.latihan_database_sqlite.presentation.ui.fragment.HomeFragment;
import com.example.latihan_database_sqlite.presentation.ui.fragment.KontenFragment;
import com.example.latihan_database_sqlite.presentation.ui.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.menu_konten) {
                fragment = new KontenFragment();
            } else if (itemId == R.id.menu_favorite) {
                fragment = new FavoriteFragment();
            } else if (itemId == R.id.menu_settings) {
                fragment = new SettingsFragment();
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }
            return false;
        });

        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
