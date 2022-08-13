
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }
    
    public void testSCNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 14"));
        System.out.println("-----------------");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));
        System.out.println("-----------------");
        System.out.println(la.uniqueIPVisitsOnDay("Mar 17").size());
    }
    
     public void testUniqueIPSC() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.countUniqueIPsInRange(200,299));
        System.out.println("-----------------");
        System.out.println(la.countUniqueIPsInRange(300,399));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        //System.out.println(visits);
        System.out.println(la.mostNumberVisitsByIP(visits));
        System.out.println(la.iPsMostVisits(visits));
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Mar 17"));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    } 
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    } 
    
    public void quiz() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
        System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
        System.out.println(la.countUniqueIPsInRange(200,299));
        HashMap<String, Integer> hm = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(hm));
        System.out.println(la.iPsMostVisits(hm));
        HashMap<String, ArrayList<String>> hm2 = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(hm2));
        System.out.println(la.iPsWithMostVisitsOnDay(hm2, "Sep 29"));
    } 
    
    
    
    
    
    
    
    
}
