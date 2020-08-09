package com.frechsack.dev.common.fx.container;


public interface TablePaneConstants
{

    byte NORTH      = 1;

    byte EAST       = 2;

    byte SOUTH      = 4;

    byte WEST       = 8;

    byte CENTER     = 0;

    byte NORTH_EAST = NORTH + EAST;

    byte EAST_NORTH = NORTH_EAST;

    byte EAST_SOUTH = EAST + SOUTH;

    byte SOUTH_EAST = EAST_SOUTH;

    byte SOUTH_WEST = SOUTH + WEST;

    byte WEST_SOUTH = SOUTH_WEST;

    byte WEST_NORTH = WEST + NORTH;

    byte NORTH_WEST = WEST_NORTH;

    byte HORIZONTAL = 1;

    byte VERTICAL   = 2;

    byte BOTH       = HORIZONTAL + VERTICAL;

    byte NONE       = 0;

    byte PERCENT  = 0;

    byte ABSOLUTE = 1;

    byte AUTO_SIZE = 2;
}
