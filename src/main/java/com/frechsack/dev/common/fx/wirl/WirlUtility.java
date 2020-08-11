package com.frechsack.dev.common.fx.wirl;

import javafx.scene.paint.Color;

/**
 * Utility Methods for the Wirl look and feel.
 *
 */
public class WirlUtility
{
    /**
     * Converts a Color to a css parsable value. {@link Color#RED} becomes to 'rgb(255,0,0)'
     * @param c The color.
     * @return The css value.
     */
    static String colorToCssRgb(Color c)
    {
        int base = 255;
        int r    = (int) (base * c.getRed());
        int g    = (int) (base * c.getGreen());
        int b    = (int) (base * c.getBlue());
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    /**
     * Returns a {@link WirlColor} that has the same name, as the given property.
     * @param property The color name.
     * @return An matching {@code WirlColor} or {@code null} if no matching color was found.
     */
    static WirlColor wirlColorByProperty(String property)
    {
        for (WirlColor wirlcolor : WirlColor.values())
            if (wirlcolor.getProperty().equals(property))
                return wirlcolor;
        return null;
    }
}
