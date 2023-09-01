package bezier;


import javax.vecmath.Point2f;

public class ClosestPointOnBezier {
    public static void main(String[] args) {
        System.out.println(closestPoint(new Point2f(2, 2), new Bezier(new Point2f(-2,0), new Point2f(-1,1), new Point2f(1,3), new Point2f(2,4))));
    }

    private static float closestPoint(Point2f point, Bezier curve) {
        float scans = 25;
        int mindex = 0;
        float min = Float.POSITIVE_INFINITY;

        for (int i = 0; i <= scans; i++) {
            Point2f p = curve.getPoint(i / scans);
            float d2 = distanceSquared(point, p);
            if (d2 < min) {
                min = d2;
                mindex = i;
            }
        }
        float t0 = Math.max((mindex - 1) / scans, 0);
        float t1 = Math.min((mindex + 1) / scans, 1);
        return localMinimum(t0, t1, point, curve, 0.0001f);
    }

    private static float localMinimum(float minX, float maxX, Point2f point, Bezier curve, float tolerance) {
        float m = minX, n = maxX, k = 0;
        while ((n - m) > tolerance) {
            k = (m + n) / 2;
            if (d2ForT(k - tolerance, point, curve) < d2ForT(k + tolerance, point, curve)) {
                n = k;
            } else {
                m = k;
            }
        }
        return k;
    }

    private static float distanceSquared(Point2f p1, Point2f p2) {
        float dx = p1.x - p2.x;
        float dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }
    
    private static float d2ForT(float t, Point2f point, Bezier curve) {
        return distanceSquared(point, curve.getPoint(t));
    }
}