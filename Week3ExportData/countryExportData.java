
/**
 * Processes country export data from a .csv file
 * 
 * @author Kyle
 * @version 3/1/22
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class countryExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        
    }
    
    public String countryInfo(CSVParser parser, String country) {
        //String output = "NOT FOUND"; // can I not do this? Strings immutable?
        String tempOutput;
        String output;
        for(CSVRecord record : parser) {
            String currCountry = record.get("Country");
            //System.out.println(currCountry);
            //System.out.println(country);
            if (currCountry == country) {
                tempOutput = currCountry;// + ": " + record.get("Exports")
                //+ ": " + record.get("Value (dollars)");
                break;
            }
                //This code is really messed up. I need to figure out
                //how to tell when I've reached the end of this for loop
                //read documentation
                //also, can I not update strings after I created one
                //
                //also, here's an idea: just return the string here.
                //if not found, just return "NOT FOUND".
        }
        if (tempOutput != country) {
            output = "NOT FOUND";
        }
        else {
            output = tempOutput; 
        }
        return output;      
    }
}
