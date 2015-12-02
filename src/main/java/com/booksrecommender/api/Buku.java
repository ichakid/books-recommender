/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booksrecommender.api;

/**
 *
 * @author Arina Listyarini DA
 */
public class Buku {
    private String name;
    private String judul;
    private int jumlahHalaman;
    private String penulis;
    
    public Buku(String name, String judul, int jumlahHalaman, String penulis) {
        this.name = name;
        this.judul = judul;
        this.jumlahHalaman = jumlahHalaman;
        this.penulis = penulis;
    }
    
    public String getName() {
        return name;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    
    public Buku() {
    }
}
