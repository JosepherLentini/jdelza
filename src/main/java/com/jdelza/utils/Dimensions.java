package com.jdelza.utils;

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
    MAP_HEIGHT(124*4);

    private final double value;

    Dimensions(double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    public int getInt() {
        return (int) value;
    }
}