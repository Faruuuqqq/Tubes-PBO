CREATE DATABASE laundry_pbo_db;
USE laundry_pbo_db;

-- 1. Tabel Pegawai
CREATE TABLE pegawai (
    id_pegawai INT AUTO_INCREMENT PRIMARY KEY,
    `password` varchar(255) not null,
    nama_pegawai VARCHAR(100) NOT NULL,
    usia_pegawai INT,
    jk_pegawai ENUM('L', 'P'),
    no_hp_pegawai VARCHAR(15),
    alamat_pegawai TEXT,
    start_date DATE
);

-- 2. Tabel Pelanggan
CREATE TABLE pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    no_hp_pelanggan VARCHAR(15),
    alamat_pelanggan TEXT
);

-- 3. Tabel Jenis Item (Layanan)
CREATE TABLE jenis_item (
    id_jenis_item INT AUTO_INCREMENT PRIMARY KEY,
    nama_item VARCHAR(50) NOT NULL,
    harga_per_kg DOUBLE NOT NULL
);

-- 4. Tabel Pesanan (Header Transaksi)
CREATE TABLE pesanan (
    id_pesanan INT AUTO_INCREMENT PRIMARY KEY,
    id_pelanggan INT,
    id_pegawai INT,
    tgl_diterima DATETIME DEFAULT CURRENT_TIMESTAMP,
    tgl_selesai DATETIME default null,
    total_kg DOUBLE,
    total_biaya DOUBLE,
    status ENUM('IN PROGRESS', 'SELESAI') DEFAULT 'IN PROGRESS',
    FOREIGN KEY (id_pelanggan) REFERENCES pelanggan(id_pelanggan),
    FOREIGN KEY (id_pegawai) REFERENCES pegawai(id_pegawai)
);

-- 5. Tabel Detail Pesanan (Rincian Item)
-- Note: id_pelanggan dihapus untuk normalisasi
CREATE TABLE detail_pesanan (
    id_detail INT AUTO_INCREMENT PRIMARY KEY,
    id_pesanan INT,
    id_jenis_item INT,
    berat_item DOUBLE,
    total_harga_item DOUBLE,
    FOREIGN KEY (id_pesanan) REFERENCES pesanan(id_pesanan) ON DELETE CASCADE,
    FOREIGN KEY (id_jenis_item) REFERENCES jenis_item(id_jenis_item)
);

-- Insert Data Dummy (Agar nanti bisa langsung tes login/tampil data)
INSERT INTO pegawai (nama_pegawai, jk_pegawai, start_date) VALUES ('Admin', 'L', '2025-01-01');
INSERT INTO jenis_item (nama_item, harga_per_kg) VALUES ('Cuci Kering', 5000), ('Cuci Setrika', 8000), ('Bed Cover', 12000);