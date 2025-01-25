package Geometry;
import java.util.List;

/**
 * Represents a line segment defined by two points in a 2D plane.
 */
public class Line {
    private double x1, y1;
    private double x2, y2;
    private final double epsilon = 0.000001d;

    /**
     * Constructs a Geometry.Line object with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Constructs a Geometry.Line object with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        double distance = Math.pow(this.x1 - this.x2, 2) + Math.pow(this.y1 - this.y2, 2);
        return Math.sqrt(distance);
    }

    /**
     * Calculates the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.x1 + this.x2) / 2;
        double middleY = (this.y1 + this.y2) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return new Point(x1, y1);
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return new Point(x2, y2);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //check if one of the line is a point
        if (this.start().equals(this.end())) {
            if (other.start().equals(other.end())) {
                if (this.start().equals(other.start())) {
                    return true;
                }
                if (!this.start().equals(other.start())) {
                    return false;
                }
            }
            if (other.onLine(this.start())) {
                return true;
            }
        }
        if (other.start().equals(other.end())) {
            if (this.onLine(other.start())) {
                return true;
            }
        }

        //check if one of the point of a line is on the other line
        if (this.onLine(other.start()) || this.onLine(other.end())
                || other.onLine(this.start()) || other.onLine(this.end())) {
            return true;
        }

        double thisIncline = (this.y2 - this.y1) / (this.x2 - this.x1);
        double otherIncline = (other.y2 - other.y1) / (other.x2 - other.x1);

        //Check if the inclines are equal
        if (Math.abs(thisIncline - otherIncline) <= epsilon) {
            return false;
        }
        // Check if one of the inclines is infinity
        if (Math.abs(thisIncline) == Double.POSITIVE_INFINITY) {
            return this.checkThisInfinity(other, otherIncline);
        }
        if (Math.abs(otherIncline) == Double.POSITIVE_INFINITY) {
            return other.checkThisInfinity(this, thisIncline);
        }

        // Calculate the intersection point and check if it's in the range
        double x = ((-(otherIncline * other.x1)) + other.y1 + (thisIncline * this.x1) - this.y1)
                / (thisIncline - otherIncline);
        double y = otherIncline * x - otherIncline * other.x1 + other.y1;
        return (checkPointInRange(x, y, other));
    }

    /**
     * Checks if this line, with a vertical incline, intersects with another line.
     *
     * @param other        the other line to check intersection with
     * @param otherIncline the incline of the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean checkThisInfinity(Line other, double otherIncline) {
        double x = this.x1;
        double y = otherIncline * x - otherIncline * other.x1 + other.y1;
        return checkPointInRange(x, y, other);
    }

    /**
     * Checks if the given point (x, y) is within the range of the two lines.
     *
     * @param x     the x-coordinate of the point
     * @param y     the y-coordinate of the point
     * @param other the other line to compare with
     * @return true if the point is within the range of both lines, false otherwise
     */
    public boolean checkPointInRange(double x, double y, Line other) {
        return (x - Math.max(this.start().getX(), this.end().getX()) <= epsilon)
                && (x - Math.min(this.start().getX(), this.end().getX()) >= -epsilon)
                && (y - Math.min(this.start().getY(), this.end().getY()) >= -epsilon)
                && (y - Math.max(this.start().getY(), this.end().getY()) <= epsilon)
                && (x - Math.max(other.start().getX(), other.end().getX()) <= epsilon)
                && (x - Math.min(other.start().getX(), other.end().getX()) >= -epsilon)
                && (y - Math.min(other.start().getY(), other.end().getY()) >= -epsilon)
                && (y - Math.max(other.start().getY(), other.end().getY()) <= epsilon);
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first other line to check intersection with
     * @param other2 the second other line to check intersection with
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Calculates the intersection point with another line, if it exists.
     *
     * @param other the other line to calculate the intersection with
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        //Check if the lines are equals
        if (this.equals(other) && this.length() > epsilon) {
            return null;
        }
        //Check one of the start/end point of a line is the same that the start/end point of other line
        if (this.start().equals(other.start()) || this.start().equals(other.end())) {
            return this.start();
        }
        if (this.end().equals(other.start()) || this.end().equals(other.end())) {
            return this.end();
        }
        //Check if one of the start/end point is on the other line
        if (this.start().distance(this.end()) <= epsilon
                && checkPointInRange(this.start().getX(), this.start().getY(), other)) {
            return this.start();
        }
        if (other.start().distance(other.end()) <= epsilon
                && checkPointInRange(other.start().getX(), other.start().getY(), this)) {
            return other.start();
        }
        if (this.isIntersecting(other)) {
            double thisIncline = (this.y2 - this.y1) / (this.x2 - this.x1);
            double otherIncline = (other.y2 - other.y1) / (other.x2 - other.x1);
            // Check if one of the inclines is infinity
            if (Math.abs(thisIncline) == Double.POSITIVE_INFINITY) {
                if (this.checkThisInfinity(other, otherIncline)) {
                    double y = otherIncline * this.x1 - otherIncline * other.x1 + other.y1;
                    return new Point(this.x1, y);
                }
            }
            if (Math.abs(otherIncline) == Double.POSITIVE_INFINITY) {
                if (other.checkThisInfinity(this, thisIncline)) {
                    double y = thisIncline * other.x1 - thisIncline * this.x1 + this.y1;
                    return new Point(other.x1, y);
                }
            }

            // Calculate the x of the intersection point
            double x = ((-(otherIncline * other.x1)) + other.y1 + (thisIncline * this.x1) - this.y1)
                    / (thisIncline - otherIncline);
            double y = thisIncline * x - thisIncline * this.x1 + this.y1;
            if ((epsilon <= Math.max(this.x1, this.x2) - x)
                    && (epsilon >= Math.min(this.x1, this.x2) - x)
                    && (epsilon >= Math.min(this.y1, this.y2) - y)
                    && (epsilon <= Math.max(this.y1, this.y2) - y)) {
                return new Point(x, y);
            }
        }
        return null;
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start().equals(other.start()) || this.start().equals(other.end()))
                && (this.end().equals(other.start()) || this.end().equals(other.end()))) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a given point is on this line.
     *
     * @param p the point to check
     * @return true if the point is on this line, false otherwise
     */
    public boolean onLine(Point p) {
        return (p.distance(this.start()) + p.distance(this.end()) == this.length());
    }


    /**
     * Calculates the closest intersection point to the start of the line with a given rectangle.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point to the start of the line, or null if no intersection occurs
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        Point closest = null;
        for (Point intersection : intersections) {
            if ((closest == null) || intersection.distance(this.start()) < intersection.distance(closest)) {
                closest = intersection;
            }
        }
        return closest;
    }

}
