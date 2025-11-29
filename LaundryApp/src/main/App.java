/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Faruq
 */

import view.MainFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Menjalankan aplikasi (Standard Thread Safety)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Membuat objek jendela utama
                MainFrame frame = new MainFrame();
                
                // Menampilkan jendela
                frame.setVisible(true);
            }
        });
    }
}
