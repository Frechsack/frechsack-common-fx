package com.frechsack.dev.common.fx.container;

/**
 * Indicates how a {@link TablePaneDefinition} manages it's size.<br> {@link #AUTO_SIZE} indicates that the {@link TablePaneDefinition}  will
 * calculate it's size based on it's content.<br> {@link #ABSOLUTE} indicates that the {@link TablePaneDefinition} has a fix size in pixels.<br>
 * {@link #RELATIVE} indicates that the {@link TablePaneDefinition} will take the remaining space of the layout and add a multiplier (it's ) size to
 * that.
 * @see TablePane
 * @see TablePaneDefinition
 */
public enum SizeMode
{
    /**
     * Performs size calculations based on the content.
     */
    AUTO_SIZE,
    /**
     * A fix size in pixels.
     */
    ABSOLUTE,
    /**
     * A relative amount of the remaining space.
     */
    RELATIVE;
}
