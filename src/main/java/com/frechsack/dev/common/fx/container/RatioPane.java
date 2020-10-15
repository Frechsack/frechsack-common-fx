package com.frechsack.dev.common.fx.container;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * A {@link RatioPane} layouts a single Node with a given ratio in it's layouts Bounds.
 */
public class RatioPane extends Pane
{
    private final IntegerProperty ratioX = new SimpleIntegerProperty(this, "ratioX", 0);
    private final IntegerProperty ratioY = new SimpleIntegerProperty(this, "ratioY", 0);
    private final ObjectProperty<Pos> anchor = new SimpleObjectProperty<>(this, "anchor", Pos.CENTER);
    private final InvalidationListener propertyListener = e -> layoutChildren();

    /**
     * Creates a new instance of {@link RatioPane}.
     *
     * @param child  The child.
     * @param ratioX The ratio on the x-axis. If the specified value is less or equal to zero, the ratio will be ignored.
     * @param ratioY The ration on the y-axis. If the specified value is less or equal to zero, the ratio will be ignored.
     * @param anchor The anchor of the child.
     */
    public RatioPane(Node child, int ratioX, int ratioY, Pos anchor)
    {
        super(child);
        this.anchor.set(anchor);
        this.ratioX.set(ratioX);
        this.ratioY.set(ratioY);
        registerListeners();
        layoutChildren();
    }

    /**
     * Creates a new instance of {@link RatioPane}.
     *
     * @param child  The child.
     * @param ratioX The ratio on the x-axis. If the specified value is less or equal to zero, the ratio will be ignored.
     * @param ratioY The ration on the y-axis. If the specified value is less or equal to zero, the ratio will be ignored.
     */
    public RatioPane(Node child, int ratioX, int ratioY)
    {
        super(child);
        registerListeners();
        layoutChildren();
    }

    /**
     * Creates a new instance of {@link RatioPane}.
     */
    public RatioPane()
    {
        registerListeners();
    }

    private void registerListeners()
    {
        anchor.addListener(propertyListener);
        ratioY.addListener(propertyListener);
        ratioX.addListener(propertyListener);
    }

    public void setRation(int ratio)
    {
        this.ratioX.set(ratio);
        this.ratioY.set(ratio);
    }

    public int getRatioX()
    {
        return ratioX.get();
    }

    public IntegerProperty ratioXProperty()
    {
        return ratioX;
    }

    public void setRatioX(int ratioX)
    {
        this.ratioX.set(ratioX);
    }

    public int getRatioY()
    {
        return ratioY.get();
    }

    public IntegerProperty ratioYProperty()
    {
        return ratioY;
    }

    public void setRatioY(int ratioY)
    {
        this.ratioY.set(ratioY);
    }

    public Pos getAnchor()
    {
        return anchor.get();
    }

    public ObjectProperty<Pos> anchorProperty()
    {
        return anchor;
    }

    public void setAnchor(Pos anchor)
    {
        this.anchor.set(anchor);
    }

    @Override
    protected void layoutChildren()
    {
        // Children check
        if (getChildren().isEmpty()) return;

        Node node   = getChildren().get(0);
        int  ratioX = this.ratioX.get();
        int  ratioY = this.ratioY.get();
        Pos  anchor = this.anchor.get();

        // Anchor null check
        if (anchor == null) anchor = Pos.CENTER;

        // Quick calculation if one ratio is less or equal to zero
        if (ratioX <= 0 || ratioY <= 0)
        {
            node.resizeRelocate(getInsets().getLeft(),
                                getInsets().getTop(),
                                getWidth() - getInsets().getRight() - getInsets().getLeft(),
                                getHeight() - getInsets().getTop() - getInsets().getBottom());

            return;
        }

        // Calculate size
        double width;
        double height;
        double insetWidth  = getWidth() - getInsets().getRight() - getInsets().getLeft();
        double insetHeight = getHeight() - getInsets().getTop() - getInsets().getBottom();
        float  xMod        = (float) (insetWidth / ratioX);
        float  yMod        = (float) (insetHeight / ratioY);
        if (xMod < yMod)
        {
            width  = insetWidth;
            height = width / ratioX * ratioY;
        }
        else if (xMod > yMod)
        {
            height = insetHeight;
            width  = height / ratioY * ratioX;
        }
        else
        {
            width  = getWidth() - getInsets().getRight() - getInsets().getLeft();
            height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        }
        node.resize(width, height);

        // Calculate position
        double x;
        double y;
        if (anchor.getHpos() == HPos.LEFT)
            x = getInsets().getLeft();
        else if (anchor.getHpos() == HPos.CENTER)
            x = getWidth() / 2 - width / 2;
        else
            x = getWidth() - getInsets().getRight() - width;

        if (anchor.getVpos() == VPos.TOP)
            y = getInsets().getTop();
        else if (anchor.getVpos() == VPos.BOTTOM)
            y = getHeight() - getInsets().getBottom() - height;
        else y = getHeight() / 2 - height / 2;
        node.relocate(x, y);
    }
}
