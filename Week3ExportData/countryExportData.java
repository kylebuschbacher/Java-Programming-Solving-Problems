
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
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for(CSVRecord record : parser) {
            //do I use .get or .contains on record? read documentation.
        }
    }
}
