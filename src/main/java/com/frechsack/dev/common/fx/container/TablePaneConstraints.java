package com.frechsack.dev.common.fx.container;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

/**
 * {@link TablePaneConstraints} define how a Node's layout bounds are calculated in a {@link TablePane}.<br> The {@code TablePaneConstraints} specify
 * the layout location (row and column index) and the size (row and column span).<br> The insets in the cell, the anchor and a {@link FillMode} are
 * provided as well.
 * @see FillMode
 * @see Pos
 * @see HPos
 * @see VPos
 */
public class TablePaneConstraints implements Cloneable
{
    /*******************************************************************************
     *                                                                             *
     * Static fields.
     *                                                                             *
     ******************************************************************************/
    private static TablePaneConstraints nullConstraints;

    /**
     * Returns a set of {@code TablePaneConstraints} to avoid a {@code NullPointerException}
     *
     * @return Some {@code TablePaneConstraints}.
     */
    static TablePaneConstraints getNullConstraints()
    {
        if (nullConstraints == null) nullConstraints = new TablePaneConstraints(0, 0, 1, 1, 0, 0, 0, 0);
        return nullConstraints;
    }

    /* *****************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/
    private int columnIndex;
    private int rowIndex;
    private int columnSpan;
    private int rowSpan;

    private int insetTop;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;

    private FillMode fillMode = FillMode.BOTH;
    private Pos      anchor   = Pos.CENTER;

    /* *****************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    /**
     * Creates a new instance of {@code TablePaneConstraints} with an anchor in the center and a {@link FillMode#BOTH} fill mode.
     *
     * @param columnIndex The column index (x-value).
     * @param rowIndex    The row index (y-value).
     * @param columnSpan  The column span (width).
     * @param rowSpan     The row span (height).
     * @param insetTop    The top inset.
     * @param insetRight  The right inset.
     * @param insetBottom The bottom inset.
     * @param insetLeft   The left inset.
     */
    public TablePaneConstraints(int columnIndex, int rowIndex, int columnSpan, int rowSpan, int insetTop, int insetRight, int insetBottom,
                                int insetLeft)
    {
        this.columnIndex = columnIndex;
        this.rowIndex    = rowIndex;
        this.columnSpan  = columnSpan;
        this.rowSpan     = rowSpan;
        this.insetTop    = insetTop;
        this.insetBottom = insetBottom;
        this.insetLeft   = insetLeft;
        this.insetRight  = insetRight;
    }

    /**
     * Creates a new instance of {@code TablePaneConstraints}.
     *
     * @param columnIndex The column index (x-value).
     * @param rowIndex    The row index (y-value).
     * @param columnSpan  The column span (width).
     * @param rowSpan     The row span (height).
     * @param insetTop    The top inset.
     * @param insetRight  The right inset.
     * @param insetBottom The bottom inset.
     * @param insetLeft   The left inset.
     * @param fillMode    The {@code FillMode}.
     * @param anchor      The anchor.
     * @see Pos
     * @see FillMode
     */
    public TablePaneConstraints(int columnIndex, int rowIndex, int columnSpan, int rowSpan, int insetTop, int insetRight, int insetBottom,
                                int insetLeft, FillMode fillMode, Pos anchor)
    {
        this.columnIndex = columnIndex;
        this.rowIndex    = rowIndex;
        this.columnSpan  = columnSpan;
        this.rowSpan     = rowSpan;
        this.insetTop    = insetTop;
        this.insetBottom = insetBottom;
        this.insetLeft   = insetLeft;
        this.insetRight  = insetRight;
        this.anchor      = anchor;
    }

    /**
     * Creates a new instance of {@code TablePaneConstraints}.
     *
     * @param columnIndex The column index (x-value).
     * @param rowIndex    The row index (y-value).
     * @param columnSpan  The column span (width).
     * @param rowSpan     The row span (height).
     * @param insets      The insets. The value is applied as the top, right, bottom and left value.
     * @param fillMode    The {@code FillMode} for the {@code Node}.
     * @param anchor      The anchor for the {@code Node}, if the {@code Node} does not consume the whole space.
     * @see Pos
     * @see FillMode
     */
    public TablePaneConstraints(int columnIndex, int rowIndex, int columnSpan, int rowSpan, int insets, FillMode fillMode, Pos anchor)
    {
        this.columnIndex = columnIndex;
        this.rowIndex    = rowIndex;
        this.columnSpan  = columnSpan;
        this.rowSpan     = rowSpan;
        this.insetTop    = insets;
        this.insetBottom = insets;
        this.insetLeft   = insets;
        this.insetRight  = insets;
        this.fillMode    = fillMode;
        this.anchor      = anchor;
    }

