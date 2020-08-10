package com.frechsack.dev.common.fx.container;

/**
 * A {@link TablePaneColumn} defines how a {@link TablePane} column behaves.<br> A {@code TablePaneColumn} assigns a {@link SizeMode} and a {@code
 * SizeMode} depending size.<br> A {@code TablePaneColumn} contains a minimum and maximum size too.
 *
 * @see SizeMode
 * @see TablePane
 * @see TablePaneDefinition
 */
public class TablePaneColumn extends TablePaneDefinition implements Cloneable
{
    /**
     * Creates a new instance of {@code TablePaneColumn} with a given {@code SizeMode} and size.
     *
     * @param sizeMode The  {@code SizeMode}
     * @param size     The size.
     */
    public TablePaneColumn(SizeMode sizeMode, double size)
    {
        super(sizeMode, size);
    }

    /**
     * Creates a new instance of {@code TablePaneColumn} with a given {@code SizeMode}, size, minimum and maximum size. <br> If the different sizes
     * are invalid, they will be set right.
     *
     * @param sizeMode The  {@code SizeMode}
     * @param size     The size.
     * @param minSize  The minimum size.
     * @param maxSize  The maximum size.
     */
    public TablePaneColumn(SizeMode sizeMode, double size, double minSize, double maxSize)
    {
        super(sizeMode, minSize, size, maxSize);
    }

    /**
     * Creates a new instance of {@code TablePaneColumn} with a {@link SizeMode#AUTO_SIZE} {@code SizeMode}.
     *
     * @param additionalSize The additional size.
     * @param minSize        The minimum size.
     * @param maxSize        The maximum size.
     */
    public TablePaneColumn(double additionalSize, double minSize, double maxSize)
    {
        super(minSize, additionalSize, maxSize);
    }

    /**
     * Creates Creates a new instance of {@code TablePaneColumn}. The created Object is a deep copy of the given {@code TablePaneColumn}.
     *
     * @param copy The base.
     */
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
