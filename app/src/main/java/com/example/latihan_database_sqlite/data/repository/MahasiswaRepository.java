package com.example.latihan_database_sqlite.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.latihan_database_sqlite.data.database.DatabaseContract;
import com.example.latihan_database_sqlite.data.database.DatabaseHelper;
import com.example.latihan_database_sqlite.data.entity.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaRepository {

    private final DatabaseHelper dbHelper;

    public MahasiswaRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Ambil semua data Mahasiswa
     */
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContract.MahasiswaEntry.TABLE_NAME,
                null, null, null, null, null,
                DatabaseContract.MahasiswaEntry.COLUMN_NIM + " DESC");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MahasiswaEntry.COLUMN_NIM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MahasiswaEntry.COLUMN_NAMA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MahasiswaEntry.COLUMN_NOMOR_HP))
                );
                list.add(mahasiswa);
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    /**
     * Mencari Mahasiswa berdasarkan nama atau NIM
     */
    public List<Mahasiswa> searchMahasiswa(String keyword) {
        List<Mahasiswa> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = DatabaseContract.MahasiswaEntry.COLUMN_NAMA + " LIKE ? OR " +
                DatabaseContract.MahasiswaEntry.COLUMN_NIM + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + keyword + "%", "%" + keyword + "%"};

        Cursor cursor = db.query(DatabaseContract.MahasiswaEntry.TABLE_NAME,
                null, selection, selectionArgs, null, null,
                DatabaseContract.MahasiswaEntry.COLUMN_NAMA + " ASC");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MahasiswaEntry.COLUMN_NIM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MahasiswaEntry.COLUMN_NAMA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MahasiswaEntry.COLUMN_NOMOR_HP))
                );
                list.add(mahasiswa);
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    /**
     * Menambahkan Mahasiswa baru
     */
    public void insertMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MahasiswaEntry.COLUMN_NIM, mahasiswa.getNim());
        values.put(DatabaseContract.MahasiswaEntry.COLUMN_NAMA, mahasiswa.getNama());
        values.put(DatabaseContract.MahasiswaEntry.COLUMN_NOMOR_HP, mahasiswa.getNomorHp());

        db.insert(DatabaseContract.MahasiswaEntry.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Menghapus Mahasiswa berdasarkan NIM
     */
    public void deleteMahasiswa(String nim) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = DatabaseContract.MahasiswaEntry.COLUMN_NIM + " = ?";
        String[] selectionArgs = {nim};
        db.delete(DatabaseContract.MahasiswaEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    /**
     * Update data Mahasiswa
     */
    public void updateMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MahasiswaEntry.COLUMN_NAMA, mahasiswa.getNama());
        values.put(DatabaseContract.MahasiswaEntry.COLUMN_NOMOR_HP, mahasiswa.getNomorHp());

        String selection = DatabaseContract.MahasiswaEntry.COLUMN_NIM + " = ?";
        String[] selectionArgs = {mahasiswa.getNim()};

        db.update(DatabaseContract.MahasiswaEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }
}
