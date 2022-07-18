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
        // Return the number of points in a shape
        int counter = 0;
        for (Point currPt : s.getPoints()) {
            ++counter;
        }
        return counter;
    }

    public double getAverageLength(Shape s) {
        // Return the avarage side length
        return (getPerimeter(s) / getNumPoints(s));
    }

    public double getLargestSide(Shape s) {
        // Returns the length of a largest side
        double largest = 0;
        double currLen = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            currLen = currPt.distance(prevPt);
            if (currLen > largest)
                largest = currLen;
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        // Returns the largest X coordinate of a shape
        double currX = 0;
        Point prevPt = s.getLastPoint();
        double largest = prevPt.getX();
        for (Point currPt : s.getPoints()) {
            currX = currPt.getX();
            if (currX > largest)
                largest = currX;
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Return the largest perimeter when multiple shapes given
        double largestPerim = 0;
        double currPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);                          // Print a filepath. Delete if unnecessary.
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currPerim = getPerimeter(s);
            System.out.println("Perimeter: " + currPerim);  // Print current perimeter. Delete if unnecessary.
            if (currPerim > largestPerim)
                largestPerim = currPerim;
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Return the filename with the largest perimeter of a shape
        File temp = null;
        double largestPerim = 0;
        double currPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);                          // Print a filepath. Delete if unnecessary.
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currPerim = getPerimeter(s);
            System.out.println("Perimeter: " + currPerim);  // Print current perimeter. Delete if unnecessary.
            if (currPerim > largestPerim) {
                temp = f;
                largestPerim = currPerim;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int numberOfPoints = getNumPoints(s);
        double length = getPerimeter(s);
        double avgSide = getAverageLength(s);
        double largestLen = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("Number of Points in a Shape: "
        + numberOfPoints + ";\nPerimeter = " + length +
        ";\nAverage Side Length = " + avgSide);
        System.out.println("Length of the Largest side = " + largestLen);
        System.out.println("Largest X coordinate of a shape = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
    }

    public void testFileWithLargestPerimeter() {
        String largest = getFileWithLargestPerimeter();
        System.out.println("File with the largest perimeter is: " + largest);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double perim = getPerimeter(triangle);
        System.out.println("perimeter = " + perim);
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
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
