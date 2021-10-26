import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Model_Liste {
	private String tag; 
	private String referenza;
	private final Date data;
	private int N = 0;
	
	
	
	public Model_Liste(Date data, String tag, String referenza) {
		super();
		this.data = data;
		this.tag = tag;
		this.referenza = referenza;
	}

	public String getData() {
		return data.toString();
	}
	
	public String getTag() {
		return tag;
	}

	public String getReferenza() {
		return referenza;
	}
	
	public int getN() {
		return N;
	}


	public void Time() {
		Date data = new Date(System.currentTimeMillis());
	}
	
	ArrayList LinkArray = new ArrayList(N);
	
	private int n = 0;
	public void Popolamento() {
		LinkArray.forEach((e) -> {
				LinkArray.add(n);
				n++;
			}); 
	}
	
	private final int A = 0;
	public char getLink() {
		return ((String) LinkArray.get(A)).charAt(0);
	}
	
	public void Ordinamento() {
		for(int i = 0; i < LinkArray.size(); i++) {
			int x = i;
			for(int j = i-1; j >= 0; j++) {
				if(((String) LinkArray.get(j)).charAt(0) > ((String) LinkArray.get(x)).charAt(0)) {
					int k = (int) LinkArray.get(x);
					LinkArray.set(x, LinkArray.get(j));
					LinkArray.set(j, k);
					x = j;
				}else break;
			}
		}
	}
}
