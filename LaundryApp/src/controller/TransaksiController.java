/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Faruq
 */

import config.Koneksi;
import model.DetailPesanan;
import model.Pesanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransaksiController {
    
    private final Connection conn;

    public TransaksiController() {
        this.conn = Koneksi.getConnection();
    }

    // 1. CREATE TRANSAKSI (Menyimpan Pesanan + Detail Item sekaligus)
    public boolean simpanTransaksi(Pesanan pesanan, List<DetailPesanan> listDetail) {
        try {
            conn.setAutoCommit(false);

            // A. Simpan Data ke Tabel PESANAN (Header)
            String sqlPesanan = "INSERT INTO pesanan (id_pelanggan, id_pegawai, tgl_diterima, tgl_selesai, total_kg, total_biaya, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            // butuh ID Pesanan yang baru saja dibuat untuk detailnya
            PreparedStatement psPesanan = conn.prepareStatement(sqlPesanan, Statement.RETURN_GENERATED_KEYS);
            psPesanan.setInt(1, pesanan.getId_pelanggan());
            psPesanan.setInt(2, pesanan.getId_pegawai());
            psPesanan.setTimestamp(3, pesanan.getTgl_diterima());
            psPesanan.setTimestamp(4, pesanan.getTgl_selesai());
            psPesanan.setDouble(5, pesanan.getTotal_kg());
            psPesanan.setDouble(6, pesanan.getTotal_biaya());
            psPesanan.setString(7, pesanan.getStatus());
            
            psPesanan.executeUpdate();

            // Ambil ID Pesanan yang baru digenerate
            ResultSet rs = psPesanan.getGeneratedKeys();
            int idPesananBaru = 0;
            if (rs.next()) {
                idPesananBaru = rs.getInt(1);
            }

            // B. Simpan Data ke Tabel DETAIL_PESANAN (Looping sebanyak item)
            String sqlDetail = "INSERT INTO detail_pesanan (id_pesanan, id_jenis_item, berat_item, total_harga_item) VALUES (?, ?, ?, ?)";
            PreparedStatement psDetail = conn.prepareStatement(sqlDetail);

            for (DetailPesanan detail : listDetail) {
                psDetail.setInt(1, idPesananBaru); // Pakai ID dari header di atas
                psDetail.setInt(2, detail.getId_jenis_item());
                psDetail.setDouble(3, detail.getBerat_item());
                psDetail.setDouble(4, detail.getTotal_harga_item());
                psDetail.addBatch(); // Kumpulkan dulu query-nya
            }

            psDetail.executeBatch(); // Jalankan semua insert detail sekaligus

            conn.commit(); // Kalau lancar semua, baru simpan permanen
            conn.setAutoCommit(true); // Kembalikan ke mode normal
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Transaksi Dibatalkan: " + e.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    // 2. READ (Untuk Laporan Riwayat Transaksi)
    public List<Pesanan> getAllPesanan() {
        List<Pesanan> list = new ArrayList<>();
        String sql = "SELECT * FROM pesanan ORDER BY id_pesanan DESC";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Pesanan p = new Pesanan();
                p.setId_pesanan(rs.getInt("id_pesanan"));
                p.setId_pelanggan(rs.getInt("id_pelanggan"));
                p.setId_pegawai(rs.getInt("id_pegawai"));
                p.setTgl_diterima(rs.getTimestamp("tgl_diterima"));
                p.setTgl_selesai(rs.getTimestamp("tgl_selesai"));
                p.setTotal_kg(rs.getDouble("total_kg"));
                p.setTotal_biaya(rs.getDouble("total_biaya"));
                p.setStatus(rs.getString("status"));
                
                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    // 3. UPDATE STATUS (Misal: Ubah dari IN PROGRESS ke SELESAI)
    public boolean updateStatusPesanan(int idPesanan, String statusBaru) {
        String sql = "UPDATE pesanan SET status = ? WHERE id_pesanan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, statusBaru);
            ps.setInt(2, idPesanan);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}