/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Faruq
 */

public class Pelanggan {
    private int id_pelanggan;
    private String nama_pelanggan;
    private String no_hp_pelanggan;
    private String alamat_pelanggan;

    public Pelanggan() {}

    public Pelanggan(int id, String nama, String hp, String alamat) {
        this.id_pelanggan = id;
        this.nama_pelanggan = nama;
        this.no_hp_pelanggan = hp;
        this.alamat_pelanggan = alamat;
    }

    public Pelanggan(String nama, String hp, String alamat) {
        this.nama_pelanggan = nama;
        this.no_hp_pelanggan = hp;
        this.alamat_pelanggan = alamat;
    }
    // Getter Setter
    public int getId_pelanggan() { return id_pelanggan; }
    public void setId_pelanggan(int id_pelanggan) { this.id_pelanggan = id_pelanggan; }

    public String getNama_pelanggan() { return nama_pelanggan; }
    public void setNama_pelanggan(String nama_pelanggan) { this.nama_pelanggan = nama_pelanggan; }

    public String getNo_hp_pelanggan() { return no_hp_pelanggan; }
    public void setNo_hp_pelanggan(String no_hp_pelanggan) { this.no_hp_pelanggan = no_hp_pelanggan; }

    public String getAlamat_pelanggan() { return alamat_pelanggan; }
    public void setAlamat_pelanggan(String alamat_pelanggan) { this.alamat_pelanggan = alamat_pelanggan; }

    @Override
    public String toString() {
        return nama_pelanggan; // Agar di ComboBox nanti muncul namanya
    }
}