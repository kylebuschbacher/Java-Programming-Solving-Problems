
/**
 * Write a description of weatherProcessor here.
 * Find coldest hour in a csv file
 * 
 * @author Kyle
 * @version 3/7/22
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherProcessor {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser){
            //need to get currentRow's temperature, and convert to a number
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp == -9999) continue;
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
                //double coldestTempSoFar = currentTemp;
            }
            else{
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp) {
                    coldestSoFar = currentRow;
                }
            }            
        }
        return coldestSoFar;
    }
    public String fileWithColdestTemperature(){
        CSVRecord largestSoFar = null;
        String fileName =""; 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (largestSoFar == null) {
                largestSoFar = currentRow;
                //double coldestTempSoFar = currentTemp;
                fileName = f.getName();
            }
            else{
                double coldestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp) {
                    largestSoFar = currentRow;
                    fileName = f.getName();
                }
            }
            
            }
            return fileName;
            
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowHumiditySoFar = null;
        for (CSVRecord currentRow : parser){
            //need to get currentRow's temperature, and convert to a number
            if (currentRow.get("Humidity") == "N/A") continue;
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));            
            if (lowHumiditySoFar == null) {
                lowHumiditySoFar = currentRow;
                
            }
            else{
                double lowestHumidity = Double.parseDouble(lowHumiditySoFar.get("Humidity"));
                if (currentHumidity < lowestHumidity) {
                    lowHumiditySoFar = currentRow;
                }
            }            
        }
        return lowHumiditySoFar;
    }
    
    public void testColdestInDay() {
        FileResource fr = new FileResource("nc_weather/2012/weather-2012-01-01.csv");
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + largest.get("TemperatureF") + " at "
                            + largest.get("TimeEST"));
        
    }
    public void testFileWithColdestTemperture() {
        String largest = fileWithColdestTemperature();
        System.out.println(largest);
        FileResource fr = new FileResource("nc_weather/2014/" + largest);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at "
                            + coldest.get("TimeEST"));
        
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-01-20.csv");
        CSVRecord csv = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was " + csv.get("Humidity") + " at "
                            + csv.get("DateUTC"));
    }
}
