package com.example.latihan_database_sqlite.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.latihan_database_sqlite.data.entity.Mahasiswa;
import com.example.latihan_database_sqlite.data.repository.MahasiswaRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MahasiswaViewModel extends AndroidViewModel {
    private final MahasiswaRepository repository;
    private final MutableLiveData<List<Mahasiswa>> mahasiswaList = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        repository = new MahasiswaRepository(application);
        loadData();
    }

    public MutableLiveData<List<Mahasiswa>> getMahasiswaList() {
        return mahasiswaList;
    }

    public void loadData() {
        executorService.execute(() -> {
            List<Mahasiswa> list = repository.getAllMahasiswa();
            mahasiswaList.postValue(list);
        });
    }

    public void searchMahasiswa(String keyword) {
        executorService.execute(() -> {
            List<Mahasiswa> filteredList = repository.searchMahasiswa(keyword);
            mahasiswaList.postValue(filteredList);
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }
}
