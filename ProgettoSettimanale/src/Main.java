import it.epicode.*;

import java.io.IOException;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        CatalogoBibliotecario catalogo = new CatalogoBibliotecario();


        Libro libro = new Libro("123456789", "Il Signore degli Anelli", 1954, 1000, "J.R.R. Tolkien", "Fantasy");
        Rivista rivista = new Rivista("987654321", "National Geographic", 2022, 50, Periodicita.MENSILE);

        catalogo.aggiungiElemento(libro);
        catalogo.aggiungiElemento(rivista);


        try {
            catalogo.salvaSuDisco("archivio.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            catalogo.caricaDaDisco("archivio.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        Optional<Elemento> risultatoRicerca = catalogo.ricercaPerISBN("123456789");
        risultatoRicerca.ifPresent(elemento -> System.out.println("Risultato ricerca: " + elemento.getTitolo()));
    }
}