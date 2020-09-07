package com.frechsack.dev.common.fx.stain;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Provides static methods for the Stain look and feel.
 */
public class Stain
{
    private static Map<Scene, StainTheme> themeMap;

    private static void requireThemeMap()
    {
        if (themeMap == null) themeMap = new HashMap<>(1);
    }

    private static String toCssColor(Color color)
    {
        return "rgb(" + (255d * color.getRed()) + "," + (255d * color.getGreen()) + "," + (255d * color.getBlue()) + ")";
    }

    /**
     * Modifies the value of the given {@link StainColor} at the specified Node. The modification is done by {@link Node#setStyle(String)}.
     *
     * @param node       The Node.
     * @param stainColor The StainColor that will be modified.
     * @param color      The new value of the specified StainColor. If null is passed, the scene's default value will be set.
     */
    public static void setColor(Node node, StainColor stainColor, Color color)
    {
        if (color == null) color = Objects.requireNonNullElse(getTheme(node.getScene()), StainTheme.DEFAULT).getColor(stainColor);
        String style = node.getStyle();
        if (style == null || style.isEmpty()) style = stainColor.getProperty() + ":" + toCssColor(color) + ";";
        else style += "\"" + stainColor.getProperty() + ":" + toCssColor(color) + ";";
        node.setStyle(style);
    }

    /**
     * Modifies the background color of the given Node. Some Nodes do not support a background color.
     *
     * @param node  The Node.
     * @param color The background color.
     */
    public static void setBackgroundColor(Node node, Color color)
    {
        String style = node.getStyle();
        if (style == null || style.isEmpty()) style = "-base:" + toCssColor(color) + ";";
        else style += "\"-base:" + toCssColor(color) + ";";
        node.setStyle(style);
    }

    /**
     * Modifies the border color of the given Node. Some Nodes do not support a border color.
     *
     * @param node  The Node.
     * @param color The border color.
     */
    public static void setBorderColor(Node node, Color color)
    {
        String style = node.getStyle();
        if (style == null || style.isEmpty()) style = "-border:" + toCssColor(color) + ";";
        else style += "\"-border:" + toCssColor(color) + ";";
        node.setStyle(style);
    }

    /**
     * Returns the {@link StainTheme} of the given {@link Scene}.
     *
     * @param scene The Scene.
     * @return The registered StainTheme. If no theme is registered, null is returned.
     */
    public static StainTheme getTheme(Scene scene)
    {
        if (themeMap == null) return null;
        return themeMap.get(scene);
    }

    /**
     * Adds the specified {@link StainTheme} to the {@link Scene}.
     *
     * @param scene The Scene.
     * @param theme The StainTheme. If null is passed, any existing {@link StainTheme} is removed from the Scene.
     */
    public static void put(Scene scene, StainTheme theme)
    {
        remove(scene);
        if (theme == null) return;
        requireThemeMap();
        scene.getStylesheets().add(theme.getStyleSheetURL().toExternalForm());
        themeMap.put(scene, theme);
    }

    /**
     * Removes any existing {@link StainTheme} from the specified {@link Scene}.
     * @param scene The Scene.
     */
    public static void remove(Scene scene)
    {
        if (themeMap == null) return;
        StainTheme currentTheme = themeMap.get(scene);
        if (currentTheme != null) scene.getStylesheets().remove(currentTheme.getStyleSheetURL().toExternalForm());
    }
}
