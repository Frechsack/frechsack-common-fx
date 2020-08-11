package com.frechsack.dev.common.fx.wirl.skin;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SkinBase;
import javafx.scene.control.skin.ProgressIndicatorSkin;
import javafx.scene.shape.Path;

public class WirlProgressIndicatorSkin extends SkinBase<ProgressIndicator>
{
    private Path ringPath;


    public WirlProgressIndicatorSkin(ProgressIndicator control)
    {
        super(control);

        initialize();

    }

    private void initialize()
    {
        ringPath = new Path();


    }
}
