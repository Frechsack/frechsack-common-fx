package com.frechsack.dev.common.fx.container;

public class TablePaneConstraints implements TablePaneConstants, Cloneable
{
    private static TablePaneConstraints nullConstraints;

    public static TablePaneConstraints getNullConstraints()
    {
        if (nullConstraints == null) nullConstraints = new TablePaneConstraints(0, 0, 1, 1, 0, 0, 0, 0);
        return nullConstraints;
    }

    private int columnIndex;
    private int rowIndex;
    private int columnSpan;
    private int rowSpan;

    private int insetTop;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;

    private boolean isSizingVertical   = true;
    private boolean isSizingHorizontal = true;
    private boolean isAnchorLeft       = false;
    private boolean isAnchorRight      = false;
    private boolean isAnchorTop        = false;
    private boolean isAnchorBottom     = false;

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
        sizing(BOTH);
        anchor(CENTER);
    }

    public TablePaneConstraints(int columnIndex, int rowIndex, int columnSpan, int rowSpan, int insetTop, int insetRight, int insetBottom,
                                int insetLeft, byte sizing, byte anchor)
    {
        this.columnIndex = columnIndex;
        this.rowIndex    = rowIndex;
        this.columnSpan  = columnSpan;
        this.rowSpan     = rowSpan;
        this.insetTop    = insetTop;
        this.insetBottom = insetBottom;
        this.insetLeft   = insetLeft;
        this.insetRight  = insetRight;
        sizing(sizing);
        anchor(anchor);
    }

    public TablePaneConstraints(TablePaneConstraints copy)
    {
        columnIndex = copy.columnIndex;
        rowIndex    = copy.rowIndex;
        columnSpan  = copy.columnSpan;
        rowSpan     = copy.rowSpan;

        isAnchorBottom = copy.isAnchorBottom;
        isAnchorLeft   = copy.isAnchorLeft;
        isAnchorTop    = copy.isAnchorTop;
        isAnchorRight  = copy.isAnchorRight;

        insetTop    = copy.insetTop;
        insetBottom = copy.insetBottom;
        insetLeft   = copy.insetLeft;
        insetRight  = copy.insetRight;

        this.isSizingVertical   = copy.isSizingVertical;
        this.isSizingHorizontal = copy.isSizingHorizontal;
    }

    private void sizing(byte sizing)
    {
        if ((sizing & VERTICAL) == VERTICAL) isSizingVertical = true;
        if ((sizing & HORIZONTAL) == HORIZONTAL) isSizingHorizontal = true;
    }

    private void anchor(byte anchor)
    {
        if ((anchor & NORTH) == NORTH) isAnchorTop = true;
        if ((anchor & SOUTH) == SOUTH) isAnchorBottom = true;
        if ((anchor & EAST) == EAST) isAnchorRight = true;
        if ((anchor & WEST) == WEST) isAnchorLeft = true;

    }

    public byte getAnchor()
    {
        byte result = 0;
        if (isAnchorBottom) result += SOUTH;
        if (isAnchorTop) result += NORTH;
        if (isAnchorLeft) result += WEST;
        if (isAnchorRight) result += EAST;
        return result;
    }

    public byte getSizing()
    {
        byte result = 0;
        if (isSizingHorizontal) result += HORIZONTAL;
        if (isSizingVertical) result += VERTICAL;
        return result;
    }

    boolean isSizingBoth()
    {
        return isSizingHorizontal && isSizingVertical;
    }

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


    public boolean isSizingVertical()
    {
        return isSizingVertical;
    }


    public boolean isSizingHorizontal()
    {
        return isSizingHorizontal;
    }


    public boolean isAnchorLeft()
    {
        return isAnchorLeft;
    }


    public boolean isAnchorRight()
    {
        return isAnchorRight;
    }


    public boolean isAnchorTop()
    {
        return isAnchorTop;
    }


    public boolean isAnchorBottom()
    {
        return isAnchorBottom;
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


    public void setSizingVertical(boolean isSizingVertical)
    {
        this.isSizingVertical = isSizingVertical;
    }


    public void setSizingHorizontal(boolean isSizingHorizontal)
    {
        this.isSizingHorizontal = isSizingHorizontal;
    }


    public void setAnchorLeft(boolean isAnchorLeft)
    {
        this.isAnchorLeft = isAnchorLeft;
    }


    public void setAnchorRight(boolean isAnchorRight)
    {
        this.isAnchorRight = isAnchorRight;
    }


    public void setAnchorTop(boolean isAnchorTop)
    {
        this.isAnchorTop = isAnchorTop;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        super.clone();
        return new TablePaneConstraints(this);
    }
}
