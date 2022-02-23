
/**Count how many genes are in a strand of DNA
 * 
 * @author Kyle
 * @version 2/22/22
 */
public class Part3 {
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
    public void printAllGenes(String dna) {
       int startIndex = 0;
       while (true) {
           String myGene = findGene(dna, startIndex);
           System.out.println(myGene);
           startIndex = dna.indexOf("ATG", startIndex) + myGene.length();
           if (myGene.isEmpty()) break;
        }
       
    }
    public int countGenes(String dna) {
       int startIndex = 0;
       int count = 0;
       while (true) {
           String myGene = findGene(dna, startIndex);
           startIndex = dna.indexOf("ATG", startIndex) + myGene.length();
           if (myGene.isEmpty()) break;
           count ++;
        }
       return count;
    }
    public void testPrintAllGenes(){
    //case 1 multiple valid ATGs and stop codons
    String testdna = "xxxATGAAATAAyyy";
    printAllGenes(testdna);
    //case 2 multiple ATGs, but no valid stop codons
    testdna = "xxxATGyyyTAAxATGgggTAGaaATGxxxTGAxxxxATGxxxTAAxxx";
    printAllGenes(testdna);
    //case 3 multiple ATGs and stop codons, but only one valid stop codons
    testdna = "ATGyyyxATGTAATAAxxTGAxxx";
    printAllGenes(testdna);
    //case 4 no ATGs
    testdna = "xxxxxkdkdfkdfkljdakljfds";
    printAllGenes(testdna);
    System.out.println("Test Complete");
}
    public void testCountGenes() {
        int testOut = countGenes("xxxATGAAATAAyyy");
        if (testOut != 1) System.out.println("Error test 1");
        System.out.println(testOut);
        //note to self: if nothing between ATG and TAA, program doesn't recognize gene.
        testOut = countGenes("xxxATGyyyTAAxATGgggTAGaaATGxxxTGAxxxxATGxxxTAAxxx");
        if (testOut != 4) System.out.println("Error test 2");
        System.out.println(testOut);
        testOut = countGenes("xxxxxkdkdfkdfkljdakljfds");
        if (testOut != 0) System.out.println("Error test 3");
        System.out.println(testOut);
        System.out.println("Test Complete");
    }

}
