package com.frechsack.dev.common.fx.container;

public class TablePaneRow extends TablePaneDefinition implements Cloneable
{
    public TablePaneRow(SizeMode sizeMode, double size)
    {
        super(sizeMode, size);
    }

    public TablePaneRow(SizeMode sizeMode, double minSize, double size, double maxSize)
    {
        super(sizeMode, minSize, size, maxSize);
    }

    public TablePaneRow(double minSize, double additionalSize, double maxSize)
    {
        super(minSize, additionalSize, maxSize);
    }

    public TablePaneRow(TablePaneRow copy)
    {
        super(copy);
    }


    @Override
    public Object clone()
    {
        return new TablePaneRow(this);
    }

}
