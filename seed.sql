USE laundry_pbo_db;

-- ==========================================
-- 1. Seed Data Pegawai
-- ==========================================
INSERT INTO pegawai (nama_pegawai, `password`, usia_pegawai, jk_pegawai, no_hp_pegawai, alamat_pegawai, start_date) VALUES 
('Budi Santoso', 'admin1', 30, 'L', '081234567890', 'Jl. Merpati No. 10, Jakarta', '2023-01-15'),
('Siti Aminah', 'admin2' 25, 'P', '085678901234', 'Jl. Kenanga No. 5, Bekasi', '2023-03-01'),
('Rudi Hermawan', 'admin3', 28, 'L', '081345678901', 'Jl. Melati No. 3, Depok', '2023-06-10'),
('Joko Anwar', 'admin4', 35, 'L', '081299887766', 'Jl. Bioskop No. 21, Jakarta', '2022-12-01'),
('Luna Maya', 'admin5', 32, 'P', '081311223344', 'Jl. Bintang No. 2, Bali', '2023-02-20'),
('Deddy Corbuzier', 'admin6', 40, 'L', '081233445566', 'Jl. Podcast No. 1, Jakarta', '2023-05-05');

-- ==========================================
-- 2. Seed Data Pelanggan
-- ==========================================
INSERT INTO pelanggan (nama_pelanggan, no_hp_pelanggan, alamat_pelanggan) VALUES 
('Andi Saputra', '081122334455', 'Jl. Kebagusan Raya No. 12'),
('Bella Puspita', '081199887766', 'Jl. Cilandak KKO No. 45'),
('Cahyo Utomo', '081212121212', 'Jl. Margonda Raya No. 88'),
('Dimas Anggara', '081777888999', 'Jl. Raya Bogor KM 30'),
('Endah Pertiwi', '081777888000', 'Jl. Juanda No. 5, Depok'),
('Fahri Albar', '081233344455', 'Jl. Sudirman No. 10'),
('Gita Savitri', '081255566677', 'Jl. Thamrin No. 25'),
('Hendra Setiawan', '081299900011', 'Jl. Gatot Subroto No. 99');

-- ==========================================
-- 3. Seed Data Jenis Item (Layanan)
-- ==========================================
INSERT INTO jenis_item (nama_item, harga_per_kg) VALUES 
('Cuci Komplit (Cuci+Gosok)', 6000),
('Cuci Kering (Lipat)', 4000),
('Setrika Saja', 3500),
('Bed Cover (Satuan)', 15000), -- Asumsi dihitung per item tapi input berat dianggap 1kg atau sesuai logika program nanti
('Cuci Express (3 Jam)', 10000),
('Karpet (Per Meter)', 20000),
('Boneka Besar', 25000);

-- ==========================================
-- 4. Seed Data Pesanan (Header)
-- ==========================================
-- Pesanan 1: Andi Saputra, dilayani Budi (Sudah Selesai)
INSERT INTO pesanan (id_pelanggan, id_pegawai, tgl_diterima, tgl_selesai, total_kg, total_biaya, status) VALUES 
(1, 1, '2023-10-01 08:00:00', '2023-10-03 10:00:00', 5.0, 30000, 'SELESAI');

-- Pesanan 2: Bella Puspita, dilayani Siti (Masih In Progress)
INSERT INTO pesanan (id_pelanggan, id_pegawai, tgl_diterima, tgl_selesai, total_kg, total_biaya, status) VALUES 
(2, 2, CURRENT_TIMESTAMP, NULL, 3.5, 14000, 'IN PROGRESS'),
(4, 3, '2023-10-05 09:00:00', '2023-10-06 14:00:00', 10.0, 60000, 'SELESAI'), -- Dimas Anggara, Cuci Komplit
(5, 1, '2023-10-07 10:30:00', NULL, 2.0, 30000, 'IN PROGRESS'), -- Endah Pertiwi, Bed Cover 2 biji
(6, 4, '2023-10-08 11:00:00', '2023-10-08 15:00:00', 4.0, 40000, 'SELESAI'), -- Fahri Albar, Express
(7, 5, '2023-10-09 08:00:00', NULL, 5.0, 20000, 'IN PROGRESS'), -- Gita Savitri, Cuci Kering
(8, 6, '2023-10-10 13:00:00', NULL, 1.0, 25000, 'IN PROGRESS'); -- Hendra Setiawan, Boneka

-- ==========================================
-- 5. Seed Data Detail Pesanan
-- ==========================================
-- Detail untuk Pesanan 1 (Andi Saputra - Cuci Komplit 5kg)
-- Asumsi ID Pesanan 1, ID Jenis Item 1 (Cuci Komplit 6000/kg)
INSERT INTO detail_pesanan (id_pesanan, id_jenis_item, berat_item, total_harga_item) VALUES 
(1, 1, 5.0, 30000);

-- Detail untuk Pesanan 2 (Bella Puspita - Cuci Kering 3.5kg)
-- Asumsi ID Pesanan 2, ID Jenis Item 2 (Cuci Kering 4000/kg)
INSERT INTO detail_pesanan (id_pesanan, id_jenis_item, berat_item, total_harga_item) VALUES 
(2, 2, 3.5, 14000),
(3, 1, 10.0, 60000), -- Pesanan 3 (Dimas): Cuci Komplit 10kg
(4, 4, 2.0, 30000),  -- Pesanan 4 (Endah): Bed Cover (Satuan) 2 pcs (2x15000)
(5, 5, 4.0, 40000),  -- Pesanan 5 (Fahri): Express 4kg (4x10000)
(6, 2, 5.0, 20000),  -- Pesanan 6 (Gita): Cuci Kering 5kg (5x4000)
(7, 7, 1.0, 25000);  -- Pesanan 7 (Hendra): Boneka Besar 1 pcs (1x25000)