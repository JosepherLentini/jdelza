package com.jdelza.utils.enums;

public enum Dimensions {

    // 1. LOGICAL CONSTANTS (Used by both Model and View)
    MAP_ROWS(8),
    MAP_COLUMNS(16),
    ZONE_ROWS(11),
    ZONE_COLUMNS(16),
    LOGICAL_TILE_SIZE(16),
    LOGICAL_SCREEN_WIDTH(256),
    LOGICAL_SCREEN_HEIGHT(176),

    // 2. RENDERING CONSTANTS (Used ONLY by the View)
    SCALE(4),
    RENDER_SCREEN_WIDTH(288 * 4),
    RENDER_SCREEN_HEIGHT(176 * 4),

    MAP_WIDTH(288*4),
    MAP_HEIGHT(495), //123.75*4


    //3. Tile sizes
    TILE_WIDTH(72),
    TILE_HEIGT(45);


    private final int value;

    Dimensions(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }


}