import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
          for (Point currPt : s.getPoints()) {
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        double points = getNumPoints(s); 
        double average = length/points;
        return average;
    }

    public double getLargestSide(Shape s) {
        double LargestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint(); //not sure this initialization is necessary, but whatever.
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            // need an if statement:
            if (currDist > LargestSide) {
                LargestSide = currDist;
            }
           
            prevPt = currPt;
        
            }
            return LargestSide;
    
}

    public double getLargestX(Shape s) {
       
        double LargestX = 0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            //get X
            double x = currPt.getX();
            if (x > LargestX) {
                LargestX = x;
        }
        
    }
    return LargestX;
}

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double average = getAverageLength(s);
        double longest = getLargestSide(s);
        double BigX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + points);
        System.out.println("average length = " + average);
        System.out.println("longest side = " + longest);
        System.out.println("Largest X = " + BigX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
