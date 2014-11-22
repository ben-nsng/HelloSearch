package project;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class QueryInfo {

	private VectorScore vso;
	private InvertedIndex idUrl;
	//private InvertedIndex urlId;
	private urlInfo info;
	private InvertedIndex idBodyIndex;
	
	public QueryInfo(VectorScore vso) throws IOException {
		this.vso = vso;
		this.idUrl = new InvertedIndex("idUrl", "ht1");
		this.idBodyIndex = new InvertedIndex("idBody", "ht1");
		//this.urlId = new InvertedIndex("urlId", "ht1");
		info = (urlInfo)idUrl.getEntryObject(vso.getUrlId());
	}
	
	public double getScore() {
		return vso.getScore();
	}
	
	public String getUrlId() {
		return vso.getUrlId();
	}
	
	public String getUrl() {
		return info.url;
	}
	
	public String getPageTitle() {
		return info.title;
	}
	
	public int getSize() {
		return info.size;
	}
	
	public String getLastModified() {
		return info.lastModified;
	}
	
	public String getWordFreqs() {
		JSONArray ja = new JSONArray();
		TreeMap<String, Integer> map = info.getBodyUniqCount();
		
		int i = 0;
		for(Entry<String, Integer> e : map.entrySet()) {
			ja.put(e);
			if(i++ > 4) break;
		}
		
		return ja.toString();
	}
	
}
