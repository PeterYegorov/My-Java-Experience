
/**
 * Write a description of Exports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports {
    
    public void printExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord r : parser) {
            String export = r.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = r.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void printExporters2products(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord r : parser) {
            String export = r.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = r.get("Country");
                System.out.print(country + "    ");
            }
        }
        System.out.println();
    }
    
    public int numberOfExporters(CSVParser parser, String exportOfInterest) {
        int counter = 0;
        for (CSVRecord r : parser) {
            String export = r.get("Exports");
            if (export.contains(exportOfInterest))
                ++counter;
        }
        return counter;
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord r : parser) {
            String currCountry = r.get("Country");
            if (currCountry.equals(country)) {
                return currCountry + ": " + r.get("Exports") + ": "
                    + r.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void bigExporters(CSVParser parser, String dumb) {
        for (CSVRecord r : parser) {
            String currValue = r.get("Value (dollars)");
            if (currValue.length() > dumb.length()) {
                System.out.println(r.get("Country") + ' ' + currValue);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        printExporters2products(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        /*System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();*/
        bigExporters(parser, "$999,999,999,999");
    }
    
    public static void main(String[] args) {
        Exports e = new Exports();
        e.tester();
    }
}
