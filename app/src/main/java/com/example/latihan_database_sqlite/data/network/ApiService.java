package com.example.latihan_database_sqlite.data.network;

import com.example.latihan_database_sqlite.data.entity.MahasiswaResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("mahasiswa")
    Call<MahasiswaResponse> getAllMahasiswa();
}
