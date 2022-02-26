
/**
 * Write a description of Part2 here.
 * Finds the ratio of C's and G's to the length of an input string
   * 
 * @author Kyle 
 * @version 2/26/22
 */
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
}
