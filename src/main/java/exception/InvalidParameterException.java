package exception;
/**
 * Eccezione utilizzata per i parametri non validi (al momento utilizzata in JSoupScraper
 * @author Cristian Zanna
 */
public class InvalidParameterException extends Exception{
	private static final long serialVersionUID = 1L;	//Auto-generato da Eclipse

	public InvalidParameterException(String message) {
		super(message);
	}
}
