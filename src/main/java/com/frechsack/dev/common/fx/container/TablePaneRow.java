package com.frechsack.dev.common.fx.container;

public class TablePaneRow extends TablePaneDefinition implements Cloneable
{

    public TablePaneRow(byte type, double size)
    {
        super(type, size);
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
