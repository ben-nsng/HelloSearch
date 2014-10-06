package project;

import java.util.LinkedList;
import java.util.ListIterator;
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
import java.io.IOException;

import project.InvertedIndex;

public class SpiderLL
{
	private String url;
	private int pages;
	private LinkedList<String> links;
	private InvertedIndex index;
	
	SpiderLL(String _url, int _pages) throws IOException {
		url = _url;
		pages = _pages;
		links = new LinkedList<String>();
		index = new InvertedIndex("idUrl","ht1");
	}
	
//	public Queue<String> crawlPage(String _url, Queue<String> links, Vector<String> processed) throws ParserException {
	public ListIterator<String> crawlPage(String _url, ListIterator<String> li) throws ParserException {	
		// TODO: Extract keywords and insert to inverted file (Indexer)
	
		
		// Extract and enqueue links that don't already exist 
		LinkBean lb = new LinkBean();
		lb.setURL(_url);
		URL[] urls = lb.getLinks();
	
		for(int i = 0; i < urls.length; i++) {
			String current = urls[i].toString();
			if(!links.contains(current))
				li.add(current);
		}
		
		return li;
	}
	
	public void crawl() throws ParserException, IOException {
		int numPages = this.pages;
		links.add(this.url);
		
		ListIterator<String> li = links.listIterator();
		while(li.hasNext()) {
			String next = li.next();
			System.out.println(numPages + "/" + this.pages + " pages remaining. Crawling " + next + "...");
			
			if(!index.exists(next)) {
				index.addEntry(Integer.toString(index.count()), next);
			}
			
			li = this.crawlPage(next, li);
		}
		
		System.out.println("\nComplete! " + pages + " pages crawled in total.");

		index.finalize();
//		index.printAll();
		
////	FOR DEBUGGING:		
//		Iterator<String> it = processed.iterator();
//		int i = 1;
//		while(it.hasNext()) {
//			System.out.println(i + ": " + it.next());
//			i++;
//		}
//		
//		Iterator<String> it2 = queue.iterator();
//		while(it2.hasNext()) {
//			System.out.println(i + ": " + it2.next());
//			i++;
//		}
	}
//	public void crawl2() throws ParserException, IOException {
//	int numPages = this.pages;
//	queue.add(this.url);
//	
//	while(!queue.isEmpty() && numPages > 0) {
//		
//		String next = queue.remove();
//		System.out.println(numPages + "/" + this.pages + " pages remaining. Crawling " + next + "...");
//		processed.add(next);
//		
//		if(!index.exists(next)) {
//			index.addEntry(Integer.toString(index.count()), next);
//		}
//		
//		this.crawlPage(next);
//		
//		numPages--;
//	}
//	
//	System.out.println("\nComplete! " + pages + " pages crawled in total.");
//
//	index.finalize();
////	index.printAll();
//	
//////FOR DEBUGGING:		
////	Iterator<String> it = processed.iterator();
////	int i = 1;
////	while(it.hasNext()) {
////		System.out.println(i + ": " + it.next());
////		i++;
////	}
////	
////	Iterator<String> it2 = queue.iterator();
////	while(it2.hasNext()) {
////		System.out.println(i + ": " + it2.next());
////		i++;
////	}
//}

////public Queue<String> crawlPage(String _url, Queue<String> links, Vector<String> processed) throws ParserException {
//public void crawlPage(String _url) throws ParserException {	
//// TODO: Extract keywords and insert to inverted file (Indexer)
//
//
//// Extract and enqueue links that don't already exist 
//LinkBean lb = new LinkBean();
//lb.setURL(_url);
//URL[] urls = lb.getLinks();
//
//for(int i = 0; i < urls.length; i++) {
//	String current = urls[i].toString();
//	if(!processed.contains(current) && !queue.contains(current))
//		queue.add(current);
//}
//
////return queue;
//}
	public static void main (String[] args) {
		try {
			SpiderLL crawler = new SpiderLL("http://www.cse.ust.hk/", 30);
			crawler.crawl();
		}
		catch (ParserException pe) {
			pe.printStackTrace ();
		}
		catch (IOException ioe) {
			ioe.printStackTrace ();
		}
	}
}
	