package Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rectangle in a 2-dimensional space.
 * It is defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;
    private Line[] edges = new Line[4];

    /**
     * Constructs a new rectangle with the specified upper-left corner,
     * width, and height.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        // Create edges
        edges[0] = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY())); // upper edge
        edges[1] = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height)); // lower edge
        edges[2] = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height)); // right edge
        edges[3] = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height)); // left edge
    }

    /**
     * Sets the upper-left point of the rectangle.
     *
     * @param upperLeft the new upper-left point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Returns a list of intersection points of this rectangle with the specified line.
     *
     * @param line the line to intersect with this rectangle
     * @return a list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        for (Line edge : edges) {
            if (line.isIntersecting(edge)) {
                Point intersection = line.intersectionWith(edge);
                if (intersection != null && !intersections.contains(intersection)) {
                    intersections.add(intersection);
                }
            }
        }
        return intersections;
    }

    /**
     * Returns the width of this rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of this rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left corner point of this rectangle.
     *
     * @return the upper-left corner point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns the array of edges of this rectangle.
     *
     * @return the array of edges
     */
    public Line[] getEdges() {
        return edges;
    }

    /**
     * Sets the edges of this rectangle to the given array of edges.
     *
     * @param edges the array of edges to set
     */
    public void setEdges(Line[] edges) {
        this.edges[0] = edges[0];
        this.edges[1] = edges[1];
        this.edges[2] = edges[2];
        this.edges[3] = edges[3];
    }

    /**
     * Checks if this rectangle is equal to the given rectangle.
     * Two rectangles are equal if their upper-left corners, width,
     * and height are all equal.
     *
     * @param rect the rectangle to compare with
     * @return true if the rectangles are equal, false otherwise
     */
    public boolean equals(Rectangle rect) {
        return upperLeft.equals(rect.upperLeft) && width == rect.width && height == rect.height;
    }
}
