package com.frechsack.dev.common.fx.container;


public class TablePaneColumn extends TablePaneDefinition
{
    public TablePaneColumn(byte type, double size)
    {
        super(type, size);
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
