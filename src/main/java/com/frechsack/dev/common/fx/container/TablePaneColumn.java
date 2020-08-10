package com.frechsack.dev.common.fx.container;


public class TablePaneColumn extends TablePaneDefinition
{

    public TablePaneColumn(SizeMode sizeMode, double size)
    {
        super(sizeMode, size);
    }

    public TablePaneColumn(SizeMode sizeMode, double minSize, double size, double maxSize)
    {
        super(sizeMode, minSize, size, maxSize);
    }

    public TablePaneColumn(double minSize, double additionalSize, double maxSize)
    {
        super(minSize, additionalSize, maxSize);
    }

    public TablePaneColumn(TablePaneColumn copy)
    {
        super(copy);
    }

    @Override
    public Object clone()
    {
        return new TablePaneColumn(this);
    }
}
