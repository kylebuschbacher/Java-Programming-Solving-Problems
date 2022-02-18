
/**
 * Find a gene in a strand of DNA where the stop codon could 
 * be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * 
 * @author Kyle
 * @version 2/13/2022
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //need a current index
        int currIndex = startIndex + 3; //don't think the +3 really matters
        //find first occurence of stopCodon that appears past startIndex
        //and is a multiple of 3 away from start index
        while (currIndex != 1) {
            currIndex = dna.indexOf(stopCodon, currIndex);
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
        }
        
        //if no such stopCodon, return the length of the dna strand
        return dna.length();
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
        num = findStopCodon(testDna, 0, "TAA");
        if (num != 28) System.out.println("error on 28");
        //case 3: look for TGA, but only TAA in dna
        test Dna = "xxxyyyATGxxxTGAxxxyyy"
        num = findStopCodon(testDna, 0, "TAA");
        if (num != -1) System.out.println("error on -1");

   }
   public String findGene(String dna) {
       //want to find DNA strings regardless of case:
       dna = dna.toUpperCase(); //strings are immutable, so I think this is right
       //find the index position of the start codon "ATG"
       int startCodon = dna.indexOf("ATG");
       //if no "ATG", return empty string
       if (startCodon == -1) {
            return "";
        }
       //Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
       //Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
       //Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
       //Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
       //If there is no valid stop codon and therefore no gene, return the empty string.
       return "";
    }
   public void testFindGene() {
    }
   public void printAllGenes(String dna) {
    }

}
