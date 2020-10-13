package com.frechsack.dev.common.fx.stain.skin;


import javafx.css.*;
import javafx.css.converter.SizeConverter;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SkinBase;
import javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.control.skin.ProgressIndicatorSkin;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StainProgressIndicatorSkin extends SkinBase<ProgressIndicator>
{
    /*******************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/

    private StackPane track;
    private StackPane bar;
    private Text      text;

    /*******************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    public StainProgressIndicatorSkin(ProgressIndicator control)
    {
        super(control);

        track = new StackPane();
        track.getStyleClass().setAll("track");
        track.setStyle("-fx-background-color:red;");
        bar = new StackPane();
        bar.getStyleClass().setAll("progress");
        bar.setStyle("-fx-background-color:blue;");
        text = new Text();
        text.getStyleClass().setAll("text");

        getChildren().setAll(track, bar);

        initialize();
    }

    private void initialize()
    {
        if (getSkinnable().isIndeterminate())
        {
        }
        else
        {
        }
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset)
    {
        return 100;
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset)
    {
        return 20;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset)
    {
        return 20;
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset)
    {
        return 10;
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight)
    {
        boolean isIndeterminate = getSkinnable().isIndeterminate();

        // Place the track
        //track.setPrefWidth(500);
        track.setPrefHeight(1);
        bar.setPrefHeight(1);

        track.resizeRelocate(contentX, contentY, contentWidth, contentHeight);
        bar.resizeRelocate(contentX, contentY, contentWidth, contentHeight);

    }

    /* ******************************************************************************
     *                                                                             *
     * Determinate
     *                                                                             *
     ******************************************************************************/

    /*******************************************************************************
     *                                                                             *
     * Properties
     *                                                                             *
     ******************************************************************************/
    private StyleableDoubleProperty indeterminateAnimationTime;

    public StyleableProperty<Number> indeterminateAnimationTimeProperty()
    {
        if (indeterminateAnimationTime == null) indeterminateAnimationTime =
                new SimpleStyleableDoubleProperty(
                        StyleableProperties.INDETERMINATE_ANIMATION_TIME,
                        this,
                        "indeterminateAnimationTime",
                        2.0);
        return indeterminateAnimationTime;
    }

    public void setIndeterminateAnimationTime(double indeterminateAnimationTime)
    {
        this.indeterminateAnimationTime.set(indeterminateAnimationTime);
    }

    public double getIndeterminateAnimationTime()
    {
        return indeterminateAnimationTime.get();
    }

    private static class StyleableProperties
    {
        private static final CssMetaData<ProgressIndicator, Number> INDETERMINATE_ANIMATION_TIME =
                new CssMetaData<>("-fx-indeterminate-animation-time",
                                  SizeConverter.getInstance(), 2.0)
                {
                    @Override
                    public boolean isSettable(ProgressIndicator n)
                    {
                        final StainProgressIndicatorSkin skin = (StainProgressIndicatorSkin) n.getSkin();
                        return skin.indeterminateAnimationTime == null ||
                                !skin.indeterminateAnimationTime.isBound();
                    }

                    @Override
                    public StyleableProperty<Number> getStyleableProperty(ProgressIndicator n)
                    {
                        final StainProgressIndicatorSkin skin = (StainProgressIndicatorSkin) n.getSkin();
                        return skin.indeterminateAnimationTimeProperty();
                    }
                };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static
        {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(SkinBase.getClassCssMetaData());
            styleables.add(INDETERMINATE_ANIMATION_TIME);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    /**
     * Returns the CssMetaData associated with this class, which may include the CssMetaData of its superclasses.
     *
     * @return the CssMetaData associated with this class, which may include the CssMetaData of its superclasses
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData()
    {
        return StyleableProperties.STYLEABLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData()
    {
        return getClassCssMetaData();
    }
}
