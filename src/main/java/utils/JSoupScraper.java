package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 * Classe adibita allo scraping degli URL e delle immagini (sottoforma di mappa Nome-URL <String, String>)
 * della pagina web passata come url di tipo String al costruttore.
 * Una volta istanziato l'oggetto fornendo l'URL al costruttore non sarà più possibile modifcare questo valore,
 * ma rimarrà possibile riscaricare il DOM ed eseguire un nuovo
 * scraping delle informazioni richieste.
 * 
 * Il metodo scrapeJS preleva gli script "in-body" e sottoforma di risorse esterne e li salva in un file.
 * Questo metodo è in ALPHA essendo modellato sull home di Wikipedia
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
	private String scriptsFileName;
	
	/*
	 * Il costruttore riceve come parametro l'URL sottoforma di String
	 * ed effettua lo scraping di URL e mappa immagini
	 */
	public JSoupScraper(final String validatedUrl) throws InvalidParameterException, IOException {
		if (validatedUrl != null && !validatedUrl.isBlank()) {
			//Salva nel campo l'url
			this.urlToScrap = validatedUrl;
			//Preleva il DOM e lo salvo nel campo
			this.document = Jsoup.connect(urlToScrap).get();
			//Effettua lo scraping dei link
			scrapeLinks();
			scrapeImages();
			scrapeJS();
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
	 * Ritorna il nome del file in cui sono stati salvati gli script del DOM
	 */
	public String getScriptsFileName() {
		return scriptsFileName;
	}
	
	/**
	 * Esegue nuovamente lo scraping delle informazioni dalla pagina web
	 */
	public void rescrape() throws IOException {
		//Recupera nuovamente il DOM
		document = Jsoup.connect(urlToScrap).get();
		//Effettua lo scrape dei link e delle immagini
		scrapeLinks();
		scrapeImages();
		scrapeJS();
	}
	
	/*
	 * Metodo per lo scraping dei link
	 */
	private void scrapeLinks() {
		Elements elementsLink = document.select("a[href]");	//Collection di Elements recuperati dai tag "a" con attributo "href"
		for(Element element : elementsLink){
			String url = element.attr("abs:href");	//Dalla collection recupera le URL assolute sottoforma di String
			links.add(url);							//Aggiunge l'URL alla LinkedList<String> DA MODIFICARE in caso di nuovo model				
		}
	}
	
	/*
	 * Metodo per lo scraping delle immagini
	 */
	private void scrapeImages() {
		Elements elementsImages = document.select("img[src]");	//Collection di Elements recuperati dai tag "img" con attributo "src"
		for(Element element : elementsImages){
			String imageUrl = element.attr("src");	//Preleva l'URL delle immagini sottoforma di String
			String[] splittedUrlImage = imageUrl.split("/");	//Splitta l'URL
			String imageName = splittedUrlImage[splittedUrlImage.length - 1];	//L'ultimo elemento è il nome dell'immagine	"nomeimmagine.estensione"	
			images.put(imageName, imageUrl);		//Aggiunge nome e URL alla mappa DA MODIFICARE in caso di nuovo Model
		}
	}
	
	/**
	 * Metodo ALPHA per lo scraping di JavaScript dal DOM
	 */
	private void scrapeJS() {
		final String END_SCRIPT = "\n\n||-------------||\n\n";	//String per separare i vari script prelevati
		String[] splittedUrl = urlToScrap.split("/");						//Nome del file generato a partire
		String fileName = splittedUrl[splittedUrl.length - 1] + "_JS.txt";	//dalla pagina ricavata dall'URL
		
		splittedUrl = null;
		
		this.scriptsFileName = fileName;
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {	//Try with resources per scrivere su file di testo
			//Seleziona tutti i tag <script> e li salva in una Collection
			Elements elementsInBodyJS = document.select("script");
			for(Element element : elementsInBodyJS) {
				writer.append(element.data().toString());	//Inserisce in coda al file il contenuto dello script "in-body"
				writer.append(END_SCRIPT);
			}
			elementsInBodyJS = null;
			
			//Seleziona tutti i tag <script> con script in risorse esterne
			Elements elementsExternalJS = document.select("script[src]");
			for(Element element : elementsExternalJS) {
				//Preleva l'URL da cui è raggiungibile lo script
				String url = element.attr("abs:src");
				//TO-DO: Validare l'url
				Document scriptDocument = Jsoup.connect(url).get();	//Preleva il DOM
				//Inserisce in coda al file il contenuto del body del DOM
				writer.append(scriptDocument.body().text());
				writer.append(END_SCRIPT);
			}
			elementsExternalJS = null;			
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
