# 🎓 Aplikasi Manajemen Mahasiswa - ProjekUTS



**ProjekUTS** adalah aplikasi Android berbasis Java yang dirancang untuk memudahkan pengguna dalam mengelola data mahasiswa. Aplikasi ini memiliki fitur penyimpanan data mahasiswa, penandaan sebagai favorit, pengingat melalui notifikasi, dan dukungan sensor cahaya untuk pengalaman pengguna yang dinamis.

---

## 🌟 Fitur-Fitur Utama

### 👨‍🎓 Manajemen Data Mahasiswa
- Tambah, edit, dan hapus data mahasiswa.
- Setiap mahasiswa memiliki nama, NIM, jurusan, dan keterangan lainnya.
- Data disimpan secara lokal menggunakan **Room Database**.

### ❤️ Halaman Favorite
- Pengguna bisa menandai mahasiswa sebagai **favorite**.
- Halaman khusus untuk menampilkan hanya mahasiswa favorit.
- Data tersimpan meski aplikasi ditutup.

### 🔔 Notifikasi Jumlah Favorite
- Saat jumlah mahasiswa favorit bertambah, akan muncul **notifikasi otomatis**.
- Notifikasi memberi tahu total mahasiswa favorit saat ini.

### 💡 Sensor Cahaya (Light Sensor)
- Aplikasi merespons **intensitas cahaya** dari lingkungan.
- Contoh: mode gelap otomatis atau perubahan warna latar berdasarkan sensor cahaya.

---

## 🖼️ Screenshot Aplikasi

| Daftar Mahasiswa | Tambah Data | Halaman Favorite | Notifikasi |
|------------------|-------------|------------------|------------|
| ![daftar](screenshots/Screenshot%202025-05-31%20200241.png) | ![tambah](screenshots/Screenshot%202025-05-31%20200217.png) | ![fav](screenshots/favorite.png) | ![notif](screenshots/notification.png) |

---

## 🧠 Teknologi yang Digunakan

- **Java + Android Studio**
- **Room Database**
- **RecyclerView**
- **AlarmManager + Notification**
- **SharedPreferences**
- **SensorManager** (Light Sensor)

---

## 🛠️ Cara Menjalankan Proyek

1. Clone repositori ini:
   ```bash
   git clone https://github.com/ahfi1809/Aplikasi-Data-Mahasiswa-Berbasis-Android.git

