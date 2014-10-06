package lab;

/* --
COMP4321 Lab2 Exercise
*/
import java.util.Vector;
import org.htmlparser.beans.StringBean;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import java.util.StringTokenizer;
import org.htmlparser.beans.LinkBean;
import java.net.URL;


public class Crawler
{
	private String url;
	Crawler(String _url)
	{
		url = _url;
	}
	public Vector<String> extractWords() throws ParserException {
		// extract words in url and return them
		// use StringTokenizer to tokenize the result from StringBean
		Vector<String> result = new Vector<String>();
		StringBean sb = new StringBean();
		sb.setLinks(false);
		sb.setURL(this.url);
		StringTokenizer st = new StringTokenizer(sb.getStrings());
		while(st.hasMoreTokens()) {
			result.add(st.nextToken());
		}
		return result;
	}
	public Vector<String> extractLinks() throws ParserException {
		// extract links in url and return them
		Vector<String> result = new Vector<String>();
		LinkBean lb = new LinkBean();
		lb.setURL(this.url);
		URL[] urls = lb.getLinks();
		for(int i = 0; i < urls.length; i++) {
			result.add(urls[i].toString());
		}
		return result;
	}
	
	public static void main (String[] args)
	{
		try
		{
			Crawler crawler = new Crawler("http://www.cs.ust.hk/~dlee/4321/");


			Vector<String> words = crawler.extractWords();		
			
			System.out.println("Words in "+crawler.url+":");
			for(int i = 0; i < words.size(); i++)
				System.out.print(words.get(i)+" ");
			System.out.println("\n\n");
			

	
			Vector<String> links = crawler.extractLinks();
			System.out.println("Links in "+crawler.url+":");
			for(int i = 0; i < links.size(); i++)		
				System.out.println(links.get(i));
			System.out.println("");
			
		}
		catch (ParserException e)
            	{
                	e.printStackTrace ();
            	}

	}
}
	