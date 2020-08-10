package com.frechsack.dev.common.fx.container;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class TablePaneConstraints implements Cloneable
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

    private FillMode fillMode = FillMode.BOTH;
    private Pos      anchor   = Pos.CENTER;

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

    public Pos getAnchor()
    {
        return anchor;
    }

    public FillMode getFillMode()
    {
        return fillMode;
    }

    public boolean isSizingBoth()
    {
        return fillMode == FillMode.BOTH;
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
        return fillMode.isVertical();
    }

    public boolean isSizingHorizontal()
    {
        return fillMode.isHorizontal();
    }

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
