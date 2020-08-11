package com.frechsack.dev.common.fx.wirl;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Wirl} class contains static methods and functions to style a {@link Node} or a {@link Scene} with the {@code Wirl} look and feel.<br>
 *     The {@code Wirl} look and feel allows to set a {@link WirlTheme}, custom color values of a {@link WirlColor} and a {@link WirlStyle} for {@code Nodes}.
 * @see WirlStyle
 * @see WirlColor
 * @see WirlTheme
 */
public class Wirl
{
    /*******************************************************************************
     *                                                                             *
     * Static fields
     *                                                                             *
     ******************************************************************************/
    private static Map<Scene, WirlTheme> sceneThemeMap;

    /* *****************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    /**
     * Registers a new {@link WirlTheme} to the given {@link Scene).
     *
     * @param scene The {@code Scene}. that should be styled.
     * @param wirlTheme The {@code WirlTheme}. If null is passed an existing {@code WirlTheme} theme is removed.
     */
    public static void putTheme(Scene scene, WirlTheme wirlTheme)
    {
        if (sceneThemeMap == null) sceneThemeMap = new HashMap<>(1);
        // Check if the scene is already registered
        if (sceneThemeMap.containsKey(scene))
            if (wirlTheme == sceneThemeMap.get(scene)) return;
            else scene.getStylesheets().remove(wirlTheme.getUrl().toExternalForm());

        // Add the new theme
        if (wirlTheme == null) sceneThemeMap.remove(scene);
        else
        {
            scene.getStylesheets().add(wirlTheme.getUrl().toExternalForm());
            sceneThemeMap.put(scene, wirlTheme);
        }
    }

    /**
     * Removes an applied {@link WirlTheme} from the given {@link Scene).
     * @param scene The {@code Scene}.
     */
    public static void removeTheme(Scene scene)
    {
        // Check if the scene is registered.
        if (sceneThemeMap.containsKey(scene))
        {
            WirlTheme wirlTheme = sceneThemeMap.get(scene);
            sceneThemeMap.remove(scene);
            scene.getStylesheets().remove(wirlTheme.getUrl().toExternalForm());
        }
    }

    /**
     * Sets the {@link WirlStyle} of a given {@link Node}. If the {@code WirlStyle} is not supported for the given Node, nothing happens.
     * @param wirlStyle The {@code WirlStyle}.
     * @param nodes The {@code Node}.
     */
    public static void setStyle(WirlStyle wirlStyle, Node... nodes)
    {
        for (Node node : nodes)
            if (node != null)
                if (wirlStyle.isSupportedNode(node))
                    node.setId(wirlStyle.getNodeStyleID(node));
    }

    /**
     * Sets a {@link WirlColor} of a given {@link Scene}. The color is set by inline-css. That means, custom inline-css could be overridden.
     * @param wirlColor The {@link WirlColor}.
     * @param newColor The new Color value.
     * @param scene The {@code Scene}.
     */
    public static void setColor(WirlColor wirlColor, Color newColor, Scene scene)
    {
        setColor(wirlColor, newColor, scene.getRoot());
    }

    /**
     * Sets a {@link WirlColor} of the given {@code Nodes}. The color is set by inline-css. That means, custom inline-css could be overridden.
     * @param wirlColor The {@link WirlColor}.
     * @param newColor The new Color value.
     * @param nodes The {@code Nodes}.
     */
    public static void setColor(WirlColor wirlColor, Color newColor, Node... nodes)
    {
        for (Node node : nodes)
            if (node != null)
                node.setStyle(wirlColor.getProperty() + ":" + WirlUtility.colorToCssRgb(newColor) + ";");
    }
}
