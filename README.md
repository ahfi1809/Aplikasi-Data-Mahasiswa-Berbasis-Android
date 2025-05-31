# 📱 ProjekUTS - Aplikasi Manajemen Aktivitas Harian

![ProjekUTS Banner](screenshots/banner.png)

**ProjekUTS** adalah aplikasi Android yang dirancang untuk membantu pengguna mengelola aktivitas harian mereka dengan mudah. Aplikasi ini dilengkapi dengan fitur dashboard interaktif, pengingat (reminder), dan manajemen aktivitas berbasis database lokal menggunakan `Room`. Dikembangkan menggunakan Java dan Android Studio sebagai bagian dari tugas UTS.

---

## ✨ Fitur Unggulan

### 🗓️ Dashboard Harian
- Tampilkan daftar aktivitas setiap hari dalam tampilan **RecyclerView**.
- Mendukung tampilan dinamis sesuai tanggal.

### ➕ Manajemen Aktivitas
- Tambah, edit, dan hapus aktivitas.
- Form input yang simpel dan mudah digunakan.
- Setiap aktivitas memiliki nama, deskripsi, dan tanggal.

### ⏰ Pengingat Aktivitas
- Reminder menggunakan **AlarmManager** dan **Notification**.
- Notifikasi akan muncul sesuai waktu aktivitas.

### 💾 Penyimpanan Data
- Menggunakan **Room Database** untuk menyimpan aktivitas.
- Simpan preferensi pengguna menggunakan **SharedPreferences**.

### 🧭 Navigasi Antar Halaman
- Navigasi menggunakan **Intent** antar aktivitas.
- Navigasi antar fragment (jika ada tab seperti Dashboard dan Favorit).

---

## 🖼️ Screenshot Aplikasi

| Dashboard | Tambah Aktivitas | Notifikasi |
|----------|------------------|------------|
| ![ss1](screenshots/dashboard.png) | ![ss2](screenshots/add_activity.png) | ![ss3](screenshots/notification.png) |

---

## 🛠️ Teknologi yang Digunakan

- **Java**
- **Android Studio**
- **Room Database**
- **RecyclerView**
- **Sensor Light & Notification**
- **SharedPreferences**

---

## 🚀 Cara Menjalankan Proyek

1. Clone repositori ini:
   ```bash
   git clone https://github.com/ahfi1809/Aplikasi-Data-Mahasiswa-Berbasis-Android.git

