/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booksrecommender.api;

import example.DLQueryEngine;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
/**
 *
 * @author Choirunnisa Fatima
 */
public class OWLAccessor {
    private String pathOWL;
    private OWLOntologyManager manager;
    private OWLOntology ontology;
    private OWLDataFactory df;
    private OWLReasoner reasoner;
    private String example_iri = "http://www.semanticweb.org/windows/ontologies/2015/10/untitled-ontology-7";
    
    public OWLAccessor() {
        try {
            pathOWL = "buku.owl";
            manager = OWLManager.createOWLOntologyManager();
            df = manager.getOWLDataFactory();
            ontology = manager.loadOntologyFromOntologyDocument(new File(pathOWL));
            
            OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
            reasoner = reasonerFactory.createReasoner(ontology);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(OWLAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Buku> findBook (String title) {
        String query = "Buku and (judul value \""+ title +"\")";
        return ask(query);
    }
    
    public void saveBook(String username, String name) throws OWLOntologyStorageException {
        OWLIndividual user = df.getOWLNamedIndividual(IRI.create(example_iri + "#" + username));
        OWLIndividual bookOwl = df.getOWLNamedIndividual(IRI.create(example_iri + "#" + name));
        OWLObjectProperty hasFather = df.getOWLObjectProperty(IRI.create(example_iri + "#hasRead"));
        OWLObjectPropertyAssertionAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(hasFather, user, bookOwl);
        AddAxiom addAxiomChange = new AddAxiom(ontology, assertion);
        manager.applyChange(addAxiomChange);
        manager.saveOntology(ontology, IRI.create(new File(pathOWL)));
    }
    
    public List<Buku> recommend(String username){
        String query = "Buku and hasTag min 1 (Tag and inverse hasTag some (Buku and inverse hasRead some (User and (username value \""+ username + "\"))))";
        return ask(query);
    }
    
    private List<Buku> ask(String query){
        ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
        DLQueryEngine dlQueryEngine = new DLQueryEngine(reasoner, shortFormProvider);
        Set<OWLNamedIndividual> individuals = dlQueryEngine.getInstances(query.trim(), true);
        List<Buku> books = new ArrayList();
        for (OWLNamedIndividual individual: individuals) {
            String name = shortFormProvider.getShortForm(individual);
            OWLDataProperty propJudul = df.getOWLDataProperty(IRI.create(example_iri + "#judul"));
            Set<OWLLiteral> juduls = reasoner.getDataPropertyValues(individual, propJudul);
            String judul = "";
            for (OWLLiteral j: juduls){
                judul = j.getLiteral();
            }
            
            OWLDataProperty propHal = df.getOWLDataProperty(IRI.create(example_iri + "#jumlahHalaman"));
            Set<OWLLiteral> hals = reasoner.getDataPropertyValues(individual, propHal);
            String jumlahHalaman = "0";
            for (OWLLiteral h: hals){
                jumlahHalaman = h.getLiteral();
            }
            
            String penulis = "a";
            Set<OWLNamedIndividual> penuliss = dlQueryEngine.getInstances("Penulis and menulis some (Buku and (judul value \"" + judul +"\"))", true);
            for (OWLNamedIndividual i: penuliss) {
                penulis = shortFormProvider.getShortForm(i);
            }
            books.add(new Buku(name, judul, jumlahHalaman, penulis));
        }
        return books;
    }
}
