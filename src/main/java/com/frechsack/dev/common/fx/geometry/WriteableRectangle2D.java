package com.frechsack.dev.common.fx.geometry;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.Objects;

/**
 * An 2D Rectangle that describes an area with a location (x and Y) and a dimension (width and height).
 */
public class WriteableRectangle2D
{
    /* ******************************************************************************
     *                                                                             *
     * Static fields
     *                                                                             *
     ******************************************************************************/
    /**
     * An immutable instance of WriteableRectangle2D with all values equal to zero.
     */
    public static final WriteableRectangle2D EMPTY = new WriteableRectangle2D(0, 0, 0, 0)
    {
        @Override
        public void setMinY(double minY)
        {
        }

        @Override
        public void setHeight(double height)
        {
        }

        @Override
        public void setMinX(double minX)
        {
        }

        @Override
        public void setWidth(double width)
        {
        }
    };

    /*******************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/
    private double minX;

    public void setMinX(double minX)
    {
        this.minX = minX;
        calculateMaxX();
    }

    public double getMinX()
    {
        return minX;
    }

    private double minY;

    public void setMinY(double minY)
    {
        this.minY = minY;
        calculateMaxY();
    }

    public double getMinY()
    {
        return minY;
    }

    private double maxX;

    public double getMaxX()
    {
        return maxX;
    }

    private double maxY;

    public double getMaxY()
    {
        return maxY;
    }

    private double width;

    public void setWidth(double width)
    {

        this.width = width;
        calculateMaxX();
    }

    public double getWidth()
    {
        return width;
    }

    private double height;

    public void setHeight(double height)
    {
        this.height = height;
        calculateMaxY();
    }

    public double getHeight()
    {
        return height;
    }

    /* ******************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/

    /**
     * Creates a new WriteableRectangle2D based on the bounds of a Rectangle2D.
     *
     * @param rectangle2D The Rectangle2D.
     */
    public WriteableRectangle2D(Rectangle2D rectangle2D)
    {
        this.minX   = rectangle2D.getMinX();
        this.minY   = rectangle2D.getMinY();
        this.width  = rectangle2D.getWidth();
        this.height = rectangle2D.getHeight();
        this.maxX   = rectangle2D.getMaxX();
        this.maxY   = rectangle2D.getMaxY();
    }

    /**
     * Creates a new WriteableRectangle2D based on the given bounds.
     *
     * @param minX   The X value.
     * @param minY   The Y value.
     * @param width  The width.
     * @param height The height.
     */
    public WriteableRectangle2D(double minX, double minY, double width, double height)
    {
        this.minX   = minX;
        this.minY   = minY;
        this.width  = width;
        this.height = height;
        calculateMaxX();
        calculateMaxY();
    }

    /**
     * Checks if this rectangle contains the given Point2D.
     *
     * @param p The Point2D.
     * @return True if the Point2D is inside the rectangle bounds, else false.
     */
    public boolean contains(Point2D p)
    {
        if (p == null) return false;
        return contains(p.getX(), p.getY());
    }

    /**
     * Checks if this rectangle contains the given Rectangle2D.
     *
     * @param r The Rectangle2D.
     * @return True if the Rectangle2D is inside this WriteableRectangle2D, else false.
     */
    public boolean contains(Rectangle2D r)
    {
        if (r == null) return false;
        return contains(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
    }

    /**
     * Checks if this rectangle contains the given WriteableRectangle2D.
     *
     * @param r The WriteableRectangle2D.
     * @return True if the WriteableRectangle2D is inside this WriteableRectangle2D, else false.
     */
    public boolean contains(WriteableRectangle2D r)
    {
        if (r == null) return false;
        return contains(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
    }

    /**
     * Checks if the given bounds are inside this WriteableRectangle2D bounds.
     *
     * @param x The X value.
     * @param y The Y value.
     * @param w The width.
     * @param h The height.
     * @return True if the bounds lay inside this WriteableRectangle2D, else false.
     */
    public boolean contains(double x, double y, double w, double h)
    {
        return x >= minX && y >= minY && w <= maxX - x && h <= maxY - y;
    }

    /**
     * Checks if the given point is inside this WriteableRectangle2D bounds.
     *
     * @param x The X value.
     * @param y The Y value.
     * @return True if the point is inside this WriteableRectangle2D, else false.
     */
    public boolean contains(double x, double y)
    {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    /**
     * Checks if the bounds of a Rectangle2D intersect with the bounds of this WriteableRectangle2D.
     *
     * @param r The Rectangle2D.
     * @return True if the bounds intersect, else false.
     */
    public boolean intersects(Rectangle2D r)
    {
        if (r == null) return false;
        return intersects(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
    }

    /**
     * Checks if the bounds of a WriteableRectangle2D intersect with the bounds of this WriteableRectangle2D.
     *
     * @param r The WriteableRectangle2D.
     * @return True if the bounds intersect, else false.
     */
    public boolean intersects(WriteableRectangle2D r)
    {
        if (r == null) return false;
        return intersects(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
    }
    /**
     * Sets the bounds of this WriteableRectangle2D.
     *
     * @param minX   The X value.
     * @param minY   The Y value.
     * @param width  The width.
     * @param height The height.
     */
    public void setBounds( double minX, double minY, double width, double height)
    {
        this.minX   = minX;
        this.minY   = minY;
        this.width  = width;
        this.height = height;
        calculateMaxX();
        calculateMaxY();
    }

    /**
     * Checks if the given bounds  intersect with the bounds of this WriteableRectangle2D.
     *
     * @param x The X value.
     * @param y The Y value.
     * @param w The width.
     * @param h The height.
     * @return True if the bounds intersect, else false.
     */
    public boolean intersects(double x, double y, double w, double h)
    {
        return x < maxX && y < maxY && x + w > minX && y + h > minY;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof WriteableRectangle2D)) return false;
        WriteableRectangle2D that = (WriteableRectangle2D) o;
        return that.minX == minX && that.minY == minY && that.width == width && that.height == height;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(minX, minY, maxX, maxY, width, height);
    }

    private void calculateMaxX()
    {
        this.maxX = minX + width;
    }

    private void calculateMaxY()
    {
        this.maxY = minY + height;
    }

    /**
     * Converts this WriteableRectangle2D to a non writeable instance of Rectangle2D.
     *
     * @return A Rectangle2D with the same bounds as this WriteableRectangle2D.
     */
    public Rectangle2D toRectangle2D()
    {
        return new Rectangle2D(minX, minY, width, height);
    }

    @Override
    public String toString()
    {
        return "WriteableRectangle2D{" +
                "minX=" + minX +
                ", minY=" + minY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}
