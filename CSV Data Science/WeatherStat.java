
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.FileReader;

public class WeatherStat {
    
    public String fileWithColdestTemperature() {
        String filename = "";
        File coldestDay = null;
        CSVRecord currentLowest = null;
        CSVRecord lowest = null;
        double currentLowestTemp;
        double lowestTemp = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            if (lowest == null) {
                lowest = coldestHourInFile(fr.getCSVParser());
                coldestDay = f;
                filename = f.getName();
                lowestTemp = Double.parseDouble(lowest.get("TemperatureF"));
                continue;
            }
            
            currentLowest = coldestHourInFile(fr.getCSVParser());
            currentLowestTemp = Double.parseDouble(currentLowest.get("TemperatureF"));
            
            if (currentLowestTemp < lowestTemp) {
                coldestDay = f;
                filename = f.getName();
                lowest = currentLowest;
                lowestTemp = currentLowestTemp;
            }
        }
        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temerature " + lowestTemp);
        
        return filename;
    }
    
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largest = null;
        for (CSVRecord currentLine : parser) {
            largest = getLargestOfTwo (currentLine, largest);
        }
        return largest;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentLine : parser) {
            lowest = getLowestOfTwo(currentLine, lowest);
        }
        return lowest;
    }
    
    public double averageTemperatureInFile (CSVParser parser, int humidityValue) {
        double sum = 0;
        int numberOfTimes = 0;
        for (CSVRecord currentLine : parser) {
            
            if (currentLine.get("Humidity") != "N/A" && Double.parseDouble(currentLine.get("Humidity")) > humidityValue) {
                sum += Double.parseDouble(currentLine.get("TemperatureF"));
                ++numberOfTimes;
            }
        }
        if (numberOfTimes == 0)
            return -1;
        return sum/numberOfTimes;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentLine : parser) {
            lowest = getLowestOfTwoHum(currentLine, lowest);
        }
        return lowest;
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentLine = hottestHourInFile(fr.getCSVParser());
            largest = getLargestOfTwo(currentLine, largest);
        }
        return largest;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentLine = lowestHumidityInFile(fr.getCSVParser());
            lowest = getLowestOfTwoHum(currentLine, lowest);
        }
        return lowest;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentLine, CSVRecord largest) {
        if (largest == null)
                largest = currentLine;
        else if (Double.parseDouble(currentLine.get("TemperatureF"))
            > Double.parseDouble(largest.get("TemperatureF"))) {
                largest = currentLine;
            }
        return largest;
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentLine, CSVRecord lowest) {
        if (Double.parseDouble(currentLine.get("TemperatureF")) < -100)
            return lowest;
        if (lowest == null)
                lowest = currentLine;
        else if (Double.parseDouble(currentLine.get("TemperatureF"))
            < Double.parseDouble(lowest.get("TemperatureF"))) {
                lowest = currentLine;
            }
        return lowest;
    }
    
    public CSVRecord getLowestOfTwoHum(CSVRecord currentLine, CSVRecord lowest) {
        if (currentLine.get("Humidity").equals("N/A"))
            return lowest;
        if (lowest == null)
                lowest = currentLine;
        else if (Double.parseDouble(currentLine.get("Humidity"))
            < Double.parseDouble(lowest.get("Humidity"))) {
                lowest = currentLine;
            }
        return lowest;
    }
    
    public void tester() {
        /*FileResource fr = new FileResource("data/2015/weather-2015-01-04.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest: " + largest.get("TemperatureF") +
            " at " + largest.get("TimeEST"));*/
        
        /*FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest: " + lowest.get("TemperatureF") +
            " at " + lowest.get("DateUTC"));*/
            
        /*CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest: " + largest.get("TemperatureF") +
            " on " + largest.get("DateUTC"));*/
            
        fileWithColdestTemperature();
        
        /*FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));*/
        
        /*CSVRecord lowestHum = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was: " + lowestHum.get("Humidity") +
            " on " + lowestHum.get("DateUTC"));*/
            
        /*FileResource fr = new FileResource();
        System.out.println("Avarage temp: " + averageTemperatureInFile(fr.getCSVParser(), 80));*/
    }
    
    public static void main(String[] args) {
        WeatherStat ws = new WeatherStat();
        ws.tester();
    }
}
