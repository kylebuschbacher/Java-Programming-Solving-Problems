import edu.duke.*;
import java.io.File;
/**
 * Write a program that reads the lines from the file at this URL location, http://www.dukelearntoprogram.com/course2/data/manylinks.html, 
 * and prints each URL on the page that is a link to youtube.com.
 * 
 * @author Kyle
 * @version 2/11/2022
 */
public class Part4 {
    public void findYoutube() {
        //open link
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.lines()) { //read a line
            //create a temp string to convert to lowercase
            String sTemp = s.toLowerCase();
            //look to see if the line contains youtube
            int startIndex = sTemp.indexOf("youtube.com");
            if (startIndex != -1) {
                //find the first double quote:
                //System.out.println("DNA is " + s); //for testing
                int firstQuoteIndex = s.lastIndexOf("\"", startIndex);
                int lastQuoteIndex = s.indexOf("\"", startIndex);
                System.out.println(s.substring(firstQuoteIndex+1, lastQuoteIndex));
            }
            
            
            //it it is, print url

        }
    }
    
    
//open link
 
 
//if it is, print the url
}
