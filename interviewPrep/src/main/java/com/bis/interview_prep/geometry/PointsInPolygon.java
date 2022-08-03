package com.bis.interview_prep.geometry;

/**
 * You are given a list of N points (x1, y1), (x2, y2), ...,
 * (xN, yN) representing a polygon. You can assume these points
 * are given in order; that is, you can construct the polygon by
 * connecting point 1 to point 2, point 2 to point 3, and so on,
 * finally looping around to connect point N to point 1.
 * <p>
 * Determine if a new point p lies inside this polygon. (If p is on the boundary of the polygon, you should return False).
 **/
public class PointsInPolygon {

    // Define Infinite (Using INT_MAX
    // caused overflow problems)
    static int INF = 10000;

    public static void main(String[] args) {
        Point polygon1[] = {new Point(0, 0),
                new Point(10, 0),
                new Point(10, 10),
                new Point(0, 10)};
        int n = polygon1.length;
        Point p = new Point(20, 20);
        if (isInside(polygon1, n, p)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        p = new Point(5, 5);
        if (isInside(polygon1, n, p)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        Point polygon2[] = {new Point(0, 0),
                new Point(5, 5), new Point(5, 0)};
        p = new Point(3, 3);
        n = polygon2.length;
        if (isInside(polygon2, n, p)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        p = new Point(5, 1);
        if (isInside(polygon2, n, p)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        p = new Point(8, 1);
        if (isInside(polygon2, n, p)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        Point polygon3[] = {new Point(0, 0),
                new Point(10, 0),
                new Point(10, 10),
                new Point(0, 10)};
        p = new Point(-1, 10);
        n = polygon3.length;
        if (isInside(polygon3, n, p)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    /**
     * This is to check if the point is lying on the inside or on the polygon
     **/
    static boolean onSegment(Point p, Point q, Point r) {
        if (q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y) &&
                q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x)
        ) {
            return true;
        }

        return false;
    }

    /**
     * To find the orientation of the ordered Triplet (p,q,r)
     * 0 -> p,q and r are collinear
     * 1 -> Clockwise
     * 2 -> Anti-Clockwise
     **/
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

        if (val == 0) {
            return 0; //collinear
        }

        return val > 0 ? 1 : 2; //clockwise or anticlockwise
    }

    /**
     * This function returns true if the line segment p1q1 and p2q2 intersects
     **/
    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        //Find the four orientations needed for general and special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        //general case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        //if p1, q1 and p2 are collinear and p2 lies on the segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }

        // p2, q2 and p1 are collinear and
        // p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }

        // p2, q2 and q1 are collinear and
        // q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }

        // Doesn't fall in any of the above cases
        return false;

    }

    // Returns true if the point p lies
    // inside the polygon[] with n vertices
    static boolean isInside(Point polygon[], int n, Point p) {
        // There must be at least 3 vertices in polygon[]
        if (n < 3) {
            return false;
        }

        // Create a point for line segment from p to infinite
        Point extreme = new Point(INF, p.y);

        // To count number of points in polygon
        // whose y-coordinate is equal to
        // y-coordinate of the point
        int decrease = 0;

        // Count intersections of the above line
        // with sides of polygon
        int count = 0, i = 0;
        do {
            int next = (i + 1) % n;

            if (polygon[i].y == p.y) decrease++;

            // Check if the line segment from 'p' to
            // 'extreme' intersects with the line
            // segment from 'polygon[i]' to 'polygon[next]'
            if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                // If the point 'p' is collinear with line
                // segment 'i-next', then check if it lies
                // on segment. If it lies, return true, otherwise false
                if (orientation(polygon[i], p, polygon[next]) == 0) {
                    return onSegment(polygon[i], p,
                            polygon[next]);
                }

                count++;
            }
            i = next;
        } while (i != 0);

        // Reduce the count by decrease amount
        // as these points would have been added twice
        count -= decrease;

        // Return true if count is odd, false otherwise
        return (count % 2 == 1); // Same as (count%2 == 1)
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
