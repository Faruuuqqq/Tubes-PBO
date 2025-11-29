/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Faruq
 */
public class JenisItem {
    private int id_jenis_item;
    private String nama_item;
    private double harga_per_kg;

    public JenisItem() {}

    public JenisItem(int id, String nama, double harga) {
        this.id_jenis_item = id;
        this.nama_item = nama;
        this.harga_per_kg = harga;
    }

    // Getter Setter
    public int getId_jenis_item() { return id_jenis_item; }
    public void setId_jenis_item(int id_jenis_item) { this.id_jenis_item = id_jenis_item; }

    public String getNama_item() { return nama_item; }
    public void setNama_item(String nama_item) { this.nama_item = nama_item; }

    public double getHarga_per_kg() { return harga_per_kg; }
    public void setHarga_per_kg(double harga_per_kg) { this.harga_per_kg = harga_per_kg; }

    @Override
    public String toString() {
        return nama_item + " (Rp " + harga_per_kg + "/kg)";
    }
}
