
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> occurences;
    
    public CharactersInPlay() {
        this.characters = new ArrayList<String>();
        this.occurences = new ArrayList<Integer>();
    }
    
    
    private void findAllCharacters () {
        FileResource fr =  new FileResource();
        String speakingPart = "";
        int indexOfPeriod = 0;
        for (String s : fr.lines()) {
            indexOfPeriod = s.indexOf(".");
            if (indexOfPeriod != -1) {
                speakingPart = s.substring(0, indexOfPeriod);
                update(speakingPart);
            }
        }
    }
    
    private void update(String person) {
        if (!this.characters.contains(person)) {
            this.characters.add(person);
            this.occurences.add(1);
            return;
        }
        int index = this.characters.indexOf(person);
        this.occurences.set(index, this.occurences.get(index)+1);
    }
    
    private void charactersWithNumParts (int num1, int num2) {
        for (int i = 0; i < characters.size(); ++i) {
            int currentOccur = occurences.get(i);
            if (currentOccur >= num1 && currentOccur <= num2)
                System.out.println(currentOccur + "   " + characters.get(i));
        }
    }
    
    
    public void tester() {
        findAllCharacters();
        System.out.println("# Unique characters: " + characters.size());
        for (int i = 0; i < characters.size(); ++i) {
            if (occurences.get(i) > 20)
                System.out.println(occurences.get(i) + "   " + characters.get(i));
        }
        System.out.println("\nTesting\n");
        charactersWithNumParts(10, 15);
    }
}
