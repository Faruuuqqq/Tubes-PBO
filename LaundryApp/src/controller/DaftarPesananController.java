/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author andin
 */
import config.Koneksi;
import model.Pesanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaftarPesananController {
    
    private final Connection conn;

    public DaftarPesananController() {
        // Mengambil koneksi dari class config yang sudah kita buat sebelumnya
        this.conn = Koneksi.getConnection();
    }

    // 1. READ (Mengambil semua data pegawai dari database)
    public List<Pesanan> getAllPesanan() {
        List<Pesanan> listPesanan = new ArrayList<>();
        String sql = "SELECT * FROM pesanan";
        
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
                
                listPesanan.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error dalam mengambil data semua pesanan: " + e.getMessage());
        }
        return listPesanan;
    }

    // 2. CREATE (Menambahkan pegawai baru)
    public boolean addPesanan(Pesanan p) {
        // Query insert sesuai kolom tabel pegawai
        String sql = "INSERT INTO pesanan (id_pelanggan, id_pegawai, tgl_diterima, tgl_selesai, total_kg, total_biaya, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId_pelanggan());
            ps.setInt(2, p.getId_pegawai());
            ps.setTimestamp(3, p.getTgl_diterima());
            ps.setTimestamp(4, p.getTgl_selesai());
            ps.setDouble(5, p.getTotal_kg());
            ps.setDouble(6, p.getTotal_biaya());
            ps.setString(7, p.getStatus());
           
            
            int rowInserted = ps.executeUpdate();
            return rowInserted > 0; // Return true jika berhasil
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error menambahkan data pesanan baru: " + e.getMessage());
            return false;
        }
    }

    // 3. UPDATE (Mengedit data pegawai)
    public boolean updatePesanan(Pesanan p) {
        String sql = "UPDATE pesanan SET id_pelanggan=?, id_pegawai=?, tgl_diterima=?, tgl_selesai=?, total_kg=?, total_biaya=?, status=? WHERE id_pesanan=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId_pelanggan());
            ps.setInt(2, p.getId_pegawai());
            
            java.util.Date terima = p.getTgl_diterima();
            java.sql.Date sqlTerima = new java.sql.Date(terima.getTime());
            ps.setDate(3, sqlTerima);
            
            java.util.Date selesai = p.getTgl_selesai();
            java.sql.Date sqlSelesai = new java.sql.Date(selesai.getTime());
            ps.setDate(4, sqlSelesai);
            
            ps.setDouble(5, p.getTotal_kg());
            ps.setDouble(6, p.getTotal_biaya());
            ps.setString(7, p.getStatus());
            
            ps.setInt(8, p.getId_pesanan()); // Where clause
            
            int rowUpdated = ps.executeUpdate();
            return rowUpdated > 0;
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error meng-update data pesanan: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE (Menghapus pegawai)
    public boolean deletePesanan(int id) {
        String sql = "DELETE FROM pesanan WHERE id_pesanan=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            int rowDeleted = ps.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error dalam menghapus pesanan: " + e.getMessage());
            return false;
        }
    }

    // Method baru untuk mengambil data lengkap sekaligus
    public javax.swing.table.DefaultTableModel getDaftarPesananLengkap() {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"ID", "Pelanggan", "Pegawai", "Tgl Terima", "Tgl Selesai", "Total Kg", "Total Biaya", "Status"});

        String sql = "SELECT p.id_pesanan, pel.nama_pelanggan, peg.nama_pegawai, " +
                    "p.tgl_diterima, p.tgl_selesai, p.total_kg, p.total_biaya, p.status " +
                    "FROM pesanan p " +
                    "JOIN pelanggan pel ON p.id_pelanggan = pel.id_pelanggan " +
                    "JOIN pegawai peg ON p.id_pegawai = peg.id_pegawai " +
                    "ORDER BY p.id_pesanan DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id_pesanan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("nama_pegawai"),
                    rs.getTimestamp("tgl_diterima"),
                    rs.getTimestamp("tgl_selesai"),
                    rs.getDouble("total_kg"),
                    rs.getDouble("total_biaya"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    // Cek apakah ID Pesanan ada di database
    public boolean isPesananExists(int id) {
        String sql = "SELECT id_pesanan FROM pesanan WHERE id_pesanan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Return true jika data ditemukan
        } catch (SQLException e) {
            Logger.getLogger(DaftarPesananController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // Ambil detail item untuk ditampilkan di tabel PesananBaru
    public javax.swing.table.DefaultTableModel getDetailItemsModel(int idPesanan) {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        // Sesuaikan nama kolom dengan tabel di GUI PesananBaru kamu
        model.addColumn("Item");
        model.addColumn("Berat");
        model.addColumn("Total Harga");

        String sql = "SELECT j.nama_item, dp.berat_item, dp.total_harga_item " +
                     "FROM detail_pesanan dp " +
                     "JOIN jenis_item j ON dp.id_jenis_item = j.id_jenis_item " +
                     "WHERE dp.id_pesanan = ?";
                     
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPesanan);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("nama_item"),
                    rs.getDouble("berat_item"),
                    rs.getDouble("total_harga_item")
                });
            }
        } catch (SQLException e) {
             Logger.getLogger(DaftarPesananController.class.getName()).log(Level.SEVERE, null, e);
        }
        return model;
    }
}