
/**
 * Write a description of Part2 here.
 * Finds the ratio of C's and G's to the length of an input string
   * 
 * @author Kyle 
 * @version 2/27/22
 */
import edu.duke.*;

public class Part2 {
    public double cgRatio(String dna){
        int count = 0;
        int index = 0;
        int indexC = 0;
        int indexG = 0;
        int minIndex = 0;
        
        while(true) {
            indexC = dna.indexOf("C", index);
            indexG = dna.indexOf("G", index);
            //logic messed up: if indexG = -1, it is less than indexC
            //if (indexC < indexG && indexC != -1) minIndex = indexC;
            //else minIndex = indexG;
            if (indexG == -1) minIndex = indexC;
            if (indexC == -1) minIndex = indexG;
            if (indexC < indexG && indexC != -1 && indexG != -1) minIndex = indexC;
            if (indexG < indexC && indexC != -1 && indexG != -1) minIndex = indexG;
            if (minIndex == -1) break;
            count ++;
            index = minIndex + 1;
        }
        double ratio = (double) count/dna.length();
        return ratio;
    }
    public void testcgRatio(){
        //case 1
        String testDna = "ATGABD";
        if (cgRatio(testDna) != 1.0/6) System.out.println("Case 1 Error");
        //case 2
        testDna = "ATCABD";
        if (cgRatio(testDna) != 1.0/6) System.out.println("Case 2 Error");
        System.out.println(cgRatio(testDna));
        //case 3
        testDna = "ATGABG";
        if (cgRatio(testDna) != 2.0/6) System.out.println("Case 3 Error");
        //case 4
        testDna = "ATGGCC";
        if (cgRatio(testDna) != 4.0/6) System.out.println("Case 4 Error");
        System.out.println(cgRatio(testDna));
        //case 5
        testDna = "GGGCCC";
        if (cgRatio(testDna) != 6.0/6) System.out.println("Case 5 Error");
        //case 6
        testDna = "GGGGGG";
        if (cgRatio(testDna) != 6.0/6) System.out.println("Case 6 Error");
        //case 7
        testDna = "CCCCCC";
        if (cgRatio(testDna) != 6.0/6) System.out.println("Case 7 Error");
        //case 8
        testDna = "AAAAAA";
        if (cgRatio(testDna) != 0.0/6) System.out.println("Case 8 Error");
        System.out.println("End of test");
    }
    public int countCTG(String dna) {
        int count = 0;
        int startIndex = 0;
        int currIndex = 0;
        while (true) {
            currIndex = dna.indexOf("CTG", startIndex);
            if (currIndex == -1) break;
            count ++;
            startIndex = currIndex + 3;
        }
        return count;
    }
    public void testcountCTG() {
        //case 1 1 CTG
        String testdna = "xxxCTGyyy";
        if (countCTG(testdna) == 1) System.out.println("Case 1 successful");
        else System.out.println("Case 1 failure, count is " + countCTG(testdna));
        //case 2 5 CTG
        testdna = "xxxCTGyyyCTGsCTGCTGxxvCTGjjj";
        if (countCTG(testdna) == 5) System.out.println("Case 2 successful");
        else System.out.println("Case 2 failure, count is " + countCTG(testdna));
        //case 3 0 CTG
        testdna = "xxCCCCCCDFDFDFDTTTTTTGGGGGjjj";
        if (countCTG(testdna) == 0) System.out.println("Case 3 successful");
        else System.out.println("Case 3 failure, count is " + countCTG(testdna));
        System.out.println("Test Completed");
    }
    public void processGenes(StorageResource sr) {
        int plusNine = 0;
        int plusCG = 0;
        int longestLength = 0;
        String longestString = "";
        for (String s: sr.data()) {
            if (s.length() > 60) {
                System.out.println(s);
                plusNine ++;                
            }
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                plusCG ++;
            }
            if (s.length() > longestLength){
                longestLength = s.length();
                longestString = s;
            }
        }
        System.out.println("Number of strings longer than 9 characters: " + plusNine);
        System.out.println("Number of strings with cg ratio > 0.35: " + plusCG);
    }
    public void testProcessGenes() {
        StorageResource testSr= new StorageResource(); 
        //testSr.add("xxx");
        //testSr.add("xxxyyytttbbb");
        //testSr.add("xxxCGCGxyxc");
        //testSr.add("gggggggggggggggg");
        //testSr.add("");
        //processGenes(testSr);
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        System.out.println("raw dna: " + dna);
        Part1 testPart1 = new Part1();
        testSr = testPart1.getAllGenes(dna);
        testPart1.printAllGenes(dna);
        processGenes(testSr);
    }
    
}
