package com.jdelza.utils.enums;

public enum Dimensions {

    // 1. LOGICAL CONSTANTS (Used by both Model and View)
    MAP_ROWS(8),
    MAP_COLUMNS(16),
    ZONE_ROWS(11),
    ZONE_COLUMNS(16),

    SHOP_ROWS(1),
    SHOP_COLUMNS(1),

    DUNGEON_1_ROWS(3),
    DUNGEON_1_COLUMNS(3),

    // 2. RENDERING CONSTANTS (Used ONLY by the View)
    SCALE(4),
    RENDER_SCREEN_WIDTH(288 * 4),
    RENDER_SCREEN_HEIGHT(176 * 4),

    MAP_WIDTH(288*4),
    MAP_HEIGHT(495), //123.75*4


    //3. Tile sizes
    TILE_WIDTH(72),
    TILE_HEIGT(45),


    //Status dimensions
    STATUS_BAR_HEIGT(208),
    STATUS_MAP_HEIGT(168),
    STATUS_MAP_WIDTH(308);



    private final int value;

    Dimensions(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }


}