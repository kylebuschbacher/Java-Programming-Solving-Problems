
/**
 * Write a description of weatherProcessor here.
 * Find coldest hour in a csv file
 * 
 * @author Kyle
 * @version 3/7/22
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class weatherProcessor {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser){
            //need to get currentRow's temperature, and convert to a number
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF")
            if (currentRow.get("TemperatureF") == -9999) continue;
            if (currentRow == null) coldestSoFar = currentRow;
            if (currentRow < coldestSoFar) coldestSoFar = currentRow;
        }
        return coldestSoFar;
    }
}
