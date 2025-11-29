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
import model.Pegawai;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PegawaiController {
    
    private final Connection conn;

    public PegawaiController() {
        // Mengambil koneksi dari class config yang sudah kita buat sebelumnya
        this.conn = Koneksi.getConnection();
    }

    // 1. READ (Mengambil semua data pegawai dari database)
    public List<Pegawai> getAllPegawai() {
        List<Pegawai> listPegawai = new ArrayList<>();
        String sql = "SELECT * FROM pegawai";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Pegawai p = new Pegawai();
                p.setId_pegawai(rs.getInt("id_pegawai"));
                p.setNama_pegawai(rs.getString("nama_pegawai"));
                p.setUsia_pegawai(rs.getInt("usia_pegawai"));
                p.setJk_pegawai(rs.getString("jk_pegawai"));
                p.setNo_hp_pegawai(rs.getString("no_hp_pegawai"));
                p.setAlamat_pegawai(rs.getString("alamat_pegawai"));
                p.setStart_date(rs.getDate("start_date"));
                
                listPegawai.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
        }
        return listPegawai;
    }

    // 2. CREATE (Menambahkan pegawai baru)
    public boolean addPegawai(Pegawai p) {
        // Query insert sesuai kolom tabel pegawai
        String sql = "INSERT INTO pegawai (nama_pegawai, usia_pegawai, jk_pegawai, no_hp_pegawai, alamat_pegawai, start_date) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNama_pegawai());
            ps.setInt(2, p.getUsia_pegawai());
            ps.setString(3, p.getJk_pegawai());
            ps.setString(4, p.getNo_hp_pegawai());
            ps.setString(5, p.getAlamat_pegawai());
            ps.setDate(6, p.getStart_date());
            
            int rowInserted = ps.executeUpdate();
            return rowInserted > 0; // Return true jika berhasil
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // 3. UPDATE (Mengedit data pegawai)
    public boolean updatePegawai(Pegawai p) {
        String sql = "UPDATE pegawai SET nama_pegawai=?, usia_pegawai=?, jk_pegawai=?, no_hp_pegawai=?, alamat_pegawai=?, start_date=? WHERE id_pegawai=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNama_pegawai());
            ps.setInt(2, p.getUsia_pegawai());
            ps.setString(3, p.getJk_pegawai());
            ps.setString(4, p.getNo_hp_pegawai());
            ps.setString(5, p.getAlamat_pegawai());
            ps.setDate(6, p.getStart_date());
            ps.setInt(7, p.getId_pegawai()); // Where clause
            
            int rowUpdated = ps.executeUpdate();
            return rowUpdated > 0;
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // 4. DELETE (Menghapus pegawai)
    public boolean deletePegawai(int id) {
        String sql = "DELETE FROM pegawai WHERE id_pegawai=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            int rowDeleted = ps.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}