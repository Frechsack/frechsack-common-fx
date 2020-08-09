package com.frechsack.dev.common.fx.container;

public class TablePaneDefinition implements TablePaneConstants, Cloneable
{
    private byte   type;
    private double size;
    private double minSize = -1;
    private double maxSize = -1;


    public TablePaneDefinition(byte type, double size)
    {
        this.type = type;
        this.size = size;
    }

    public TablePaneDefinition(byte type,double minSize,double size, double maxSize)
    {
        this.type = type;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.size = size;
    }

    public TablePaneDefinition(double minSize,double additionalSize, double maxSize)
    {
        this.type = AUTO_SIZE;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.size = additionalSize;
    }

    public TablePaneDefinition(TablePaneDefinition copy)
    {
        this.type = copy.type;
        this.size = copy.size;
        this.minSize = copy.minSize;
        this.maxSize = copy.maxSize;
    }


    public void setSize(double size)
    {
        this.size = size;
    }


    public void setType(byte type)
    {
        this.type = type;
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
        return minSize == -1;
    }

    public boolean isMaxSizeSet()
    {
        return maxSize == -1;
    }

    public double getMinSize()
    {
        return minSize;
    }


    public byte getType()
    {
        return type;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone()
    {
        return new TablePaneDefinition(this);
    }
}
