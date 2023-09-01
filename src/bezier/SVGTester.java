package bezier;
import java.io.*;
import bezier.*;

public class SVGTester {
    public static void main(String[] args) throws IOException {
        SVGWriter writer = new SVGWriter(new File("test.svg"), 0.0, 0.0, 500.0, 500.0);
        writer.setFill("white");

        Path path = new Path();
        path.addMoveTo(0, 0);
        path.addLineTo(100, 0);
        path.addLineTo(100, 100);
        path.addCubicBezierTo(100, 125, 150, 75, 200, 150);
        path.addQuadraticBezierTo(150, 225, 100, 150);
        path.addArcTo(50, 50, 0, false, true, 50, 100);
        path.addClosePath();

        writer.writePath(path);
        writer.setFill("red");
        writer.writeEllipse(10.5, 9.5, 150, 150);
        writer.close();
    }
}