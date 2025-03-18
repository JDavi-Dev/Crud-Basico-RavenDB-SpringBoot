package com.example.demo;

import java.util.List;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository{

    private static final IDocumentStore store;

    static {
        store = new DocumentStore("http://192.168.123.3:8080", "TesteSpring");
        store.initialize();
    }

    public void save(Cliente cliente) {
        try (IDocumentSession session = store.openSession()) {
            session.store(cliente);
            session.saveChanges();
        }
    }

    public Cliente findById(String id) {
        try (IDocumentSession session = store.openSession()) {
            return session.load(Cliente.class, id);
        }
    }
    
    public List<Cliente> findAll() {
        try (IDocumentSession session = store.openSession()) {
            return session.query(Cliente.class).toList();
        }
    }
    
    public void deleteById(String id){
        try (IDocumentSession session = store.openSession()){
            Cliente cliente = session.load(Cliente.class, id);
            if (cliente != null){
                session.delete(cliente);
                session.saveChanges();
            }
        }
    }
}
