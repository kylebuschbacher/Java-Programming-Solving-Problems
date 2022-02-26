
/**
 * Find a gene in a strand of DNA where the stop codon could 
 * be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * 
 * @author Kyle
 * @version 2/25/2022
 */
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //need a current index
        int currIndex = startIndex + 3; //don't think the +3 really matters
        //find first occurence of stopCodon that appears past startIndex
        //and is a multiple of 3 away from start index
        while (currIndex != dna.length()) {
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            if (currIndex == -1) {
                break;
            }
                    }
        
        //if no such stopCodon, return the length of the dna strand
        return -1;
    }
    public void testFindStopCodon(){
        //test cases
        //case 1:
        //TAA at index 5  012345678901234                 
        String testDna = "xxxyyyTAAyyyxxx";
        int num = findStopCodon(testDna, 0, "TAA");
        if (num != 6) System.out.println("error on 9");
        //case 2: multiple TAA, but only one divisible by 3  
        //                 xxxyyyxxxyyyxxxyyyxxx
        //         0123456789012345678901234567890  
        testDna = "xxxyyyxxATGxTAAxTAATAATAAxTAAyyyxxx";
        num = findStopCodon(testDna, 8, "TAA");
        if (num != 26) System.out.println("error on 26");
        System.out.println(num);
        //case 3: look for TGA, but only TAA in dna
        testDna = "xxxyyyATGxxxTGAxxxyyy";
        num = findStopCodon(testDna, 0, "TAA");
        if (num != testDna.length() ) System.out.println("error on dnalength");

   }
   public String findGene(String dna, int startIndex) {
       //want to find DNA strings regardless of case:
       dna = dna.toUpperCase(); //strings are immutable, so I think this is right
       //find the index position of the start codon "ATG"
       int startCodon = dna.indexOf("ATG", startIndex);
       //if no "ATG", return empty string
       if (startCodon == -1) {
            return "";
        }
       //Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
       int stopTAA = findStopCodon(dna, startCodon, "TAA");
       //Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
       int stopTAG = findStopCodon(dna, startCodon, "TAG");
       //Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
       int stopTGA = findStopCodon(dna, startCodon, "TGA");
       //Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
       //int temp = Math.min(stopTAA, stopTAG);
       //int minCodon = Math.min(temp, stopTGA);
       //If there is no valid stop codon and therefore no gene, return the empty string.
       //if (minCodon == dna.length()) {
       //return "";
       //}
       //return dna.substring(startCodon, minCodon+3);
       //
       int minIndex;
       if (stopTAA == -1 ||
           stopTGA < stopTAA && stopTGA != -1) {
               minIndex = stopTGA;
            }
       else {
           minIndex = stopTAA;
       }
       if (minIndex == -1 || stopTAG < minIndex && stopTAG != -1) {
           minIndex = stopTAG;
        }
       if (minIndex == -1) return "";
       return dna.substring(startCodon, minIndex + 3);
       
       
    }
   public void testFindGene() {
       //test case 1: no ATG
       //                012345678901 
       String testdna = "xxxyyyTAAxxx";
       System.out.println(testdna);
       System.out.println(findGene(testdna, 0));
       //test case 2: one ATG, one valid TAA
       //         012345678901 
       testdna = "xxxATGcccTAAxxx";
       System.out.println(testdna);
       System.out.println(findGene(testdna, 0));
       //test case 3: one ATG, multiple valid stop codons
       //         012345678901234567890123456 
       testdna = "xxxATGcccTGAAAATAAxxxTAGxxx";
       System.out.println(testdna);
       System.out.println(findGene(testdna, 0));
       //test case 4: one ATG, no valid stop codons
       testdna = "xxxATGccccTGAAAATAAxxxTAGxxx";
       System.out.println(testdna);
       System.out.println(findGene(testdna, 0));
       //test case 5: one ATG, no stop codons
       testdna = "xxxATGcccxxx";
       System.out.println(testdna);
       System.out.println(findGene(testdna, 0));
    }
   public void printAllGenes(String dna) {
       int startIndex = 0;
       while (true) {
           String myGene = findGene(dna, startIndex);
           System.out.println(myGene);
           startIndex = dna.indexOf("ATG", startIndex) + myGene.length();
           if (myGene.isEmpty()) break;
        }
       
    }
    public StorageResource getAllGenes(String dna) {
       StorageResource sr = new StorageResource();
       int startIndex = 0;
       while (true) {
           String myGene = findGene(dna, startIndex);
           sr.add(myGene);
           startIndex = dna.indexOf("ATG", startIndex) + myGene.length();
           if (myGene.isEmpty()) break;
        }
       return sr;
    }
    public void testPrintAllGenes(){
    //case 1 multiple valid ATGs and stop codons
    String testdna = "xxxATGyyyxxxTAAxxxATGyyyTAGaaa";
    printAllGenes(testdna);
    //case 2 multiple ATGs, but no valid stop codons
    testdna = "xxxATGyyyxxxATGxTAAxxATG";
    printAllGenes(testdna);
    //case 3 multiple ATGs and stop codons, but only one valid stop codons
    testdna = "ATGyyyxATGTAATAAxxTGAxxx";
    printAllGenes(testdna);
    //case 4 no ATGs
    testdna = "TAATAATGATAGTAG";
    printAllGenes(testdna);
    System.out.println("Test Complete");
    }
    public void testGetAllGenes(){
    StorageResource testSr= new StorageResource();
    //case 1 multiple valid ATGs and stop codons
    String testdna = "xxxATGyyyxxxTAAxxxATGyyyTAGaaa";
    testSr = getAllGenes(testdna);
    for (String s: testSr.data()) {
        System.out.println(s);
    }
    //case 2 multiple ATGs, but no valid stop codons
    testdna = "xxxATGyyyxxxATGxTAAxxATG";
    printAllGenes(testdna);
    //case 3 multiple ATGs and stop codons, but only one valid stop codons
    testdna = "ATGyyyxATGTAATAAxxTGAxxx";
    printAllGenes(testdna);
    //case 4 no ATGs
    testdna = "TAATAATGATAGTAG";
    printAllGenes(testdna);
    System.out.println("Test Complete");
    }

}