package com.example.latihan_database_sqlite.data.entity;

public class Mahasiswa {
    private final String nim;
    private final String nama;
    private final String nomorHp;

    public Mahasiswa(String nim, String nama, String nomorHp) {
        this.nim = nim;
        this.nama = nama;
        this.nomorHp = nomorHp;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorHp() {
        return nomorHp;
    }
}
