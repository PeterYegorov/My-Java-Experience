
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                myFreqs.set(index, myFreqs.get(index)+1);
            }
        }
    }
    
    private int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); ++i) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex))
                maxIndex = i;
        }
        return maxIndex;
    }
    
    public void test() {
        findUnique();
        System.out.println("# Unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); ++i) {
            if(myFreqs.get(i) > 100)
                System.out.println(myFreqs.get(i) + "   " + myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "
            + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
