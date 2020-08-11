package com.frechsack.dev.common.fx.wirl;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;

/**
 * A {@code WirlStyle} defines how a {@link Node} is drawn. Some {@code Nodes} support multiple visual appearances like {@link
 * javafx.scene.control.Button}.
 */
public enum WirlStyle
{
    /**
     * Indicates that a {@code Node} is painted flat.
     */
    FLAT("flat", false),

    /**
     * Indicates that a {@code Node} is a top-pane. A top-pane has a different background color, compared to 'normal' panes.
     */
    TOP_PANE("top-pane", true),
    /**
     * Indicates that a {@code Node} is a pane.
     */
    PANE("pane", true),
    /**
     * Indicates that a {@code Node} is drawn like a {@link javafx.scene.control.Button}.
     */
    BUTTON_TYPE("button-type", true);

    private static final Class<?>[] flatStylables = {ButtonBase.class, ComboBoxBase.class, ChoiceBox.class, TextInputControl.class};
    private final        String     iD;
    private final        boolean    isValidID;

    WirlStyle(String iD, boolean isValidID)
    {
        this.iD        = iD;
        this.isValidID = isValidID;
    }

    /**
     * Checks if the given {@link Node} is supported by this {@code WirlStyle}.
     *
     * @param n The {@code Node}.
     * @return True if it is, else false.
     */
    public boolean isSupportedNode(Node n)
    {
        if (this == WirlStyle.FLAT)
        {
            for (Class<?> clazz : flatStylables)
                if (clazz.isAssignableFrom(n.getClass()))
                    return true;
            return false;
        }
        return true;
    }

    /**
     * Returns the css-style-id for the given node with this {@code WirlStyle}.
     *
     * @param n The {@code Node}.
     * @return The css-style-id.
     */
    String getNodeStyleID(Node n)
    {
        if (isValidID)
            return iD;
        return iD + "-" + n.getStyleClass().get(0);
    }
}
