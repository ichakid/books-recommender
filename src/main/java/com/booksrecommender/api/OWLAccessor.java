/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booksrecommender.api;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
/**
 *
 * @author Choirunnisa Fatima
 */
public class OWLAccessor {
    private String pathOWL;
    private OWLOntologyManager manager;
    private OWLOntology ontology;
    
    public OWLAccessor() {
        try {
            pathOWL = "buku.owl";
            manager = OWLManager.createOWLOntologyManager();
            ontology = manager.loadOntologyFromOntologyDocument(new File(pathOWL));
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(OWLAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showClasses() {
         for (OWLClass cls : ontology.getClassesInSignature()) {
            System.out.println(cls.toString());
        }
    }
    
//    public void findByTitle(String title) {
//        OWLClass cls = factory.getOWLClass(":Buku", pm); cls.get
//        for (OWLIndividual indiv : cls.getIndividuals(ontology)) {
//            System.out.println(indiv.asOWLNamedIndividual().getIRI().getFragment());
//        }
//    }
    
    public static void main(String[] args) {
        OWLAccessor bookOntology = new OWLAccessor();
        bookOntology.showClasses();
    }
}
