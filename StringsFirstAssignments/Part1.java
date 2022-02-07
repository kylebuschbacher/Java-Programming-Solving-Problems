import edu.duke.*;
import java.io.File;
/**
 * Find a gene using the simplified algorithm
 * 
 * @author (Kyle) 
 * @version (2/6/22)
 */
public class Part1 {
    public String findSimpleGene (String dna) {
        //find the index position of the start codon "ATG"
        int startCodon = dna.indexOf("ATG");
        //if no "ATG", return empty string
        if (startCodon == -1) {
            return "";
        }
        //find the index position of the first stop codon "TAA"
        int stopCodon = dna.indexOf("TAA", startCodon);
        //if no "TAA", return empty string
        if (stopCodon == -1) {
            return ""
            }
        //if distance between "ATG and "TAA" is multiple of 3
        if ((stopCodon - startCodon) % 3 == 0) {
            //return the string between startCodon and stopCodon
        }
        //else, return empty String:
        //need to update return statement
    return "";}

}
