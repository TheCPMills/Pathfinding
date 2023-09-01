package bezier;

import javax.vecmath.Point2f;

public class Bezier {
    private final Point2f startPoint, endPoint, controlPoint1, controlPoint2;

    public Bezier(Point2f startPoint, Point2f controlPoint1, Point2f controlPoint2, Point2f endPoint) {
        this.startPoint = startPoint;
        this.controlPoint1 = controlPoint1;
        this.controlPoint2 = controlPoint2;
        this.endPoint = endPoint;
    }

    public Point2f getPoint(float t) {
        float x = (float) (Math.pow(1 - t, 3) * startPoint.x + 3 * Math.pow(1 - t, 2) * t * controlPoint1.x + 3 * (1 - t) * Math.pow(t, 2) * controlPoint2.x + Math.pow(t, 3) * endPoint.x);
        float y = (float) (Math.pow(1 - t, 3) * startPoint.y + 3 * Math.pow(1 - t, 2) * t * controlPoint1.y + 3 * (1 - t) * Math.pow(t, 2) * controlPoint2.y + Math.pow(t, 3) * endPoint.y);
        return new Point2f(x, y);
    }
}
