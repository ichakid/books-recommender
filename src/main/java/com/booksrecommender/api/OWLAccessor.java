/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booksrecommender.api;

import java.io.File;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.manchestersyntax.renderer.ParserException;
import org.semanticweb.owlapi.model.*;
import static org.semanticweb.owlapi.model.IRI.create;
/**
 *
 * @author Choirunnisa Fatima
 */
public class OWLAccessor {
    private String pathOWL;
    private OWLOntologyManager manager;
    private OWLOntology ontology;
    private OWLDataFactory df;
    
    public OWLAccessor() {
        try {
            pathOWL = "buku.owl";
            manager = OWLManager.createOWLOntologyManager();
            df = manager.getOWLDataFactory();
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
    
    public void setObjectPropertyAssertion() throws OWLOntologyStorageException {
        /*System.out.println(ontology.getAnnotationAssertionAxioms());*/
        /*IRI example_iri = manager.getOntologyDocumentIRI(ontology);*/
        String example_iri = "http://www.semanticweb.org/windows/ontologies/2015/10/untitled-ontology-7";
        OWLIndividual matthew = df.getOWLNamedIndividual(IRI.create(example_iri + "#Jacq"));
        OWLIndividual peter = df.getOWLNamedIndividual(IRI.create(example_iri + "#Danur"));
        // We need the hasFather property
        OWLObjectProperty hasFather = df.getOWLObjectProperty(IRI.create(example_iri + "#menulis"));
        // matthew -> hasFather -> peter
        OWLObjectPropertyAssertionAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(hasFather, matthew, peter);
        // Finally, add the axiom to outsahr ontology
        AddAxiom addAxiomChange = new AddAxiom(ontology, assertion);
        manager.applyChange(addAxiomChange);
        manager.saveOntology(ontology, IRI.create(new File(pathOWL)));
    }
    
//    public void findByTitle(String title) {
//        OWLClass cls = factory.getOWLClass(":Buku", pm); cls.get
//        for (OWLIndividual indiv : cls.getIndividuals(ontology)) {
//            System.out.println(indiv.asOWLNamedIndividual().getIRI().getFragment());
//        }
//    }
}
