import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import utils.JSoupScraper;

public class RunTestJSoupScraper {
	public static void main(String[] args) {
		try {
			JSoupScraper scraper = new JSoupScraper("https://it.wikipedia.org/wiki/Pagina_principale");
			LinkedList<String> links = scraper.getLinks();
			HashMap<String, String> images = scraper.getImages();
			
			links.forEach(System.out::println);
			
			images.forEach((key, value) -> System.out.println("Nome: " + key + " Url: " + value));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
