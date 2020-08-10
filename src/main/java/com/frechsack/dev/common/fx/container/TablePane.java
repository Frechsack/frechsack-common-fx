package com.frechsack.dev.common.fx.container;

import com.frechsack.dev.common.fx.geometry.WriteableRectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A {@link TablePane} organizes {@code Nodes} in a grid of columns and rows. Each column and row can be defined. <br> Columns and rows are not added
 * and removed dynamic when Nodes are added.<br> The {@link TablePaneConstraints} of a Node are stored in the Node itself.<br> The layout is not
 * updated automatically if the {@code TablePaneRows} or {@code TablePaneColumns} are modified.<br> The function {@link TablePane#layout()} should be
 * called manually.
 * @see TablePaneDefinition
 * @see TablePaneConstraints
 * @see FillMode
 * @see SizeMode
 */
public class TablePane extends Pane
{
    /*******************************************************************************
     *                                                                             *
     * Attributes
     *                                                                             *
     ******************************************************************************/
    private static final String                    TABLE_PANE_CONSTRAINTS_KEY = "table-pane-constraints";
    private final        List<TablePaneDefinition> rowList                    = new ArrayList<>();
    private final        List<TablePaneDefinition> columnList                 = new ArrayList<>();
    private final        WriteableRectangle2D      nodeArea                   = new WriteableRectangle2D(0, 0, 0, 0);
    private final        WriteableRectangle2D      nodeBounds                 = new WriteableRectangle2D(0, 0, 0, 0);
    private              double[]                  columnWidths;
    private              double[]                  rowHeights;

    /* *****************************************************************************
     *                                                                             *
     * Methods
     *                                                                             *
     ******************************************************************************/
    /**
     * Creates a new instance of {@code TablePane} with no columns or rows.
     */
    public TablePane()
    {
        super();
    }

    /**
     * Creates a new instance of {@code TablePane} with the defined columns and rows.
     *
     * @param tablePaneDefinitions The columns and rows of the {@code TablePane}. You should pass instances of {@link TablePaneColumn} and {@link
     *                             TablePaneRow}.
     */
    public TablePane(TablePaneDefinition... tablePaneDefinitions)
    {
        super();
        for (TablePaneDefinition def : tablePaneDefinitions)
            if (def instanceof TablePaneColumn) columnList.add(def);
            else if (def instanceof TablePaneRow) rowList.add(def);
            else if (columnList.size() > rowList.size()) rowList.add(def);
            else columnList.add(def);

    }

    /**
     * Adds a children to this {@code TablePane} with the given {@link TablePaneConstraints}.
     *
     * @param child       The child that should be added.
     * @param constraints The {@code TablePaneConstraints}.
     * @return An instance of this {@code TablePane}.
     */
    public TablePane add(Node child, TablePaneConstraints constraints)
    {
        setConstraints(child, constraints);
        getChildren().add(child);
        return this;
    }

    /**
     * Adds a {@link TablePaneDefinition} as a row to this {@link TablePane}. The same {@link TablePaneDefinition} can be added multiple times to this
     * {@link TablePane}.
     *
     * @param definition The {@link TablePaneDefinition}
     * @return An instance of this {@code TablePane}.
     */
    public TablePane addRow(TablePaneDefinition definition)
    {
        rowList.add(definition);
        return this;
    }

    /**
     * Adds a {@link TablePaneDefinition} as a column to this {@link TablePane}. The same {@code TablePaneDefinition} can be added multiple times to
     * this {@link TablePane}.
     *
     * @param definition The {@code TablePaneDefinition}
     * @return An instance of this {@code TablePane}.
     */
    public TablePane addColumn(TablePaneDefinition definition)
    {
        columnList.add(definition);
        return this;
    }

    /**
     * Sets the {@link TablePaneConstraints} of the given {@link Node}.
     *
     * @param node        The {@code Node}.
     * @param constraints The {@code Node's} {@code TablePaneConstraints}.
     */
    public static void setConstraints(Node node, TablePaneConstraints constraints)
    {
        if (constraints == null) node.getProperties().remove(TABLE_PANE_CONSTRAINTS_KEY);
        else node.getProperties().put(TABLE_PANE_CONSTRAINTS_KEY, constraints);
    }

    /**
     * Returns the {@link TablePaneConstraints} of the given {@link Node}.
     *
     * @param node The {@code Node}.
     * @return The {@code Node's}  {@code TablePaneConstraints}. If the don't exist, a shared instance of {@code TablePaneConstraints} is returned.
     */
    public static TablePaneConstraints getConstraints(Node node)
    {
        if (node.hasProperties())
        {
            Object value = node.getProperties().get(TABLE_PANE_CONSTRAINTS_KEY);
            if (value == null) return TablePaneConstraints.getNullConstraints();
            if (value instanceof TablePaneConstraints) return (TablePaneConstraints) value;
        }
        return TablePaneConstraints.getNullConstraints();
    }

    /**
     * Returns a list with instances of {@link TablePaneDefinition} who act as a row.
     * @return A list with instances of {@link TablePaneDefinition}.
     */
    public List<TablePaneDefinition> getRowList()
    {
        return rowList;
    }
    /**
     * Returns a list with instances of {@link TablePaneDefinition} who act as a column.
     * @return A list with instances of {@link TablePaneDefinition}.
     */
    public List<TablePaneDefinition> getColumnList()
    {
        return columnList;
    }

    /* ******************************************************************************
     *                                                                             *
     * Layout Calculation
     *                                                                             *
     ******************************************************************************/

    @Override
    protected void layoutChildren()
    {
        // Calculate Columns and Rows sizes
        calculateColumnWidths();

        calculateRowHeights();
        System.out.println("ColumnWidths: " + Arrays.toString(columnWidths));
        System.out.println("ColumnHeights: " + Arrays.toString(rowHeights));
        TablePaneConstraints constraints;
        for (Node child : getChildren())
        {
            constraints = getConstraints(child);
            calculateNodeArea(constraints);
            calculateNodeBounds(child, constraints);
        }
    }

    private void calculateNodeBounds(Node child, TablePaneConstraints constraints)
    {
        // System.out.println("NodeArea: " + nodeArea);

        if (constraints.isSizingBoth()) nodeBounds.setBounds(nodeArea.getMinX(), nodeArea.getMinY(), nodeArea.getWidth(), nodeArea.getHeight());
        else if (constraints.isSizingHorizontal())
        {
            nodeBounds.setWidth(nodeArea.getWidth());
            nodeBounds.setHeight(Math.min(nodeArea.getHeight(), getPrefHeight(child)));
            nodeBounds.setMinX(nodeArea.getMinX());
            // Anchors
            if (constraints.isAnchorTop()) nodeBounds.setMinY(nodeArea.getMinY());
            else if (constraints.isAnchorBottom()) nodeBounds.setMinY(nodeArea.getMaxY() - nodeBounds.getHeight());
            else nodeBounds.setMinY(nodeArea.getHeight() / 2 - nodeBounds.getHeight() / 2 + nodeArea.getMinY());

        }
        else if (constraints.isSizingVertical())
        {
            nodeBounds.setHeight(nodeArea.getHeight());
            nodeBounds.setWidth(Math.min(nodeArea.getWidth(), getPrefWidth(child)));
            nodeBounds.setMinY(nodeArea.getMinY());
            // Anchors
            if (constraints.isAnchorLeft()) nodeBounds.setMinX(nodeArea.getMinX());
            else if (constraints.isAnchorRight()) nodeBounds.setMinX(nodeArea.getMaxX() - nodeBounds.getWidth());
            else nodeBounds.setMinX(nodeArea.getWidth() / 2 - nodeBounds.getWidth() / 2 + nodeArea.getMinX());

        }
        else
        {
            nodeBounds.setHeight(Math.min(nodeArea.getHeight(), getPrefHeight(child)));
            nodeBounds.setWidth(Math.min(nodeArea.getWidth(), getPrefWidth(child)));
            // Anchors
            if (constraints.isAnchorLeft()) nodeBounds.setMinX(nodeArea.getMinX());
            else if (constraints.isAnchorRight()) nodeBounds.setMinX(nodeArea.getMaxX() - nodeBounds.getWidth());
            else nodeBounds.setMinX(nodeArea.getWidth() / 2 - nodeBounds.getWidth() / 2 + nodeArea.getMinX());
            if (constraints.isAnchorTop()) nodeBounds.setMinY(nodeArea.getMinY());
            else if (constraints.isAnchorBottom()) nodeBounds.setMinY(nodeArea.getMaxY() - nodeBounds.getHeight());
            else nodeBounds.setMinY(nodeArea.getHeight() / 2 - nodeBounds.getHeight() / 2 + nodeArea.getMinY());
        }

        //  System.out.println("NodeBound: " + nodeBounds);

        child.resizeRelocate(nodeBounds.getMinX(), nodeBounds.getMinY(), nodeBounds.getWidth(), nodeBounds.getHeight());

    }

    private void calculateNodeArea(TablePaneConstraints constraints)
    {
        int x = 0, y = 0, w = 0, h = 0;
        // Calculate aX
        for (int i = 0; i < constraints.getColumnIndex(); i++) x += columnWidths[i];
        // Calculate aW
        for (int ax = 0, i = constraints.getColumnIndex(); ax < constraints.getColumnSpan(); i++, ax++) w += columnWidths[i];
        // Calculate aY
        for (int i = 0; i < constraints.getRowIndex(); i++) y += rowHeights[i];
        // Calculate aH
        for (int ay = 0, i = constraints.getRowIndex(); ay < constraints.getRowSpan(); i++, ay++) h += rowHeights[i];
        // Calculate insets
        x += constraints.getInsetLeft();
        w -= constraints.getInsetLeft() + constraints.getInsetRight();
        y += constraints.getInsetTop();
        h -= constraints.getInsetTop() + constraints.getInsetRight();
        // Additional bounds check, if the width is zero and the inset is positive, a negative width is calculated.
        if (w < 0) w = 0;
        if (h < 0) h = 0;
        nodeArea.setBounds(x, y, w, h);
    }

    private double getPrefWidth(Node child)
    {
        if (child instanceof Control)
        {
            Control control = (Control) child;
            // Check if the "preferred size" is set
            if (control.getPrefWidth() == USE_COMPUTED_SIZE) return control.prefWidth(control.getHeight());
            return ((Control) child).getPrefWidth();
        }
        else return child.prefHeight(-1);
    }


    private double getPrefHeight(Node child)
    {
        if (child instanceof Control)
        {
            Control control = (Control) child;
            // Check if the "preferred size" is set
            if (control.getPrefHeight() == USE_COMPUTED_SIZE) return control.prefHeight(control.getWidth());
            return ((Control) child).getPrefHeight();
        }
        else return child.prefHeight(-1);
    }

    private void calculateColumnWidths()
    {
        TablePaneDefinition column;
        // Check if arrays should be created again - if it already exists fill it with zero too avoid wrong calc.
        if (columnWidths == null || columnWidths.length != columnList.size()) columnWidths = new double[columnList.size()];
        else Arrays.fill(columnWidths, 0);
        // Calculate remaining size.
        double    remainingWidth       = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double    preferredWidth;
        final int COLUMN_WIDTHS_LENGTH = columnWidths.length;
        int       calculatedCounter    = 0;
        // Calculate absolute.
        for (int i = 0; i < COLUMN_WIDTHS_LENGTH; i++)
        {
            column = columnList.get(i);
            // Check if absolute - else continue
            if (!column.isAbsolute()) continue;

            preferredWidth = column.getSize();

            // The max size is ignored in an absolute environment.
            // Check if the width must be wrapped to fit into the remaining space.
            // That is only the case if a min size is not set.
            if (preferredWidth > remainingWidth) preferredWidth = remainingWidth;
            if (column.isMinSizeSet() && column.getMinSize() > preferredWidth) preferredWidth = column.getMinSize();
            columnWidths[i] = preferredWidth;
                              remainingWidth -= preferredWidth;
            calculatedCounter++;
            // We can't break here if no space is remaining, because that could create an ugly looking behaviour of spanning children.
        }
        // Check if anything is calculated
        if (calculatedCounter == COLUMN_WIDTHS_LENGTH) return;

        // Calculate auto size.
        double               preferredChildWidth;
        TablePaneConstraints constraints;

        for (int i = 0; i < COLUMN_WIDTHS_LENGTH; i++)
        {
            column = columnList.get(i);
            // Check if absolute - else continue
            if (!column.isAutoSize()) continue;
            preferredWidth = 0;
            for (Node child : getChildren())
            {
                // If the child is not visible it is not valid.
                if (!child.isVisible()) continue;
                constraints = getConstraints(child);
                // Check if node is valid - if not continue
                if (!(constraints.getColumnIndex() == i && constraints.getColumnSpan() == 1)) continue;
                // Calculate the child pref width with insets - the space that is necessary for the node to be represented right.
                // The size is increased by one, because some nodes do some floating errors, while calculating their preferred size.
                preferredChildWidth = getPrefWidth(child) + constraints.getInsetLeft() + constraints.getInsetRight() + 1;
                // Set the new calculated values.
                if (preferredWidth < preferredChildWidth) preferredWidth = preferredChildWidth;
            }
            // Add the additional size
            preferredWidth += column.getSize();

            // Check if a max size is set
            if (column.isMaxSizeSet() && column.getMaxSize() < preferredWidth) preferredWidth = column.getMaxSize();
            // Check if a min size is set
            if (preferredWidth > remainingWidth) preferredWidth = remainingWidth;
            if (column.isMinSizeSet() && column.getMinSize() > preferredWidth) preferredWidth = column.getMinSize();
            columnWidths[i] = preferredWidth;
                              remainingWidth -= preferredWidth;
            calculatedCounter++;
        }
        // Check if anything is calculated
        if (calculatedCounter == COLUMN_WIDTHS_LENGTH) return;
        // Calculate relative.
        for (int i = 0; i < COLUMN_WIDTHS_LENGTH; i++)
        {
            column = columnList.get(i);
            // Check if relative
            if (!column.isRelative()) continue;

            preferredWidth = remainingWidth * column.getSize();

            // Check if the width must be wrapped to fit into the remaining space.
            // That is only the case if a min size is not set.
            if (column.isMaxSizeSet() && column.getMaxSize() < preferredWidth) preferredWidth = column.getMaxSize();
            if (preferredWidth > remainingWidth) preferredWidth = remainingWidth;
            if (column.isMinSizeSet() && column.getMinSize() > preferredWidth) preferredWidth = column.getMinSize();

            columnWidths[i] = preferredWidth;
                              remainingWidth -= preferredWidth;
            // We can't break here if no space is remaining, because that could create an ugly looking behaviour of spanning children.
        }
    }

    private void calculateRowHeights()
    {
        TablePaneDefinition row;
        // Check if arrays should be created again - if it already exists fill it with zero too avoid wrong calc.
        if (rowHeights == null || rowHeights.length != rowList.size()) rowHeights = new double[rowList.size()];
        else Arrays.fill(rowHeights, 0);
        // Calculate remaining size.
        double    remainingHeight    = getHeight() - getInsets().getTop() - getInsets().getBottom();
        double    preferredHeight;
        final int ROW_HEIGHTS_LENGTH = rowHeights.length;
        int       calculatedCounter  = 0;
        // Calculate absolute.
        for (int i = 0; i < ROW_HEIGHTS_LENGTH; i++)
        {
            row = rowList.get(i);
            // Check if absolute - else continue
            if (!row.isAbsolute()) continue;

            preferredHeight = row.getSize();

            // The max size is ignored in an absolute environment.
            // Check if the width must be wrapped to fit into the remaining space.
            // That is only the case if a min size is not set.
            if (preferredHeight > remainingHeight) preferredHeight = remainingHeight;
            if (row.isMinSizeSet() && row.getMinSize() > preferredHeight) preferredHeight = row.getMinSize();
            rowHeights[i] = preferredHeight;
                            remainingHeight -= preferredHeight;
            calculatedCounter++;
            // We can't break here if no space is remaining, because that could create an ugly looking behaviour of spanning children.
        }
        // Check if anything is calculated
        if (calculatedCounter == ROW_HEIGHTS_LENGTH) return;

        // Calculate auto size.
        double               preferredChildHeight;
        TablePaneConstraints constraints;

        for (int i = 0; i < ROW_HEIGHTS_LENGTH; i++)
        {
            row = rowList.get(i);
            // Check if absolute - else continue
            if (!row.isAutoSize()) continue;
            preferredHeight = 0;
            for (Node child : getChildren())
            {
                // If the child is not visible it is not valid.
                if (!child.isVisible()) continue;
                constraints = getConstraints(child);
                // Check if node is valid - if not continue
                if (!(constraints.getRowIndex() == i && constraints.getRowSpan() == 1)) continue;
                // Calculate the child pref width with insets - the space that is necessary for the node to be represented right.
                // The size is increased by one, because some nodes do some floating errors, while calculating their preferred size.
                preferredChildHeight = getPrefHeight(child) + constraints.getInsetTop() + constraints.getInsetBottom() + 1;
                // Set the new calculated values.
                if (preferredHeight < preferredChildHeight) preferredHeight = preferredChildHeight;
            }
            // Add the additional size
            preferredHeight += row.getSize();

            // Check if a max size is set
            if (row.isMaxSizeSet() && row.getMaxSize() < preferredHeight) preferredHeight = row.getMaxSize();
            // Check if a min size is set
            if (preferredHeight > remainingHeight) preferredHeight = remainingHeight;
            if (row.isMinSizeSet() && row.getMinSize() > preferredHeight) preferredHeight = row.getMinSize();
            rowHeights[i] = preferredHeight;
                            remainingHeight -= preferredHeight;
            calculatedCounter++;
        }
        // Check if anything is calculated
        if (calculatedCounter == ROW_HEIGHTS_LENGTH) return;
        // Calculate relative.
        for (int i = 0; i < ROW_HEIGHTS_LENGTH; i++)
        {
            row = rowList.get(i);
            // Check if relative
            if (!row.isRelative()) continue;

            preferredHeight = remainingHeight * row.getSize();

            // Check if the width must be wrapped to fit into the remaining space.
            // That is only the case if a min size is not set.
            if (row.isMaxSizeSet() && row.getMaxSize() < preferredHeight) preferredHeight = row.getMaxSize();
            if (preferredHeight > remainingHeight) preferredHeight = remainingHeight;
            if (row.isMinSizeSet() && row.getMinSize() > preferredHeight) preferredHeight = row.getMinSize();

            rowHeights[i] = preferredHeight;
                            remainingHeight -= preferredHeight;
            // We can't break here if no space is remaining, because that could create an ugly looking behaviour of spanning children.
        }
    }
}

