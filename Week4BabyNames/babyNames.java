import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Processes baby name data.
 * 
 * @author Kyle
 * @version 3/13/22
 */
public class babyNames {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlNames = 0;
        int boyNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boyNames ++;
            }
            else {
                totalGirls += numBorn;
                girlNames ++;
            }
            
        }
        System.out.println("Total number of births = " + totalBirths);
        System.out.println("Total number of girls = " + totalGirls);
        System.out.println("Total number of girl names = " + girlNames);
        System.out.println("Total number of boys = " + totalBoys);
        System.out.println("Total number of boy names = " + boyNames);
        
    }
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int rank = -1;
        int count = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(1).equals(gender)) continue;
            String currName = rec.get(0);
            if (currName.equals(name)) rank = count + 1;
            count ++;
        }
        return rank;
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int currRank = 1;
        String noName = "NO NAME";
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(1).equals(gender)) continue;
            if (currRank != rank) currRank ++;
            else return rec.get(0);
        }
        return noName;
    }
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        //FileResource fr = new FileResource("us_babynames_test/yob" + year+ "short.csv");
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        return newName;
        
    }
    public int yearOfHighestRank(String name, String gender){
        //seems to work, though I messed up. Supposed to return year of highest rank. Not the actual highest rank
        //all files seem to be "yob" + the year. so need to parse the file name to retrieve the year.
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int yearHighest = -1;
        //I need to get the file name so I can create a file resource
        for (File f : dr.selectedFiles()) {
            int rank = -1;
            int count = 0;            
            String fileName = f.getName();
            FileResource fr = new FileResource("us_babynames_test/" + fileName); //change as needed for testing
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (!rec.get(1).equals(gender)) continue;
                String currName = rec.get(0);
                if (currName.equals(name)) rank = count + 1;
                count ++;
            }
            if (highestRank == -1 || rank < highestRank) {
                highestRank = rank;
                }                   
            if (rank == highestRank && highestRank != -1) {
                String highest = fileName.substring(3,7);
                yearHighest = Integer.parseInt(highest);
            }
        }
        return yearHighest;
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames_test/yob2012short.csv");
        totalBirths(fr);
    }
    
}
