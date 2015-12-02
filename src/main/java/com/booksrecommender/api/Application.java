/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booksrecommender.api;
import java.util.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author Arina Listyarini DA
 */
public class Application {
    public static void main(String[] args) throws OWLOntologyStorageException {
        OWLAccessor bookOntology = new OWLAccessor();
        String username = "Dammie";
        
        System.out.println("===========================");
        System.out.println("Rekomendasi Buku");
        System.out.println("===========================");
        System.out.println("Masukkan judul buku:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        System.out.println("---");
        System.out.println("Hasil pencarian:");
        List<Buku> hasilBuku = bookOntology.findBook(input);
        int i=1;
        for (Buku buku: hasilBuku) {
            System.out.println(i + ". " + buku.getJudul() + " oleh " + buku.getPenulis() + " || " + buku.getJumlahHalaman() + " halaman");
            i++;
        }
        
        System.out.println("---");
        System.out.println("Buku yang anda sudah baca adalah: "); input = scan.nextLine();
        bookOntology.saveBook(username, hasilBuku.get(Integer.parseInt(input)-1).getName());
        
        System.out.println("---");
        List<Buku> rekomendasiBuku = bookOntology.recommend(username);
        System.out.println("Buku-buku rekomendasi untuk dibaca selanjutnya yaitu:");
        i=1;
        for (Buku buku: rekomendasiBuku) {
            System.out.println(i + ". " + buku.getJudul() + " oleh " + buku.getPenulis() + " || " + buku.getJumlahHalaman() + " halaman");
            i++;
        }
    }    
}
