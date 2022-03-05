
/**
 * Processes country export data from a .csv file
 * 
 * @author Kyle
 * @version 3/5/22
 */
import edu.duke.*;
import org.apache.commons.csv.*;
/**reading documentation:
 * Parses CSV files according to the specified format. Because CSV appears in many different dialects, the parser supports many formats by allowing the specification of a CSVFormat. 
 * The parser works record wise. It is not possible to go back, once a record has been parsed from the input stream.
 * Is that why this is broken? I have to reinitialize the parser each time!
 * Yes!! That is what it says in part1. Dang it!!!!
 * 
 * Lesson learned: Read the documentation! and carefully read the assignment!
 */

public class countryExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        //just realized below will yield "NOT FOUND" no matter what
        //need to reinitialize parser
        System.out.println(countryInfo(parser, "Iraq"));
        //countryInfo2(parser, "Germany");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold", "diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
                
    }
    public void quiz() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    //3
    listExportersTwoProducts(parser,"fish", "nuts");
    //Panama is the 3rd country that exports fish and nuts
    System.out.println();
    //4
    parser = fr.getCSVParser();
    System.out.println(numberOfExporters(parser, "gold"));
    System.out.println();
    //30 countries export gold
    //5
    parser = fr.getCSVParser();
    System.out.println(countryInfo(parser, "Nauru"));
    System.out.println();
    //phosphates is an export of this country
    //6
    parser = fr.getCSVParser();
    bigExporters(parser, "$999,999,999,999");
    //European Union is 2nd country that exports >$1T
    }
    
    public String countryInfo(CSVParser parser, String country) {
        int test = 0; 
        for(CSVRecord record : parser) {
            String currCountry = record.get("Country");            
            if (currCountry.contains(country)) {
                return country + ": " + record.get("Exports")
                + ": " + record.get("Value (dollars)");                
            }
                
        }
        
        return "NOT FOUND";
        }
        
        //a debug class
        public void countryInfo2(CSVParser parser, String country) {
        int test = 0; 
        for(CSVRecord record : parser) {
           test ++;
           System.out.println(test);
           String currCountry = record.get("Country");            
           if (currCountry.contains(country)) {
                System.out.println(country + ": " + record.get("Exports")
                + ": " + record.get("Value (dollars)"));                
            }
                
        }
        
        System.out.println("NOT FOUND");
        }
        
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
    //int test = 0;
    for (CSVRecord record : parser){
        String currExport = record.get("Exports");     
        if (currExport.contains(exportItem1) && currExport.contains(exportItem2)) {
            String country = record.get("Country");
            System.out.println(country);
    }
            
        }
        
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser){
            String currExport = record.get("Exports");
            if (currExport.contains(exportItem)) count ++;
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String currDollars = record.get("Value (dollars)");
            if (currDollars.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + currDollars);
            }
        }
    }
   
        //another debug class
        //wanted to examine whether strings can be reassigned.
        //they can!
        public void stringTester() {
            String a = "This is string a";
            String b = a;
            b = "This is string b";//you can reassign strings!
            
            System.out.println("String a: " + a);
            System.out.println("String b: " + b);
            if (a == "This is string a") System.out.println("Strings can equal each other");
            
        }
            
    }

