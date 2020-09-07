package com.frechsack.dev.common.fx.stain;

import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A StainTheme maps a {@link StainColor} to a {@link Color}.
 */
public enum StainTheme
{
    /**
     * The default theme.
     */
    DEFAULT(StainTheme.class.getResource("stylesheets/stainstyle.css")),
    /**
     * A lighter theme.
     */
    LIGHT(StainTheme.class.getResource("stylesheets/stainlightstyle.css")),
    /**
     * A dark theme.
     */
    DARK(StainTheme.class.getResource("stylesheets/staindarkstyle.css"));


    private final URL styleSheetURL;
    private Map<StainColor, Color> colorMap;
    StainTheme(URL styleSheetURL)
    {
        this.styleSheetURL = styleSheetURL;
    }

    private void requireColorMap()
    {
        if(colorMap!=null)return;
        CssParser parser = new CssParser();

        try
        {
            Stylesheet css = parser.parse(styleSheetURL);

            // Check if a root rule exists
            if (css.getRules().size() == 0)
            {
                System.err.println("The stylesheet:" + styleSheetURL.toExternalForm() + " contains no rules.");
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
            colorMap = new HashMap<>(StainColor.values().length);
            for (StainColor stainColor : StainColor.values())
            {
                Color color = Color.WHITE;
                for (Declaration declaration : rootRule.getDeclarations())
                    if (declaration.getProperty().equals(stainColor.getProperty()))
                    {
                        color = ColorConverter.getInstance().convert(declaration.getParsedValue(), null);
                        break;
                    }
                colorMap.put(stainColor, color);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Color of the specified {@link StainColor}.
     * @param color The StainColor.
     * @return The color.
     */
    public Color getColor(StainColor color)
    {
        requireColorMap();
        return colorMap.get(color);
    }

    URL getStyleSheetURL()
    {
        return styleSheetURL;
    }
}
