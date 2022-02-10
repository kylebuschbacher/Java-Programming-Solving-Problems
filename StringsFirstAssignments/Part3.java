
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
    //is this the right way to declear a boolean method?
        //return true if string a appears at least twice in stringb, else return false.
        //0. convert both strings to uppercase:
        stringa = stringa.toUpperCase();
        stringb = stringb.toUpperCase();
        //1. find stringa in stringb once
        int startIndex = stringb.indexOf(stringa); 
        //if not there, return false:
        if (startIndex == -1){
            return false;
        }
        //2. find stringa again past the startindex+length of first string
        int secondIndex = stringb.indexOf(stringa, startIndex + stringa.length());
        //2a. if not there, return false.
        if (secondIndex == -1){
            return false;
        }
        //2b. if there, return true.
        else {
        return true;
    }
    }
    
    public String lastPart(String stringa, String stringb) {
        //0. convert both strings to uppercase:
        stringa = stringa.toUpperCase();
        stringb = stringb.toUpperCase();
        //find first occurance of string a in string b
        int startIndex = stringb.indexOf(stringa); 
        //if not there, return string b:
        if (startIndex == -1){
            return stringb;
        }
       
        //else return stringb immediately following where string a is.
        else {
            return stringb.substring(startIndex + stringa.length());
        }
    }
    
    public void testing() {
        System.out.println(twoOccurrences("atg", "atgatgatg"));
        System.out.println(twoOccurrences("atg", "atgatatattg"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}
