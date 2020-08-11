package com.frechsack.dev.common.fx.wirl;

import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A {@code WirlTheme} is a set of {@link WirlColor}'s.
 */
public enum WirlTheme
{
    /**
     * Indicates a dark theme.
     */
    DARK("DARK", WirlTheme.class.getResource("stylesheets/wirldarkstyle.css")),

    /**
     * Indicates the default theme.
     */
    DEFAULT("DEFAULT", WirlTheme.class.getResource("stylesheets/wirlstyle.css")),

    /**
     * Indicates the light theme.
     */
    LIGHT("LIGHT", WirlTheme.class.getResource("stylesheets/wirllightstyle.css"));

    /*******************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/
    private       Map<WirlColor, Color> colorMap;
    private final String                name;
    private final URL                   url;
    /*******************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    WirlTheme(String name, URL url)
    {
        if (url == null)
        {
            System.out.println("WirlTheme: " + name + " got a null url.");
            System.exit(0);
        }
        this.name = name;
        this.url  = url;
    }


    private void readCssFile(URL url)
    {
        CssParser parser = new CssParser();

        try
        {
            Stylesheet css = parser.parse(url);

            // Check if a root rule exists
            if (css.getRules().size() == 0)
            {
                System.err.println("The stylesheet:" + url.toExternalForm() + " contains no rules.");
                return;
            }
            // Get the last .root entry
            List<Rule> ruleList = css.getRules();
            Rule       rootRule = ruleList.get(0);

            for (Rule r : ruleList)
                for (Selector s : r.getSelectors())
                {
                    String result = s.toString();
                    if (result.equals("*.root"))
                    {
                        rootRule = r;
                        break;
                    }
                }

            colorMap = new HashMap<>(WirlColor.values().length);

            for (WirlColor wirlColor : WirlColor.values())
            {
                Color color = Color.WHITE;
                for (Declaration declaration : rootRule.getDeclarations())
                    if (declaration.getProperty().equals(wirlColor.getProperty()))
                    {
                        color = ColorConverter.getInstance().convert(declaration.getParsedValue(), null);
                        break;
                    }
                colorMap.put(wirlColor, color);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Returns the native {@link Color} of a {@link WirlColor}. If the {@code WirlColor} is not defined by this {@code WirlTheme}, the {@link WirlTheme#DEFAULT}'s theme {@code Color} is returned.
     * @param wirlColor The {@code WirlColor}.
     * @return The native {@link Color}.
     */
    public Color getColor(WirlColor wirlColor)
    {
        if (colorMap == null) readCssFile(url);
        if (this != DEFAULT)
            return colorMap.getOrDefault(wirlColor, DEFAULT.getColor(wirlColor));
        else
            return colorMap.getOrDefault(wirlColor, Color.TRANSPARENT);
    }

    /**
     * Returns the name of this {@code WirlTheme}.
     * @return The name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns a reference to a stylesheet of this {@code WirlTheme}.
     * @return The reference.
     */
    public URL getUrl()
    {
        return url;
    }
}
