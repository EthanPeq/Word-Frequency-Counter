import java.io.*;
import java.util.*;

public class TextAnalysis {
	File file;
	ArrayList<String> words;
	ArrayList<Integer> count;
	
	public TextAnalysis() {
		words = new ArrayList<String>();
		count = new ArrayList<Integer>();
		
	}
	public void setFile(String fileString) {
		file = new File(fileString);
	}
	
	public void analysisFile() throws FileNotFoundException {
		Scanner inputFile = new Scanner(file);
	
		while (inputFile.hasNext()) {
			String nextWord = inputFile.next().replaceAll("\\p{Punct}", "").toLowerCase();
			
			if(words.contains(nextWord)){
				int index = words.indexOf(nextWord);
				count.set(index, count.get(index)+1);
			}
			else {
				words.add(nextWord);
				count.add(1);
			}		    
		}
	}
	
	public String getOriginalTxt() throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String msg = "";
		int cnt = 2;

		while(sc.hasNext()) {
			msg += sc.next() + " ";
			if(cnt % 5 == 0) {
				msg += "\n";
			}
			cnt++;
		}
		
		return msg;
	}
	
	public String getUniqueWordCount() {
		String msg = "Number of Unique Words:\n";
		return msg + words.size();
	}
	
	public String getWords() {
		String msg = "";
		for(int i = 0; i < words.size(); i++) {
			msg += words.get(i) + " " + count.get(i) + "\n";
		}
		return msg;
	}
	
	public int wordSearch(String word) {
		int index = words.indexOf(word.toLowerCase());
		return count.get(index);
	}
	
	public void sortByFrequency() {	
		boolean sorted = false;
		while(!sorted) {
			sorted = true;
			for(int i = 0; i < count.size()-1; i++) {
				if(count.get(i) < count.get(i+1)) {
					int num = count.get(i);
					count.set(i, count.get(i+1));
					count.set(i+1, num);
					
					String word = words.get(i);
					words.set(i, words.get(i+1));
					words.set(i+1, word);
					sorted = false;
				}
			}
		}
	}
	
    //Print the word count{}
	//get txt file{}
	//recieve the txt file{}
	//number of unique words{}
	//word search for frequency{}
	//sort words based on frequency
	
	
	
}
