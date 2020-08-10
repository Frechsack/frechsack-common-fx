package com.frechsack.dev.common.fx.container;

import javafx.geometry.Orientation;

public enum FillMode
{
    VERTICAL(false, true), HORIZONTAL(true, false), BOTH(true, true), NONE(false, false);

    private final boolean isVertical;
    private final boolean isHorizontal;

    FillMode(boolean isHorizontal, boolean isVertical)
    {
        this.isHorizontal = isHorizontal;
        this.isVertical   = isVertical;
    }

    public Orientation[] getOrientations()
    {
        if (isVertical && isHorizontal) return new Orientation[]{Orientation.HORIZONTAL, Orientation.VERTICAL};
        else if (isVertical) return new Orientation[]{Orientation.VERTICAL};
        else if (isHorizontal) return new Orientation[]{Orientation.HORIZONTAL};
        return new Orientation[0];
    }

    public boolean isVertical()
    {
        return isVertical;
    }

    public boolean isHorizontal()
    {
        return isHorizontal;
    }
}
