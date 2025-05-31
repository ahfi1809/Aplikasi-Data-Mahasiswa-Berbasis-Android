package com.example.latihan_database_sqlite.data.database;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {}

    public static class MahasiswaEntry implements BaseColumns {
        public static final String TABLE_NAME = "mahasiswa";
        public static final String COLUMN_NIM = "nim";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_NOMOR_HP = "nomor_hp";
    }
}
