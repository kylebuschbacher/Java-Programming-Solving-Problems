
/**
 * Determine how many occurences of a string appear in another string
 * 
 * @author Kyle
 * @version 2/22/22
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int startIndex = 0;
        while (true) {
            int foundIndex = stringb.indexOf(stringa, startIndex);
            if (foundIndex == -1) break;
            count ++;
            startIndex = foundIndex + stringa.length();
        }
        return count;
    }
    public void testHowMany() {
        //case 1: only 1 stringa in stringb
        int testCount = howMany("ABA", "xxxABAyyy");
        if (testCount != 1) System.out.println("Error on 1");
        //case 2: 3 stringa in stringb, no overlap
        testCount = howMany("ABA", "xxxABAyyyABAabcABA");
        if (testCount !=3) System.out.println("Error on 3");
        //case 3: 2 stringa in stringb, with 3 overlaps
        testCount = howMany("ABA", "xxxABABAyyyABABAabcABA");
        if (testCount !=3) System.out.println("Error on 3");
        //case 4: 0 stringa in stringb
        testCount = howMany("ABA", "xxxgadgaedagaeageavvvv");
        if (testCount !=0) System.out.println("Error on 0");
        System.out.println("Test complete");
    }
}
