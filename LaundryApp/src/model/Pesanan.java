/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Faruq
 */
import java.sql.Timestamp; // Kita pakai Timestamp agar jam juga tersimpan, bukan cuma tanggal

public class Pesanan {
    private int id_pesanan;
    private int id_pelanggan;
    private int id_pegawai;
    private Timestamp tgl_diterima;
    private Timestamp tgl_selesai;
    private double total_kg;
    private double total_biaya;
    private String status; // "IN PROGRESS" atau "SELESAI"

    public Pesanan() {}

    // Getter Setter
    public int getId_pesanan() { return id_pesanan; }
    public void setId_pesanan(int id_pesanan) { this.id_pesanan = id_pesanan; }

    public int getId_pelanggan() { return id_pelanggan; }
    public void setId_pelanggan(int id_pelanggan) { this.id_pelanggan = id_pelanggan; }

    public int getId_pegawai() { return id_pegawai; }
    public void setId_pegawai(int id_pegawai) { this.id_pegawai = id_pegawai; }

    public Timestamp getTgl_diterima() { return tgl_diterima; }
    public void setTgl_diterima(Timestamp tgl_diterima) { this.tgl_diterima = tgl_diterima; }

    public Timestamp getTgl_selesai() { return tgl_selesai; }
    public void setTgl_selesai(Timestamp tgl_selesai) { this.tgl_selesai = tgl_selesai; }

    public double getTotal_kg() { return total_kg; }
    public void setTotal_kg(double total_kg) { this.total_kg = total_kg; }

    public double getTotal_biaya() { return total_biaya; }
    public void setTotal_biaya(double total_biaya) { this.total_biaya = total_biaya; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
