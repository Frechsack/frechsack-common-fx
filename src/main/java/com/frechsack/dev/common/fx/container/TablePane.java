package com.frechsack.dev.common.fx.container;

import com.frechsack.dev.common.fx.geometry.WriteableRectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablePane extends Pane implements TablePaneConstants
{
    private static final String                    TABLE_PANE_CONSTRAINTS_KEY = "table-pane-constraints";
    private final        List<TablePaneDefinition> rowList                    = new ArrayList<>();
    private final        List<TablePaneDefinition> columnList                 = new ArrayList<>();
    private final WriteableRectangle2D nodeArea   = new WriteableRectangle2D(0, 0, 0, 0);
    private final WriteableRectangle2D nodeBounds = new WriteableRectangle2D(0, 0, 0, 0);
    private       double[]             columnWidths;
    private              double[]                  rowHeights;

    public TablePane(TablePaneDefinition... tablePaneDefinitions)
    {
        for (TablePaneDefinition def : tablePaneDefinitions)
            if (def instanceof TablePaneColumn) columnList.add(def);
            else if (def instanceof TablePaneRow) rowList.add(def);
            else if (columnList.size() > rowList.size()) rowList.add(def);
            else columnList.add(def);

    }

    public void add(Node child, TablePaneConstraints constraints)
    {
        setConstraints(child, constraints);
        getChildren().add(child);
    }

    public static void setConstraints(Node node, TablePaneConstraints constraints)
    {
        if (constraints == null) node.getProperties().remove(TABLE_PANE_CONSTRAINTS_KEY);
        else node.getProperties().put(TABLE_PANE_CONSTRAINTS_KEY, constraints);
    }

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


    /* ******************************************************************************
     *                                                                             *
     * Layout Calculation
     *                                                                             *
     ******************************************************************************/

    @Override
    protected void layoutChildren()
    {
        // Calculation:
        // Calculate Columns and Rows sizes
        calculateColumnWidths();
        calculateRowHeights();

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
        if (constraints.isSizingBoth()) nodeBounds.setBounds(nodeArea.getMinX(), nodeArea.getMinY(), nodeArea.getWidth(), nodeArea.getHeight());
        if (constraints.isSizingHorizontal())
        {
            nodeBounds.setWidth(nodeArea.getWidth());
            nodeBounds.setHeight(getPrefHeight(child));
            nodeBounds.setMinX(nodeArea.getMinX());
            // Anchors
            if (constraints.isAnchorTop()) nodeBounds.setMinY(nodeArea.getMinY());
            if (constraints.isAnchorBottom()) nodeBounds.setMinY(nodeArea.getMaxY() - nodeBounds.getHeight());
        }
        else if (constraints.isSizingVertical())
        {
            nodeBounds.setHeight(nodeArea.getHeight());
            nodeBounds.setWidth(Math.min(nodeArea.getWidth(), getPrefHeight(child)));
            nodeBounds.setMinY(nodeArea.getMinY());
            // Anchors
            if (constraints.isAnchorLeft()) nodeBounds.setMinX(nodeArea.getMinX());
            if (constraints.isAnchorRight()) nodeBounds.setMinX(nodeArea.getMaxX() - nodeBounds.getWidth());
        }
        else
        {
            nodeBounds.setHeight(nodeArea.getHeight());
            nodeBounds.setWidth(nodeArea.getWidth());
            // Anchors
            if (constraints.isAnchorLeft()) nodeBounds.setMinX(nodeArea.getMinX());
            if (constraints.isAnchorRight()) nodeBounds.setMinX(nodeArea.getMaxX() - nodeBounds.getWidth());
            if (constraints.isAnchorTop()) nodeBounds.setMinY(nodeArea.getMinY());
            if (constraints.isAnchorBottom()) nodeBounds.setMinY(nodeArea.getMaxY() - nodeBounds.getHeight());
        }
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
        w -= constraints.getInsetLeft() - constraints.getInsetRight();
        y += constraints.getInsetTop();
        h -= constraints.getInsetTop() - constraints.getInsetRight();
        // Additional bounds check, if the width is zero and the inset is positive, a negative width is calculated.
        if (w < 0) w = 0;
        if (h < 0) h = 0;
        nodeArea.setBounds(x, y, w, h);
    }

    private double getPrefWidth(Node child)
    {
        if (child instanceof Control) return
                ((Control) child).getPrefWidth();
        else return child.prefWidth(-1);
    }

    private double getPrefHeight(Node child)
    {
        if (child instanceof Control) return
                ((Control) child).getPrefHeight();
        else return child.prefHeight(-1);
    }

    private void calculateColumnWidths()
    {
        TablePaneDefinition column;
        // Check if arrays should be created again - if it already exists fill it with zero too avoid wrong calc.
        if (columnWidths == null || columnWidths.length != columnList.size()) columnWidths = new double[columnList.size()];
        else Arrays.fill(columnWidths, 0);
        // Calculate remaining width;
        double    remainingWidth       = getWidth() - getInsets().getLeft() - getInsets().getRight();
        final int COLUMN_WIDTHS_LENGTH = columnWidths.length;
        // Calculate columns with absolute width
        for (int i = 0; i < COLUMN_WIDTHS_LENGTH; i++)
        {
            column = columnList.get(i);
            // Check if absolute, else continue.
            if (column.getType() != ABSOLUTE) continue;
            // Check if remaining width is bigger than the column size;
            if (column.getSize() <= remainingWidth)
            {
                columnWidths[i] = column.getSize();
                remainingWidth -= column.getSize();
            }
            else
            {
                columnWidths[i] = remainingWidth;
                // We can return here, because there is no available space anymore.
                return;
            }
        }
        // Check if space is still available
        if (remainingWidth == 0) return;
        // Calculate columns with auto size.
        TablePaneConstraints constraints;
        double               maxWidth = 0;
        double               childWidth;
        for (int i = 0; i < COLUMN_WIDTHS_LENGTH; i++)
        {
            column = columnList.get(i);
            // Check if auto size
            if (column.getType() != AUTO_SIZE) continue;
            // Get the widest node. Valid Nodes:
            // 1x1 span in a auto size column.

            for (Node child : getChildren())
            {
                constraints = getConstraints(child);
                // Check if node is valid - if not continue
                if (!(constraints.getColumnIndex() == i && constraints.getColumnSpan() == 1)) continue;
                // Calculate the child pref width with insets - the space that is necessary for the node to be represented right.
                childWidth = getPrefWidth(child) + constraints.getInsetLeft() + constraints.getInsetRight();
                // Set the new max width.
                if (maxWidth < childWidth) maxWidth = childWidth;
            }
            // Add the additional size amount
            maxWidth += column.getSize();
            // Check if the calculated max width is valid
            if (column.isMaxSizeSet() && maxWidth > column.getMaxSize()) maxWidth = column.getMaxSize();
            if (column.isMinSizeSet() && maxWidth < column.getMinSize()) maxWidth = column.getMinSize();
            // Assign
            if (maxWidth <= remainingWidth)
            {
                columnWidths[i] = maxWidth;
                                  remainingWidth -= maxWidth;
            }
            else
            {
                columnWidths[i] = remainingWidth;
                // We can return here, because there is no available space anymore.
                return;
            }
        }
        // Check if space is still available
        if (remainingWidth == 0) return;
        // Calculate columns with relative size.
        for (int i = 0; i < COLUMN_WIDTHS_LENGTH; i++)
        {
            column   = columnList.get(i);
            maxWidth = remainingWidth * column.getSize();
            // Check if the calculated max width is valid
            if (column.isMaxSizeSet() && maxWidth > column.getMaxSize()) maxWidth = column.getMaxSize();
            if (column.isMinSizeSet() && maxWidth < column.getMinSize()) maxWidth = column.getMinSize();
            // Assign
            if (maxWidth <= remainingWidth)
            {
                columnWidths[i] = maxWidth;
                                  remainingWidth -= maxWidth;
            }
            else
            {
                columnWidths[i] = remainingWidth;
                // We can return here, because there is no available space anymore.
                return;
            }
        }

    }

    private void calculateRowHeights()
    {
        TablePaneDefinition row;
        // Check if arrays should be created again - if it already exists fill it with zero too avoid wrong calc.
        if (rowHeights == null || rowHeights.length != rowList.size()) rowHeights = new double[rowList.size()];
        else Arrays.fill(columnWidths, 0);
        // Calculate remaining width;
        double    remainingHeight    = getHeight() - getInsets().getTop() - getInsets().getBottom();
        final int ROW_HEIGHTS_LENGTH = rowHeights.length;
        // Calculate columns with absolute width
        for (int i = 0; i < ROW_HEIGHTS_LENGTH; i++)
        {
            row = rowList.get(i);
            // Check if absolute, else continue.
            if (row.getType() != ABSOLUTE) continue;
            // Check if remaining width is bigger than the column size;
            if (row.getSize() <= remainingHeight)
            {
                rowHeights[i] = row.getSize();
                remainingHeight -= row.getSize();
            }
            else
            {
                rowHeights[i] = remainingHeight;
                // We can return here, because there is no available space anymore.
                return;
            }
        }
        // Check if space is still available
        if (remainingHeight == 0) return;
        // Calculate columns with auto size.
        TablePaneConstraints constraints;
        double               maxHeight = 0;
        double               childHeight;
        for (int i = 0; i < ROW_HEIGHTS_LENGTH; i++)
        {
            row = rowList.get(i);
            // Check if auto size
            if (row.getType() != AUTO_SIZE) continue;
            // Get the widest node. Valid Nodes:
            // 1x1 span in a auto size column.

            for (Node child : getChildren())
            {
                constraints = getConstraints(child);
                // Check if node is valid - if not continue
                if (!(constraints.getRowIndex() == i && constraints.getRowSpan() == 1)) continue;
                // Calculate the child pref width with insets - the space that is necessary for the node to be represented right.
                childHeight = getPrefHeight(child) + constraints.getInsetTop() + constraints.getInsetBottom();
                // Set the new max width.
                if (maxHeight < childHeight) maxHeight = childHeight;
            }
            // Add the additional size amount
            maxHeight += row.getSize();
            // Check if the calculated max width is valid
            if (row.isMaxSizeSet() && maxHeight > row.getMaxSize()) maxHeight = row.getMaxSize();
            if (row.isMinSizeSet() && maxHeight < row.getMinSize()) maxHeight = row.getMinSize();
            // Assign
            if (maxHeight <= remainingHeight)
            {
                rowHeights[i] = maxHeight;
                                remainingHeight -= maxHeight;
            }
            else
            {
                rowHeights[i] = remainingHeight;
                // We can return here, because there is no available space anymore.
                return;
            }
        }
        // Check if space is still available
        if (remainingHeight == 0) return;
        // Calculate columns with relative size.
        for (int i = 0; i < ROW_HEIGHTS_LENGTH; i++)
        {
            row       = rowList.get(i);
            maxHeight = remainingHeight * row.getSize();
            // Check if the calculated max width is valid
            if (row.isMaxSizeSet() && maxHeight > row.getMaxSize()) maxHeight = row.getMaxSize();
            if (row.isMinSizeSet() && maxHeight < row.getMinSize()) maxHeight = row.getMinSize();
            // Assign
            if (maxHeight <= remainingHeight)
            {
                rowHeights[i] = maxHeight;
                                remainingHeight -= maxHeight;
            }
            else
            {
                rowHeights[i] = remainingHeight;
                // We can return here, because there is no available space anymore.
                return;
            }
        }
    }


}

