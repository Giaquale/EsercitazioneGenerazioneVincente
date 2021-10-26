import org.apache.commons.validator.UrlValidator;

import exception.InvalidUrlException;
import java.util.HashMap;
import java.util.Scanner;

public class main {

    public static <T> void main(String[] args) throws InvalidUrlException {
        HashMap<String, T> dictionary = new HashMap<String, T>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ciao, inserisci una URL valida:");
        String url = sc.nextLine();

        // 1) Check URL
        UrlValidator urlValidator = new UrlValidator();
        
        try {
			if(urlValidator.isValid(url)) {
			return;	
			}
		} catch (Exception e) {
			if(!urlValidator.isValid(url)) {
			e.printStackTrace();
			throw new InvalidUrlException("Inserisci un URL valida!");
			}
		}
        
        

    
    
    
    
    }
}