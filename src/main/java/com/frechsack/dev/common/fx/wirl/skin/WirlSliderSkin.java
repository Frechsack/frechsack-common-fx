package com.frechsack.dev.common.fx.wirl.skin;

import com.frechsack.dev.common.fx.wirl.base.WirlValueFillSkin;
import javafx.beans.value.ChangeListener;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Custom {@link javafx.scene.control.Skin} for a {@link Slider}. The {@code Skin} is part of the {@link com.frechsack.dev.common.fx.wirl.Wirl} look and feel.
 */
public class WirlSliderSkin extends SliderSkin implements WirlValueFillSkin
{
    private static final List<CssMetaData<? extends Styleable, ?>> wirlSkinMetaDataUnmodifieableList;
    static
    {
        ArrayList<CssMetaData<? extends Styleable, ?>> list = new ArrayList<>(Slider.getClassCssMetaData());
        list.addAll(wirlValueFillSkinMetaDataUnmodifieableList);
        wirlSkinMetaDataUnmodifieableList = Collections.unmodifiableList(list);
    }

    private final StyleableObjectProperty<Color>
                                         valueFillColor              =
            new SimpleStyleableObjectProperty<>(valueFillColorMetaData, valueFillColorDefault);
    private final Slider                 control;
    private final Rectangle              valueFillShape              = new Rectangle();
    private       StackPane              track;
    private       StackPane              thumb;
    private final ChangeListener<Number> valueFillShapePositionChangeListener = (observable, oldValue, newValue) -> recolateValueFillShape();
    private final ChangeListener<Color> valueFillColorListener = (observable, oldValue, newValue) -> valueFillShape.setFill(newValue);

    public WirlSliderSkin(Slider control)
    {
        super(control);
        this.control = control;

        // Get Track
        for (Node node : getChildren())
        {
            if (node.getStyleClass().contains("track"))
                track = (StackPane) node;
            if (node.getStyleClass().contains("thumb"))
                thumb = (StackPane) node;
            if (thumb != null && track != null)
                break;
        }
        if (track == null || thumb == null)
            throw new NullPointerException("There is no Track found in this slider");

        getChildren().add(valueFillShape);
        thumb.layoutYProperty().addListener(valueFillShapePositionChangeListener);
        thumb.layoutXProperty().addListener(valueFillShapePositionChangeListener);
        thumb.widthProperty().addListener(valueFillShapePositionChangeListener);
        thumb.heightProperty().addListener(valueFillShapePositionChangeListener);

        track.widthProperty().addListener(valueFillShapePositionChangeListener);
        track.heightProperty().addListener(valueFillShapePositionChangeListener);
        track.layoutXProperty().addListener(valueFillShapePositionChangeListener);
        track.layoutYProperty().addListener(valueFillShapePositionChangeListener);

        valueFillColor.addListener(valueFillColorListener);
        valueFillShape.setFill(valueFillColor.get());
    }

    private void recolateValueFillShape()
    {
        // TODO: Get border width from css meta data.
        double  borderWidth  = 1;
        boolean isHorizontal = control.getOrientation() == Orientation.HORIZONTAL;
        double width;
        double height;

        // This may cause an error if layout coordinates are zero
        /*
        if (isHorizontal && thumb.getLayoutX() == 0 || !isHorizontal && thumb.getLayoutY() == 0)
        {
            System.out.println("Exec first if");
            double percent = control.getValue() / control.getMax();
            System.out.println(track.getWidth());

            width = isHorizontal ? track.getWidth() * percent : track.getHeight() * percent;
            System.out.println("Calc Width: " + width);
        }*/
        width  = isHorizontal ? thumb.getLayoutX() + thumb.getWidth() / 2 : thumb.getLayoutY() + thumb.getHeight() / 2;
        height = isHorizontal ? track.getHeight() : track.getWidth();

        width -= borderWidth * 2;
        height -= borderWidth * 2;

        valueFillShape.setX(track.getLayoutX() + borderWidth);
        valueFillShape.setY(track.getLayoutY() + borderWidth);

        // Get the comp orientation
        if (isHorizontal)
        {
            valueFillShape.setWidth(width);
            valueFillShape.setHeight(height);
        }
        else
        {
            valueFillShape.setWidth(height);
            valueFillShape.setHeight(width);
        }
        thumb.toFront();
    }

    @Override
    public void dispose()
    {
        super.dispose();

        thumb.layoutYProperty().removeListener(valueFillShapePositionChangeListener);
        thumb.layoutXProperty().removeListener(valueFillShapePositionChangeListener);
        thumb.widthProperty().removeListener(valueFillShapePositionChangeListener);
        thumb.heightProperty().removeListener(valueFillShapePositionChangeListener);

        track.widthProperty().removeListener(valueFillShapePositionChangeListener);
        track.heightProperty().removeListener(valueFillShapePositionChangeListener);
        track.layoutXProperty().removeListener(valueFillShapePositionChangeListener);
        track.layoutYProperty().removeListener(valueFillShapePositionChangeListener);

        valueFillColor.removeListener(valueFillColorListener);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData()
    {
        return wirlSkinMetaDataUnmodifieableList;
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData()
    {
        return wirlSkinMetaDataUnmodifieableList;
    }

    @Override
    public StyleableObjectProperty<Color> valueFillColorProperty()
    {
        return valueFillColor;
    }
}
