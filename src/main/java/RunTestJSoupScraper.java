import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import exception.InvalidParameterException;
import utils.JSoupScraper;
/**
 * Classe per il test di JSoupScraper
 * @author Cristian Zanna
 *
 */
public class RunTestJSoupScraper {
	public static void main(String[] args) {
		try {
			String urlValidata = "https://it.wikipedia.org/wiki/Pagina_principale";
			JSoupScraper scraper = new JSoupScraper(urlValidata);
			LinkedList<String> links = scraper.getLinks();
			HashMap<String, String> images = scraper.getImages();
			
			//scraper.rescrape();
			
			//links.forEach(System.out::println);
			
			//images.forEach((key, value) -> System.out.println("Nome: " + key + " Url: " + value));
			
			for(String link : links) {
				System.out.println(link);
			}
			
			for (Map.Entry<String, String> entry : images.entrySet()) {
				System.out.println("Nome: " + entry.getKey() + " Url: " + entry.getValue());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidParameterException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
	}
}
