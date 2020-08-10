package com.frechsack.dev.common.fx.container;

/**
 * A {@link TablePaneDefinition} defines how a {@link TablePane} row or column behaves.<br> A {@code TablePaneDefinition} assigns a {@link SizeMode}
 * and a {@code SizeMode} depending size.<br> A {@code TablePaneDefinition} contains a minimum and maximum size too.
 *
 * @see SizeMode
 * @see TablePane
 */
public class TablePaneDefinition implements Cloneable
{
    /* *****************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/
    private SizeMode sizeMode;
    private double   size;
    private double   minSize = -1;
    private double   maxSize = -1;

    /* *****************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    /**
     * Creates a new instance of {@code TablePaneDefinition} with a given {@code SizeMode} and size.
     *
     * @param sizeMode The  {@code SizeMode}
     * @param size     The size.
     */
    public TablePaneDefinition(SizeMode sizeMode, double size)
    {
        this.sizeMode = sizeMode;
        this.size     = size;
    }

    /**
     * Creates a new instance of {@code TablePaneDefinition} with a given {@code SizeMode}, size, minimum and maximum size. <br> If the different
     * sizes are invalid, they will be set right.
     *
     * @param sizeMode The  {@code SizeMode}
     * @param size     The size.
     * @param minSize  The minimum size.
     * @param maxSize  The maximum size.
     */
    public TablePaneDefinition(SizeMode sizeMode, double size, double minSize, double maxSize)
    {
        this.sizeMode = sizeMode;
        this.minSize  = minSize;
        this.maxSize  = maxSize;
        this.size     = size;
    }

    /**
     * Creates a new instance of {@code TablePaneDefinition} with a {@link SizeMode#AUTO_SIZE} {@code SizeMode}.
     *
     * @param additionalSize The additional size.
     * @param minSize        The minimum size.
     * @param maxSize        The maximum size.
     */
    public TablePaneDefinition(double additionalSize, double minSize, double maxSize)
    {
        this.sizeMode = SizeMode.AUTO_SIZE;
        this.minSize  = minSize;
        this.maxSize  = maxSize;
        this.size     = additionalSize;
    }

    /**
     * Creates Creates a new instance of {@code TablePaneDefinition}. The created Object is a deep copy of the given {@code TablePaneDefinition}.
     *
     * @param copy The base.
     */
    public TablePaneDefinition(TablePaneDefinition copy)
    {
        this.sizeMode = copy.sizeMode;
        this.size     = copy.size;
        this.minSize  = copy.minSize;
        this.maxSize  = copy.maxSize;
    }

    // Size Mode

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

    public SizeMode getSizeMode()
    {
        return sizeMode;
    }

    public void setSizeMode(SizeMode sizeMode)
    {
        this.sizeMode = sizeMode;
    }

    // Size

    public void setSize(double size)
    {
        this.size = size;
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

    public void setMaxSize(double maxSize)
    {
        this.maxSize = maxSize;
    }

    public void setMinSize(double minSize)
    {
        this.minSize = minSize;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone()
    {
        return new TablePaneDefinition(this);
    }
}
