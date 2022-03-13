import edu.duke.*;
import org.apache.commons.csv.*;
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
    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames_test/yob2012short.csv");
        totalBirths(fr);
    }
    
}
