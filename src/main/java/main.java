import org.apache.commons.validator.UrlValidator;

import java.util.HashMap;

public class main {
    public static void main(String[] args) {
//        HashMap<String, T> dictionary = new HashMap<String, T>();
        System.out.println("Ciao, inserisci una URL valida:");

        // 1) Check URL
        UrlValidator urlValidator = new UrlValidator();
        urlValidator.isValid("http://my favorite site!");

        // 2) Avvio ricerca link con inserimento db alla fine

        // 3) Aggiornamento mappa link con inserimento db alla fine

        // 4) Avvio Ricerca mappe con inserimento db alla fine

        // 5)bho
    }
}