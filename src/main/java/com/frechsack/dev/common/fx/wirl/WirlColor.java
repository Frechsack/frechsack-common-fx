package com.frechsack.dev.common.fx.wirl;

import javafx.scene.paint.Color;

/**
 * A {@code WirlColor} describes a css-value. The value of the color itself depends on the {@link WirlTheme}.
 * @see WirlTheme
 * @see Wirl
 */
public enum WirlColor
{
    BASE_DEFAULT("-wirl-base-default"),
    BORDER_DEFAULT("-wirl-border-default"),
    BORDER_HOVER("-wirl-border-hover"),
    BORDER_FOCUSED("-wirl-border-focused"),
    TEXT("-wirl-text"),
    ACCENT("-wirl-accent"),
    MARK("-wirl-mark"),
    BASE_TOP_PANE("-wirl-base-top-pane"),
    BASE_TOOL_TIP("-wirl-base-tool-tip"),
    BASE_MENU_BAR("-wirl-base-menu-bar"),
    BASE_INPUT("-wirl-base-input"),

    BASE_CONTEXT_MENU("-wirl-base-context-menu"),
    BORDER_CONTEXT_MENU("-wirl-border-context-menu"),

    BASE_DEFAULT_ITEM("-wirl-base-default-item"),
    BASE_FOCUSED_ITEM("-wirl-base-focused-item"),

    BASE_HOVER_BUTTON("-wirl-base-hover-button"),
    BASE_ARMED_BUTTON("-wirl-base-armed-button"),
    BORDER_ARMED_BUTTON("-wirl-border-armed-button");

    private final String property;

    WirlColor(String property)
    {
        this.property = property;
    }

    /**
     * Returns the name of the css-value.
     * @return The name.
     */
    public String getProperty()
    {
        return property;
    }

    /**
     * Returns the color value of this css-value, based on the given {@link WirlTheme}.
     * @param wirlTheme The {@code WirlTheme}.
     * @return The native color value.
     */
    public Color getColor(WirlTheme wirlTheme)
    {
        return wirlTheme.getColor(this);
    }

    /**
     * Returns the color value of this css-value in the {@link WirlTheme#DEFAULT} theme.
     * @return The native color value.
     */
    public Color getColor()
    {
        return getColor(WirlTheme.DEFAULT);
    }
}
