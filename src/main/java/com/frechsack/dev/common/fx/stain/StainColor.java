package com.frechsack.dev.common.fx.stain;

import javafx.scene.paint.Color;

/**
 * A StainColor maps a Css rule and its name.
 */
public enum StainColor
{
    /**
     * The background color for root elements.
     * {@link javafx.scene.Scene}.
     */
    ROOT_BASE("-root-base"),
    /**
     * The background color for elements in front of root elements.
     */
    ROOT_FRONT("-root-front"),
    /**
     * The border color for focused borders.
     */
    BORDER_FOCUSED("-border-focused"),
    /**
     * The color for text.
     */
    TEXT("-text"),
    /**
     * The background color to indicate some progress.
     */
    PROGRESS("-progress"),

    /**
     * The background color of a mark, for example arrows.
     */
    MARK("-mark"),

    /**
     * The background color for controls.
     */
    CONTROL_BASE("-control-base"),
    /**
     * The background color for hovered controls.
     */
    CONTROL_BASE_HOVER("-control-base-hover"),
    /**
     * The background color for armed of pressed controls.
     */
    CONTROL_BASE_ARMED("-control-base-armed"),
    /**
     * The border color for controls.
     */
    CONTROL_BORDER("-control-border"),
    /**
     * The border color for hovered controls.
     */
    CONTROL_BORDER_HOVER("-control-border-hover"),
    /**
     * The border color for armed or pressed controls.
     */
    CONTROL_BORDER_ARMED("-control-border-armed"),
    /**
     * The background color for text controls.
     */
    TEXT_CONTROL_BASE("-text-control-base"),
    /**
     * The background color for selected or hovered items.
     */
    ITEM_SELECTED("-item-selected"),
    /**
     * The background color for focused and selected or hovered  items.
     */
    ITEM_FOCUSED("-item-focused");

    private final String property;

    StainColor(String property)
    {
        this.property = property;
    }

    /**
     * Returns the name of the css rule.
     * @return The name of the css rule.
     */
    public String getProperty()
    {
        return property;
    }

    /**
     * Returns the color of this {@link StainColor} relative to the theme.
     * @param theme The StainTheme.
     * @return The color of this StainColor.
     */
    public Color getColor(StainTheme theme)
    {
        return theme.getColor(this);
    }

    /**
     * Returns the default color of this {@link StainColor}.
     * @return The default color of this StainColor.
     */
    public Color getColor()
    {
        return getColor(StainTheme.DEFAULT);
    }
}
