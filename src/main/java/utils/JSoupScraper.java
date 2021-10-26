package utils;
/**
 * Classe adibita allo scraping degli URL e delle immagini (sottoformadi mappa Nome-URL <String, String>)
 * della pagina web passata come url di tipo String al costruttore.
 * Una volta istanziato l'oggetto fornendo l'URL al costruttore non sarà più possibile modifcare questo valore,
 * ma rimarrà possibile riscaricare il DOM ed eseguire un nuovo
 * scraping delle informazioni richieste
 * 
 * @author Cristian Zanna
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import exception.InvalidParameterException;

public class JSoupScraper {
	private final  String urlToScrap;
	private Document document;
	private final  LinkedList<String> links = new LinkedList<>();	//Provvisorio in attesa del model
	private final HashMap<String, String> images = new HashMap<>();	//Provvisorio in attesa del model
	
	/*
	 * Il costruttore riceve come parametro l'URL sottoforma di String
	 * ed effettua lo scraping di URL e mappa immagini
	 */
	public JSoupScraper(final String validateUrl) throws InvalidParameterException, IOException {
		if (validateUrl != null && !validateUrl.isBlank()) {
			//Salva nel campo l'url
			this.urlToScrap = validateUrl;
			//Preleva il DOM e lo salvo nel campo
			this.document = Jsoup.connect(urlToScrap).get();
			//Effettua lo scraping dei link
			scrapLinks();
			scrapImages();
		}
		else {
			throw new InvalidParameterException("NULL or Empty URL");
		}
	}
	
	/*
	 * Ritorna la lista link sottoforma di LinkedList
	 */
	public LinkedList<String> getLinks(){		
		return links;
	}
	
	/*
	 * Ritorna la mappa di immagini usando il nome dell'immagine come chiave e l'URL come valore
	 */
	public HashMap<String, String> getImages(){
		return images;
	}
	
	/**
	 * Esegue nuovamente lo scraping delle informazioni dalla pagina web
	 */
	public void rescrap() throws IOException {
		//Recupera nuovamente il DOM
		document = Jsoup.connect(urlToScrap).get();
		//Effettua lo scrap dei link e delle immagini
		scrapLinks();
		scrapImages();
	}
	
	/*
	 * Metodo per lo scraping dei link
	 */
	private void scrapLinks() {
		Elements elementsLink = document.select("a[href]");	//Collection di Elements recuperati dai tag "a" con attributo "href"
		for(Element element : elementsLink){
			String url = element.attr("abs:href");	//Dalla collection recupera le URL assolute sottoforma di String
			links.add(url);							//Aggiunge l'URL alla LinkedList<String> DA MODIFICARE in caso di nuovo model				
		}
	}
	
	/*
	 * Metodo per lo scraping delle immagini
	 */
	private void scrapImages() {
		Elements elementsImages = document.select("img[src]");	//Collection di Elements recuperati dai tag "img" con attributo "src"
		for(Element element : elementsImages){
			String imageUrl = element.attr("src");	//Preleva l'URL delle immagini sottoforma di String
			String[] splittedUrlImage = imageUrl.split("/");	//Splitta l'URL
			String imageName = splittedUrlImage[splittedUrlImage.length - 1];	//L'ultimo elemento è il nome dell'immagine	"nomeimmagine.estensione"	
			images.put(imageName, imageUrl);		//Aggiunge nome e URL alla mappa DA MODIFICARE in caso di nuovo Model
		}
	}
}
