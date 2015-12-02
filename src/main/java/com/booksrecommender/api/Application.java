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
    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println("Rekomendasi Buku");
        System.out.println("===========================");
        System.out.println("Masukkan judul buku:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        List<Buku> rekomendasiBuku = new ArrayList<>();
        rekomendasiBuku.add(new Buku("Twilight",200,"Stephenie Meyer"));
        rekomendasiBuku.add(new Buku("Bubu Caca Bubabu",250,"Caca Marica"));
        rekomendasiBuku.add(new Buku("Hai Miiko",150,"Ono Eriko"));
        
        System.out.println("---");
        System.out.println("Buku yang anda masukkan adalah " + input);
        System.out.println("Buku-buku rekomendasi untuk dibaca selanjutnya yaitu:");
        for(int i = 0; i < rekomendasiBuku.size(); i++) {
            System.out.println(i+1 + ". " + rekomendasiBuku.get(i).getJudul() + " oleh " + rekomendasiBuku.get(i).getPenulis() + " || " + rekomendasiBuku.get(i).getJumlahHalaman() + " halaman");
        }
        
        System.out.println("===========================");
        
        OWLAccessor bookOntology = new OWLAccessor();
        try {
            bookOntology.saveBook("Dammie", "Doraemon");
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        bookOntology.showClasses();
    }    
}
