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
import model.Pelanggan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PelangganController {
    
    private final Connection conn;

    public PelangganController() {
        this.conn = Koneksi.getConnection();
    }

    // 1. READ (Ambil semua data pelanggan)
    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setId_pelanggan(rs.getInt("id_pelanggan"));
                p.setNama_pelanggan(rs.getString("nama_pelanggan"));
                p.setNo_hp_pelanggan(rs.getString("no_hp_pelanggan"));
                p.setAlamat_pelanggan(rs.getString("alamat_pelanggan"));
                
                listPelanggan.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error dalam mengambil data semua pelanggan: " + e.getMessage());
        }
        return listPelanggan;
    }

    // 2. CREATE (Tambah pelanggan)
    public boolean addPelanggan(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, no_hp_pelanggan, alamat_pelanggan) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNama_pelanggan());
            ps.setString(2, p.getNo_hp_pelanggan());
            ps.setString(3, p.getAlamat_pelanggan());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error menambahkan data pelanggan baru: " + e.getMessage());
            return false;
        }
    }

    // 3. UPDATE (Edit pelanggan)
    public boolean updatePelanggan(Pelanggan p) {
        String sql = "UPDATE pelanggan SET nama_pelanggan=?, no_hp_pelanggan=?, alamat_pelanggan=? WHERE id_pelanggan=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNama_pelanggan());
            ps.setString(2, p.getNo_hp_pelanggan());
            ps.setString(3, p.getAlamat_pelanggan());
            ps.setInt(4, p.getId_pelanggan());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error meng-update data pelanggan: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE (Hapus pelanggan)
    public boolean deletePelanggan(int id) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Gagal Hapus: Pelanggan ini memiliki riwayat transaksi!");
            return false;
        } catch (SQLException e) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // Method Cari Pelanggan berdasarkan Nama atau No HP
    public List<Pelanggan> searchPelanggan(String keyword) {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE ? OR no_hp_pelanggan LIKE ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setId_pelanggan(rs.getInt("id_pelanggan"));
                p.setNama_pelanggan(rs.getString("nama_pelanggan"));
                p.setNo_hp_pelanggan(rs.getString("no_hp_pelanggan"));
                p.setAlamat_pelanggan(rs.getString("alamat_pelanggan"));
                
                listPelanggan.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, e);
        }
        return listPelanggan;
    }
}
