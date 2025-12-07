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
import model.JenisItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JenisItemController {
    
    private final Connection conn;

    public JenisItemController() {
        this.conn = Koneksi.getConnection();
    }

    // 1. READ
    public List<JenisItem> getAllJenisItem() {
        List<JenisItem> listJenis = new ArrayList<>();
        String sql = "SELECT * FROM jenis_item";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                JenisItem item = new JenisItem();
                item.setId_jenis_item(rs.getInt("id_jenis_item"));
                item.setNama_item(rs.getString("nama_item"));
                item.setHarga_per_kg(rs.getDouble("harga_per_kg"));
                
                listJenis.add(item);
            }
        } catch (SQLException e) {
            Logger.getLogger(JenisItemController.class.getName()).log(Level.SEVERE, null, e);
        }
        return listJenis;
    }

    // 2. CREATE
    public boolean addJenisItem(JenisItem item) {
        String sql = "INSERT INTO jenis_item (nama_item, harga_per_kg) VALUES (?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getNama_item());
            ps.setDouble(2, item.getHarga_per_kg());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(JenisItemController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // 3. UPDATE
    public boolean updateJenisItem(JenisItem item) {
        String sql = "UPDATE jenis_item SET nama_item=?, harga_per_kg=? WHERE id_jenis_item=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getNama_item());
            ps.setDouble(2, item.getHarga_per_kg());
            ps.setInt(3, item.getId_jenis_item());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(JenisItemController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // 4. DELETE
    public boolean deleteJenisItem(int id) {
        String sql = "DELETE FROM jenis_item WHERE id_jenis_item=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(JenisItemController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<JenisItem> searchJenisItem(String keyword) {
        List<JenisItem> listJenis = new ArrayList<>();
        String sql = "SELECT * FROM jenis_item WHERE nama_item LIKE ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                JenisItem item = new JenisItem();
                item.setId_jenis_item(rs.getInt("id_jenis_item"));
                item.setNama_item(rs.getString("nama_item"));
                item.setHarga_per_kg(rs.getDouble("harga_per_kg"));
                
                listJenis.add(item);
            }
        } catch (SQLException e) {
            Logger.getLogger(JenisItemController.class.getName()).log(Level.SEVERE, null, e);
        }
        return listJenis;
    }
}
