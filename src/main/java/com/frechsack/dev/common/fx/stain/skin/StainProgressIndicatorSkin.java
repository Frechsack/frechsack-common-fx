package com.frechsack.dev.common.fx.stain.skin;


import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SkinBase;
import javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.layout.StackPane;

public class StainProgressIndicatorSkin extends SkinBase<ProgressIndicator>
{

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public StainProgressIndicatorSkin(ProgressIndicator control)
    {
        super(control);

        StackPane pane = new StackPane();
        pane.setStyle("-fx-background-color:purple");
        getChildren().add(pane);



    }





}
