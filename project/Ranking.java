package project;

import java.util.ArrayList;
import java.io.IOException;

import jdbm.helper.FastIterator;

public class Ranking {
	private ArrayList<VectorScore> vectorSpace;
	private InvertedIndex idPageRankIndex;
	
	public Ranking(ArrayList<VectorScore> vectorSpace) throws IOException {
		this.idPageRankIndex = new InvertedIndex("idPageRank", "ht1");
		this.vectorSpace = vectorSpace;
	}
	
	public ArrayList<VectorScore> compute() throws IOException{
		double vectorSpaceWeight = 0.5;
		double tempScore = 0;
		
		FastIterator iter = idPageRankIndex.getIteratorKeys();
		String key;
		return this.vectorSpace;
	}
}
