package com.example.latihan_database_sqlite.data.entity;

import java.util.List;

public class MahasiswaResponse {
    private boolean success;
    private String message;
    private List<Mahasiswa> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Mahasiswa> getData() {
        return data;
    }
}
