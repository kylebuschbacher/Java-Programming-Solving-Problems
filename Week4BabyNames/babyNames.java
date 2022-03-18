import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Processes baby name data.
 * 
 * @author Kyle
 * @version 3/17/22
 * all methods complete. Doing quiz1 in void method quiz 1
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
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirthsHigher = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
             if (!rec.get(1).equals(gender)) continue;
             String currName = rec.get(0);
             if (!currName.equals(name)) totalBirthsHigher += Integer.parseInt(rec.get(2));
             else return totalBirthsHigher;
            }
        return totalBirthsHigher;
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames_test/yob2012short.csv");
        
        totalBirths(fr);
    }
    public void quiz1(){
        //1. 
        int year = 1900;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        System.out.println("Question 1");
        totalBirths (fr);
        //2. 
        year = 1905;
        fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        System.out.println("Question 2");
        totalBirths (fr);
        //3.
        year = 1960;
        String name = "Emily";
        String gender = "F"     ;  
        int rank = getRank(year, name, gender);
        System.out.println("Question 3:");
        System.out.println("Rank for " + name + ": " +rank);
        //4.
        year = 1971;
        name = "Frank";
        gender = "M";       
        rank = getRank(year, name, gender);
        System.out.println("Question 4:");
        System.out.println("Rank for " + name + ": " +rank);
        //5. 
        rank = 350;
        gender = "F";
        year = 1980;
        name = getName(year, rank, gender);
        System.out.println("Question 5:");
        System.out.println("Rank for " + name + ": " +rank);
        //6. Question 6 What is the boy’s name of rank 450 in 1982?
        rank = 450;
        gender = "M";
        year = 1982;
        name = getName(year, rank, gender);
        System.out.println("Question 6:");
        System.out.println("Rank for " + name + ": " +rank);
        //7. Suppose Susan was born in 1972. Based on her name's rank in 1972, 
        //what would her name be if she were born in 2014
        year = 1972;
        gender = "F";
        int newYear = 2014;
        name = "Susan";
        String newName = whatIsNameInYear(name, year, newYear, gender);
        System.out.println("Question 7:");
        System.out.println(name + " in " + year + " would be " + newName + " in " + newYear);
        //8. Suppose Owen was born in 1974. Based on his name's rank in 1974, 
        //what would his name be if he were born in 2014
        year = 1974;
        gender = "M";
        newYear = 2014;
        name = "Owen";
        newName = whatIsNameInYear(name, year, newYear, gender);
        System.out.println("Question 8:");
        System.out.println(name + " in " + year + " would be " + newName + " in " + newYear);
        
    }
}
