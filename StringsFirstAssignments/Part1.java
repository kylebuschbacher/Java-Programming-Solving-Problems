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
        //check if distance between "ATG and "TAA" is multiple of 3
        if (stopCodon == -1) {
            return "";
            }
        //if distance between "ATG and "TAA" is multiple of 3
        if ((stopCodon - startCodon) % 3 == 0) {
            //return the string between startCodon and stopCodon
            return dna.substring(startCodon, stopCodon+3);
        }
        //else, return empty String:
        //need to update return statement
    return "";}
    
    public void testSimpleGene() {
        //test case 1: DNA with ATG, TAA and the substring
        //between them is a multiple of 3 (a gene)
        String dna = "AAAAATGGAGTAATTTTTT";
        System.out.println("DNA is " + dna);
        String result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        //test case 2: DNA with no “ATG”
        dna = "GAGTAA";
        System.out.println("DNA is " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        //test case 3: DNA with no "TAA"
        dna = "GAGTATGAAAAATGGGGGA";
        System.out.println("DNA is " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        //test case 4: DNA with ATG and TAA, gene not a multiple of 3
        dna = "GAGTATGAAAATAAGGGA";
        System.out.println("DNA is " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
    }

}

public class Part2 {
    public String findSimpleGene (String dna) {
        //want to find DNA strings regardless of case:
        dna = dna.toUpperCase() //strings are immutable, so I think this is right
        //find the index position of the start codon "ATG"
        int startCodon = dna.indexOf("ATG");
        //if no "ATG", return empty string
        if (startCodon == -1) {
            return "";
        }
        //find the index position of the first stop codon "TAA"
        int stopCodon = dna.indexOf("TAA", startCodon);
        //if no "TAA", return empty string
        //check if distance between "ATG and "TAA" is multiple of 3
        if (stopCodon == -1) {
            return "";
            }
        //if distance between "ATG and "TAA" is multiple of 3
        if ((stopCodon - startCodon) % 3 == 0) {
            //return the string between startCodon and stopCodon
            return dna.substring(startCodon, stopCodon+3);
        }
        //else, return empty String:
        //need to update return statement
    return "";}
    
    public void testSimpleGene() {
        //test case 1: DNA with ATG, TAA and the substring. also test case
        //between them is a multiple of 3 (a gene)
        String dna = "AAAAatgGAGTAATTTTTT";
        System.out.println("DNA is " + dna);
        String result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        //test case 2: DNA with no “ATG”
        dna = "GAGTAA";
        System.out.println("DNA is " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        //test case 3: DNA with no "TAA"
        dna = "GAGTATGAAAAATGGGGGA";
        System.out.println("DNA is " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        //test case 4: DNA with ATG and TAA, gene not a multiple of 3
        dna = "GAGTATGAAAATAAGGGA";
        System.out.println("DNA is " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
    }
}
