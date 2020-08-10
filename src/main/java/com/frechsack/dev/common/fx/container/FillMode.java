package com.frechsack.dev.common.fx.container;

import javafx.geometry.Orientation;

/**
 * Indicates how a Node is resized in a {@code TablePane} cell.<br>
 * A Node that is {@link #HORIZONTAL} will stretch the whole cell size horizontal and a {@link #VERTICAL} will stretch the whole cell size vertical.<br>
 * {@link #BOTH} combines theses two behaviours.<br>
 * If the {@code Node} is assigned to {@link #NONE} - the {@code Node} is sized in it's preferred width and height.
 *
 * @see TablePane
 */
public enum FillMode
{
    /**
     * Stretches a Node through the complete vertical space.
     */
    VERTICAL(false, true),
    /**
     * Stretches a Node through the complete horizontal space.
     */
    HORIZONTAL(true, false),

    /**
     * Stretches a Node through the complete horizontal and vertical space.
     */
    BOTH(true, true),

    /**
     * Does not stretch a Node. The preferred Node's size is used.
     */
    NONE(false, false);


    /*******************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/
    private final boolean isVertical;
    private final boolean isHorizontal;

    /*******************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    FillMode(boolean isHorizontal, boolean isVertical)
    {
        this.isHorizontal = isHorizontal;
        this.isVertical   = isVertical;
    }

    /**
     * Returns the {@code Orientations} that are contained in this {@code FillNode}.
     * @return
     */
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