    /**
     * Creates a new instance of {@code TablePaneConstraints} with an anchor in the center.
     *
     * @param columnIndex The column index (x-value).
     * @param rowIndex    The row index (y-value).
     * @param columnSpan  The column span (width).
     * @param rowSpan     The row span (height).
     * @param insets      The insets. The value is applied as the top, right, bottom and left value.
     * @param fillMode    The {@code FillMode} for the Node.
     * @see FillMode
     */
    public TablePaneConstraints(int columnIndex, int rowIndex, int columnSpan, int rowSpan, int insets, FillMode fillMode)
    {
        this.columnIndex = columnIndex;
        this.rowIndex    = rowIndex;
        this.columnSpan  = columnSpan;
        this.rowSpan     = rowSpan;
        this.insetTop    = insets;
        this.insetBottom = insets;
        this.insetLeft   = insets;
        this.insetRight  = insets;
        this.fillMode    = fillMode;
    }

    /**
     * Creates a new instance of {@code TablePaneConstraints}. The created Object is a deep copy of the given {@code TablePaneConstraints}.
     *
     * @param copy The base.
     */
    public TablePaneConstraints(TablePaneConstraints copy)
    {
        columnIndex = copy.columnIndex;
        rowIndex    = copy.rowIndex;
        columnSpan  = copy.columnSpan;
        rowSpan     = copy.rowSpan;
        anchor      = copy.anchor;
        fillMode    = copy.fillMode;
        insetTop    = copy.insetTop;
        insetBottom = copy.insetBottom;
        insetLeft   = copy.insetLeft;
        insetRight  = copy.insetRight;
    }

    // Sizing

    public FillMode getFillMode()
    {
        return fillMode;
    }

    public void setFillMode(FillMode fillMode)
    {
        this.fillMode = fillMode;
    }

    public boolean isSizingBoth()
    {
        return fillMode == FillMode.BOTH;
    }

    public boolean isSizingVertical()
    {
        return fillMode.isVertical();
    }

    public boolean isSizingHorizontal()
    {
        return fillMode.isHorizontal();
    }

    // Bounds

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public int getColumnSpan()
    {
        return columnSpan;
    }

    public int getRowSpan()
    {
        return rowSpan;
    }

    public void setColumnIndex(int columnIndex)
    {
        this.columnIndex = columnIndex;
    }

    public void setRowIndex(int rowIndex)
    {
        this.rowIndex = rowIndex;
    }

    public void setColumnSpan(int columnSpan)
    {
        this.columnSpan = columnSpan;
    }

    public void setRowSpan(int rowSpan)
    {
        this.rowSpan = rowSpan;
    }

    // Inset

    public int getInsetTop()
    {
        return insetTop;
    }

    public int getInsetBottom()
    {
        return insetBottom;
    }

    public int getInsetLeft()
    {
        return insetLeft;
    }

    public int getInsetRight()
    {
        return insetRight;
    }

    public void setInsetTop(int insetTop)
    {
        this.insetTop = insetTop;
    }

    public void setInsetBottom(int insetBottom)
    {
        this.insetBottom = insetBottom;
    }

    public void setInsetLeft(int insetLeft)
    {
        this.insetLeft = insetLeft;
    }

    public void setInsetRight(int insetRight)
    {
        this.insetRight = insetRight;
    }

    // Anchor

    public boolean isAnchorLeft()
    {
        return anchor.getHpos() == HPos.LEFT;
    }

    public boolean isAnchorRight()
    {
        return anchor.getHpos() == HPos.RIGHT;
    }

    public boolean isAnchorTop()
    {
        return anchor.getVpos() == VPos.TOP;
    }

    public boolean isAnchorBottom()
    {
        return anchor.getVpos() == VPos.BOTTOM;
    }

    public void setAnchor(Pos anchor)
    {
        this.anchor = anchor;
    }

    public Pos getAnchor()
    {
        return anchor;
    }

    @Override
    public String toString()
    {
        return "TablePaneConstraints{" +
                "columnIndex=" + columnIndex +
                ", rowIndex=" + rowIndex +
                ", columnSpan=" + columnSpan +
                ", rowSpan=" + rowSpan +
                ", insetTop=" + insetTop +
                ", insetBottom=" + insetBottom +
                ", insetLeft=" + insetLeft +
                ", insetRight=" + insetRight +
                ", fillMode=" + fillMode +
                ", anchor=" + anchor +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        super.clone();
        return new TablePaneConstraints(this);
    }
}
