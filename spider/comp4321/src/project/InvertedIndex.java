package project;

import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.htree.HTree;
import jdbm.helper.FastIterator;

import java.util.Vector;
import java.io.IOException;
import java.io.Serializable;

class Posting implements Serializable
{
	public String doc;
	public int freq;
	Posting(String doc, int freq)
	{
		this.doc = doc;
		this.freq = freq;
	}
}

class urlInfo {
	public String url;
	public int parent;
	public urlInfo(String _url, int _parent) {
		url = _url;
		parent = _parent;
	}
}

public class InvertedIndex
{
	private RecordManager recman;
	private HTree hashtable;

	public InvertedIndex(String recordmanager, String objectname) throws IOException
	{
		recman = RecordManagerFactory.createRecordManager(recordmanager);
		long recid = recman.getNamedObject(objectname);
			
		if (recid != 0)
			hashtable = HTree.load(recman, recid);
		else
		{
			hashtable = HTree.createInstance(recman);
			recman.setNamedObject(objectname, hashtable.getRecid() );
		}
	}

	public void finalize() throws IOException
	{
		recman.commit();
		recman.close();		
	} 
	public void commit() throws IOException
	{
		recman.commit();		
	} 

	public void addEntry(String id, String newEntry) throws IOException
	{
		hashtable.put(id, newEntry);
		recman.commit();
	}
	public void delEntry(String word) throws IOException
	{
		// Delete the word and its list from the hashtable
		hashtable.remove(word);
	} 
	public void printAll() throws IOException
	{
		// Print all the data in the hashtable
		FastIterator iter = hashtable.keys();
		String key;
		while((key = (String)iter.next()) != null) {
			System.out.println(key+" = "+hashtable.get(key));
		}
	}	
	public int count() throws IOException {
		FastIterator iter = hashtable.keys();
		int count = 0;
		String key;
		while((key = (String)iter.next()) != null) {
			count++;
		}
		return count;
	}
	public boolean exists(String _url) throws IOException {
		FastIterator iter = hashtable.values();
		String value;
		while((value = (String)iter.next()) != null) {
			if(value.equals(_url)) return true;
		}
		return false;
	}
//	public static void main(String[] args)
//	{
//		try
//		{
//			InvertedIndex index = new InvertedIndex("lab1","ht1");
//	
//			index.addEntry("cat", 2, 6);
//			index.addEntry("dog", 1, 33);
//			System.out.println("First print");
//			index.printAll();
//			
//			index.addEntry("cat", 8, 3);
//			index.addEntry("dog", 6, 73);
//			index.addEntry("dog", 8, 83);
//			index.addEntry("dog", 10, 5);
//			index.addEntry("cat", 11, 106);
//			System.out.println("Second print");
//			index.printAll();
//			
//			index.delEntry("dog");
//			System.out.println("Third print");
//			index.printAll();
//			index.finalize();
//		}
//		catch(IOException ex)
//		{
//			System.err.println(ex.toString());
//		}
//
//	}
}