package model;
/**
 * Classe adibita allo scraping degli URL e delle immagini (sottoformadi mappa Nome-URL)
 * della pagina web passata come String url al costruttore
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

public class JSoupScraper {
	private String urlToScrap;
	private Document document;
	private LinkedList<String> links = new LinkedList<>();	//Provvisorio in attesa del model
	private HashMap<String, String> images = new HashMap<>();	//Provvisorio in attesa del model
	
	/*
	 * Il costruttore riceve come parametro l'URL sottoforma di String
	 * ed effettua lo scraping di URL e mappa immagini
	 */
	public JSoupScraper(String validateUrl) throws IOException {
		//Salvo nel campo l'url
		urlToScrap = validateUrl;
		//Prelevo il documento e lo salvo nel campo
		document = Jsoup.connect(urlToScrap).get();
		//Effettuo lo scraping dei link
		scrapLinks();
		scrapImages();
	}
	
	/*
	 * Ritorna la lista linksottoforma di LinkedList
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
	
	/*
	 * Metodo per lo scraping dei link
	 */
	private void scrapLinks() {
		Elements elementsLink = document.select("a[href]");
		for(Element element : elementsLink){
			String url = element.attr("abs:href"); //prelevo l'url sottoforma di String
			links.add(url);
		}
	}
	
	/*
	 * Metodo per lo scraing delle immagini
	 */
	private void scrapImages() {
		Elements elementsImages = document.select("img[src]");
		for(Element element : elementsImages){
			String imageUrl = element.attr("src");	//prelevo l'url delle immagini sottoforma di String
			String[] splittedUrlImage = imageUrl.split("/");	//splitto l'url
			String imageName = splittedUrlImage[splittedUrlImage.length - 1];	//l'ultimo elemento è il nome dell'immagine		
			images.put(imageName, imageUrl);
		}
	}
}
