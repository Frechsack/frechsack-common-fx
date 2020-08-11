package com.frechsack.dev.common.fx.wirl.base;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.css.converter.ColorConverter;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface WirlValueFillSkin
{
    Color valueFillColorDefault = Color.rgb(144, 200, 246);
    String valueFillColorKey = "-value-fill";

    CssMetaData<Slider, Color> valueFillColorMetaData =
            new CssMetaData<>(valueFillColorKey, ColorConverter.getInstance(), valueFillColorDefault)
            {
                @Override
                public boolean isSettable(Slider styleable)
                {
                    if (styleable.getSkin() instanceof WirlValueFillSkin)
                        return !((WirlValueFillSkin) styleable.getSkin()).valueFillColorProperty().isBound();
                    return false;
                }

                @Override
                public StyleableProperty<Color> getStyleableProperty(Slider styleable)
                {
                    return ((WirlValueFillSkin) styleable.getSkin()).valueFillColorProperty();
                }
            };

    StyleableObjectProperty<Color> valueFillColorProperty();

    private static List<CssMetaData<? extends Styleable, ?>> getMetaDataUnmodifiableList()
    {
        ArrayList<CssMetaData<? extends Styleable, ?>> list = new ArrayList<>();
        Collections.addAll(list, valueFillColorMetaData);
        return Collections.unmodifiableList(list);
    }


    List<CssMetaData<? extends Styleable, ?>> wirlValueFillSkinMetaDataUnmodifieableList = getMetaDataUnmodifiableList();
}
