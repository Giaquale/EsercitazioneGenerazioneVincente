package htmlUnit;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test_Scrape 
{

	public static void main(String args[]) throws Exception {

		String url = "https://it.wikipedia.org/wiki/Cowboy_Bebop";
		WebClient webClient = new WebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(false); //WARNING: Obsolete content type encountered: 'text/javascript'.
		HtmlPage htmlPage = webClient.getPage(url);

		System.out.println(htmlPage.getTitleText() + "\n");

		//querySelector
	    DomNode domNode = htmlPage.querySelector("p");
	    System.out.println(domNode.getVisibleText());

		//recupera tutti i link all'interno del sito
		List<HtmlAnchor> anchors = htmlPage.getAnchors();
		for (HtmlAnchor anchor : anchors) {
			System.out.println(anchor.getAttribute("href"));
		}
	}
}
