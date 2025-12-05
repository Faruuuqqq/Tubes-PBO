/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Faruq
 */

public class DetailPesanan {
    private int id_detail;
    private int id_pesanan;
    private int id_jenis_item;
    private double berat_item;
    private double total_harga_item;

    public DetailPesanan() {}

    // Getter Setter
    public int getId_detail() { return id_detail; }
    public void setId_detail(int id_detail) { this.id_detail = id_detail; }

    public int getId_pesanan() { return id_pesanan; }
    public void setId_pesanan(int id_pesanan) { this.id_pesanan = id_pesanan; }

    public int getId_jenis_item() { return id_jenis_item; }
    public void setId_jenis_item(int id_jenis_item) { this.id_jenis_item = id_jenis_item; }

    public double getBerat_item() { return berat_item; }
    public void setBerat_item(double berat_item) { this.berat_item = berat_item; }

    public double getTotal_harga_item() { return total_harga_item; }
    public void setTotal_harga_item(double total_harga_item) { this.total_harga_item = total_harga_item; }
}
