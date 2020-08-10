package com.frechsack.dev.common.fx.container;

public class TablePaneDefinition implements Cloneable
{
    private SizeMode sizeMode;
    private double   size;
    private double   minSize = -1;
    private double   maxSize = -1;


    public TablePaneDefinition(SizeMode sizeMode, double size)
    {
        this.sizeMode = sizeMode;
        this.size     = size;
    }

    public TablePaneDefinition(SizeMode sizeMode, double minSize, double size, double maxSize)
    {
        this.sizeMode = sizeMode;
        this.minSize  = minSize;
        this.maxSize  = maxSize;
        this.size     = size;
    }

    public TablePaneDefinition(double minSize, double additionalSize, double maxSize)
    {
        this.sizeMode = SizeMode.AUTO_SIZE;
        this.minSize  = minSize;
        this.maxSize  = maxSize;
        this.size     = additionalSize;
    }

    public TablePaneDefinition(TablePaneDefinition copy)
    {
        this.sizeMode = copy.sizeMode;
        this.size     = copy.size;
        this.minSize  = copy.minSize;
        this.maxSize  = copy.maxSize;
    }

    public boolean isAutoSize()
    {
        return sizeMode == SizeMode.AUTO_SIZE;
    }

    public boolean isRelative()
    {
        return sizeMode == SizeMode.RELATIVE;
    }

    public boolean isAbsolute()
    {
        return sizeMode == SizeMode.ABSOLUTE;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public void setSizeMode(SizeMode sizeMode)
    {
        this.sizeMode = sizeMode;
    }

    public double getSize()
    {
        return size;
    }

    public double getMaxSize()
    {
        return maxSize;
    }

    public boolean isMinSizeSet()
    {
        return minSize != -1;
    }

    public boolean isMaxSizeSet()
    {
        return maxSize != -1;
    }

    public double getMinSize()
    {
        return minSize;
    }

    public SizeMode getSizeMode()
    {
        return sizeMode;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone()
    {
        return new TablePaneDefinition(this);
    }
}
