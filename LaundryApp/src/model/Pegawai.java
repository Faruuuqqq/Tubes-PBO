/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Faruq
 */

import java.sql.Date; 

public class Pegawai {
    private int id_pegawai;
    private String nama_pegawai;
    private int usia_pegawai;
    private String jk_pegawai; // 'L' atau 'P'
    private String no_hp_pegawai;
    private String alamat_pegawai;
    private Date start_date;

    // Constructor Kosong
    public Pegawai() {}

    // Constructor isi
    public Pegawai(int id, String nama, int usia, String jk, String hp, String alamat, Date tgl) {
        this.id_pegawai = id;
        this.nama_pegawai = nama;
        this.usia_pegawai = usia;
        this.jk_pegawai = jk;
        this.no_hp_pegawai = hp;
        this.alamat_pegawai = alamat;
        this.start_date = tgl;
    }

    public int getId_pegawai() { return id_pegawai; }
    public void setId_pegawai(int id_pegawai) { this.id_pegawai = id_pegawai; }

    public String getNama_pegawai() { return nama_pegawai; }
    public void setNama_pegawai(String nama_pegawai) { this.nama_pegawai = nama_pegawai; }

    public int getUsia_pegawai() { return usia_pegawai; }
    public void setUsia_pegawai(int usia_pegawai) { this.usia_pegawai = usia_pegawai; }

    public String getJk_pegawai() { return jk_pegawai; }
    public void setJk_pegawai(String jk_pegawai) { this.jk_pegawai = jk_pegawai; }

    public String getNo_hp_pegawai() { return no_hp_pegawai; }
    public void setNo_hp_pegawai(String no_hp_pegawai) { this.no_hp_pegawai = no_hp_pegawai; }

    public String getAlamat_pegawai() { return alamat_pegawai; }
    public void setAlamat_pegawai(String alamat_pegawai) { this.alamat_pegawai = alamat_pegawai; }

    public Date getStart_date() { return start_date; }
    public void setStart_date(Date start_date) { this.start_date = start_date; }
    
    // Override toString agar saat ditampilkan di ComboBox muncul Namanya, bukan alamat memori
    @Override
    public String toString() {
        return nama_pegawai;
    }
}
