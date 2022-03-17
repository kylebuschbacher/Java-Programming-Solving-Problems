import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Processes baby name data.
 * 
 * @author Kyle
 * @version 3/16/22
 * just need to write algorithm and implemment code for getTotalBirthsRankedHigher.
 * All other methods tested satisfactorily
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
        return getRankForCurrentYear(fr, name, gender);
        
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
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int yearHighest = -1;
        //I need to get the file name so I can create a file resource
        for (File f : dr.selectedFiles()) {
            int count = 0;            
            String fileName = f.getName();
            FileResource fr = new FileResource("us_babynames_test/" + fileName); //change as needed for testing
            int rank = getRankForCurrentYear(fr, name, gender);            
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
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int averageCount = 0;
        int totalRank = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            FileResource fr = new FileResource("us_babynames_test/" + fileName);
            //int currRank = -1;
            int count = 0;
            int currRank = getRankForCurrentYear(fr, name, gender);            
            if (currRank != -1) {
                totalRank += currRank;
                averageCount ++;
            }
        }
        if (totalRank == 0) return -1.0; {
        return (double) totalRank/averageCount;
        }
            
    }
    public int getRankForCurrentYear(FileResource fr, String name, String gender) {
        int count = 0;
        //I should make this its own method, getRankForCurrentYear
        for (CSVRecord rec : fr.getCSVParser(false)) {
             if (!rec.get(1).equals(gender)) continue;
             String currName = rec.get(0);
             if (currName.equals(name)) return count + 1;
             count ++;
            }
        return -1;    
            
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames_test/yob2012short.csv");
        totalBirths(fr);
    }
    
}
