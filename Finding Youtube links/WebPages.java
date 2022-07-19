
/**
 * Write a description of WebPages here.
 * 
 * @author (Peter Yegorov) 
 * @version (1.1)
 */
import edu.duke.*;
public class WebPages {
    
    public void printYouTubeURLs (String url) {         // Print all YouTube links from the input page
        URLResource urlRes = new URLResource(url);
        for (String word : urlRes.words()) {
            if (word.toLowerCase().indexOf("youtube.com") != -1) {
                int firstQuoteIndex = word.indexOf("\"") + 1;
                int secondQuoteIndex = word.indexOf("\"", firstQuoteIndex);
                String urlStr = word.substring(firstQuoteIndex, secondQuoteIndex);
                System.out.println(urlStr);
            }
        }
    }
    
    public void test() {
        printYouTubeURLs("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
    
    public static void main (String[] args) {
        WebPages wp = new WebPages();
        wp.test();
    }
}
