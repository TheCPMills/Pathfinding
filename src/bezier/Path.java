package bezier;

public class Path {
    private String path;
    
    public Path() {
        path = "";
    }

    public Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void addPath(String d) {
        this.path += d;
    }

    public void addMoveTo(double x, double y) {
        path += String.format("M %f %f ", x, y);
    }

    public void addRelativeMoveTo(double dx, double dy) {
        path += String.format("m %f %f ", dx, dy);
    }

    public void addLineTo(double x, double y) {
        path += String.format("L %f %f ", x, y);
    }

    public void addRelativeLineTo(double dx, double dy) {
        path += String.format("l %f %f ", dx, dy);
    }

    public void addHorizontalLineTo(double x) {
        path += String.format("H %f ", x);
    }

    public void addRelativeHorizontalLineTo(double dx) {
        path += String.format("h %f ", dx);
    }

    public void addVerticalLineTo(double y) {
        path += String.format("V %f ", y);
    }

    public void addRelativeVerticalLineTo(double dy) {
        path += String.format("v %f ", dy);
    }

    public void addCubicBezierTo(double x1, double y1, double x2, double y2, double x, double y) {
        path += String.format("C %f %f %f %f %f %f ", x1, y1, x2, y2, x, y);
    }

    public void addRelativeCubicBezierTo(double dx1, double dy1, double dx2, double dy2, double dx, double dy) {
        path += String.format("c %f %f %f %f %f %f ", dx1, dy1, dx2, dy2, dx, dy);
    }

    public void addSmoothCubicBezierTo(double x2, double y2, double x, double y) {
        path += String.format("S %f %f %f %f ", x2, y2, x, y);
    }

    public void addRelativeSmoothCubicBezierTo(double dx2, double dy2, double dx, double dy) {
        path += String.format("s %f %f %f %f ", dx2, dy2, dx, dy);
    }

    public void addQuadraticBezierTo(double x1, double y1, double x, double y) {
        path += String.format("Q %f %f %f %f ", x1, y1, x, y);
    }

    public void addRelativeQuadraticBezierTo(double dx1, double dy1, double dx, double dy) {
        path += String.format("q %f %f %f %f ", dx1, dy1, dx, dy);
    }

    public void addSmoothQuadraticBezierTo(double x, double y) {
        path += String.format("T %f %f ", x, y);
    }

    public void addRelativeSmoothQuadraticBezierTo(double dx, double dy) {
        path += String.format("t %f %f ", dx, dy);
    }

    public void addArcTo(double rx, double ry, double xAxisRotation, boolean largeArcFlag, boolean sweepFlag, double x, double y) {
        path += String.format("A %f %f %f %d %d %f %f ", rx, ry, xAxisRotation, largeArcFlag ? 1 : 0, sweepFlag ? 1 : 0, x, y);
    }

    public void addRelativeArcTo(double rx, double ry, double xAxisRotation, boolean largeArcFlag, boolean sweepFlag, double dx, double dy) {
        path += String.format("a %f %f %f %d %d %f %f ", rx, ry, xAxisRotation, largeArcFlag ? 1 : 0, sweepFlag ? 1 : 0, dx, dy);
    }

    public void addClosePath() {
        path += "Z ";
    }
}