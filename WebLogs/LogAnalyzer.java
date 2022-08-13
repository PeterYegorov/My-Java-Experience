
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         this.records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line: fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String ip = le.getIpAddress();
             if (!uniqueIPs.contains(ip))
                uniqueIPs.add(ip);
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum (int num) {
         for (LogEntry le: records) {
             int sc = le.getStatusCode();
             if(sc > num)
                System.out.println("Status code greater than " + num + ": " + sc);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String day) {
         ArrayList<String> dayIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String date = le.getAccessTime().toString().substring(4, 10);
             if(date.equals(day)) {
                 if (!dayIPs.contains(le.getIpAddress()))
                    dayIPs.add(le.getIpAddress());
             }
         }
         return dayIPs;
     }
     
     public int countUniqueIPsInRange (int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         int count = 0;
         for (LogEntry le: records) {
             int sc = le.getStatusCode();
             if(sc >= low && sc <= high && !uniqueIPs.contains(le.getIpAddress())) {
                 uniqueIPs.add(le.getIpAddress());
                 ++count;
             }
         }
         return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> visits = new HashMap<String, Integer>();
         for (LogEntry le: records) {
             String ip = le.getIpAddress();
             if (visits.containsKey(ip)) {
                 int currentCount = visits.get(ip);
                 visits.put(ip, ++currentCount);
             } else {
                 visits.put(ip, 1);
             }
         }
         return visits;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> visits) {
         int maxVisits = 0;
         for (int val: visits.values()) {
             if (val > maxVisits)
                 maxVisits = val;
         }
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visits) {
         int maxVisits = mostNumberVisitsByIP(visits);
         ArrayList<String> ips = new ArrayList<String>();
         for (String key: visits.keySet()) {
             int value = visits.get(key);
             if (value == maxVisits) {
                 maxVisits = value;
                 ips.add(key);
             }
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> dateIps = new HashMap<String, ArrayList<String>>();
         for (LogEntry le: records) {
             String date = le.getAccessTime().toString().substring(4, 10);
             if(dateIps.containsKey(date)) {
                 dateIps.get(date).add(le.getIpAddress());
             } else {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(le.getIpAddress());
                 dateIps.put(date, ips);
             }
         }
         return dateIps;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateIps) {
         int maxVisits = 0;
         String mostVisited = null;
         for (String day: dateIps.keySet()) {
             int currentListSize = dateIps.get(day).size();
             if (currentListSize > maxVisits) {
                 maxVisits = currentListSize;
                 mostVisited = day;
             }
         }
         return mostVisited;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateIps, String day) {
         HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
         ArrayList<String> ipsOnDay = dateIps.get(day);
         for (String s : ipsOnDay) {
             if(!ipCount.containsKey(s)){
                 ipCount.put(s, 1);
             }
             else{
                 ipCount.put(s, ipCount.get(s) + 1);
             }
         }
         return iPsMostVisits(ipCount);
     }
     
     
     
}
