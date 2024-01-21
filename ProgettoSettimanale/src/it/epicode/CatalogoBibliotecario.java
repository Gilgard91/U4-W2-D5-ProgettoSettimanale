package it.epicode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogoBibliotecario {

    private List<Elemento> archivio = new ArrayList<>();

    public void aggiungiElemento(Elemento elemento) {
        archivio.add(elemento);
    }

    public void rimuoviElemento(String isbn) {
        archivio.removeIf(elemento -> elemento.getIsbn().equals(isbn));
    }

    public Optional<Elemento> ricercaPerISBN(String isbn) {
        return archivio.stream()
                .filter(elemento -> elemento.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Elemento> ricercaPerAnnoPubblicazione(int anno) {
        return archivio.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .toList();
    }

    public List<Elemento> ricercaPerAutore(String autore) {
        return archivio.stream()
                .filter(elemento -> elemento instanceof Libro)
                .map(elemento -> (Libro) elemento)
                .filter(libro -> libro.getAutore().equals(autore))
                .map(libro -> (Elemento) libro)
                .toList();
    }

    public void salvaSuDisco(String nomeFile) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            oos.writeObject(archivio);
        }
    }

    @SuppressWarnings("unchecked")
    public void caricaDaDisco(String nomeFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile))) {
            archivio = (List<Elemento>) ois.readObject();
        }
    }
}
