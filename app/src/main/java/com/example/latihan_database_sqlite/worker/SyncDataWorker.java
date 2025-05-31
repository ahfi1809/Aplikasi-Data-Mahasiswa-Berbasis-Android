package com.example.latihan_database_sqlite.worker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.latihan_database_sqlite.data.network.ApiClient;
import com.example.latihan_database_sqlite.data.network.ApiService;
import com.example.latihan_database_sqlite.data.database.DatabaseHelper;
import com.example.latihan_database_sqlite.data.entity.Mahasiswa;
import com.example.latihan_database_sqlite.data.entity.MahasiswaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SyncDataWorker extends Worker {

    public SyncDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            ApiService apiService = ApiClient.getApiService();
            Call<MahasiswaResponse> call = apiService.getAllMahasiswa();
            Response<MahasiswaResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                List<Mahasiswa> apiData = response.body().getData();
                saveApiDataToLocal(apiData);
                return Result.success();
            }
            return Result.failure();
        } catch (Exception e) {
            return Result.failure();
        }
    }

    private void saveApiDataToLocal(List<Mahasiswa> apiData) {
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM mahasiswa");

            String sql = "INSERT INTO mahasiswa VALUES (?, ?, ?)";
            SQLiteStatement stmt = db.compileStatement(sql);

            for (Mahasiswa mhs : apiData) {
                stmt.bindString(1, mhs.getNim());
                stmt.bindString(2, mhs.getNama());
                stmt.bindString(3, mhs.getNomorHp());
                stmt.executeInsert();
                stmt.clearBindings();
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }
}
