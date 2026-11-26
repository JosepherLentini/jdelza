package com.jdelza.utils.events;

import com.jdelza.model.world.GameMap;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;

/**
 *
 */
public abstract class EnterEnvironment {

    private Directions playerDirection;
    private int environmentColumns;
    private int environmentRows;


    public EnterEnvironment(GameMap gameMap, Directions playerDirection) {
        this.playerDirection = playerDirection;
        this.environmentColumns = gameMap.getMapColumns();
        this.environmentRows = gameMap.getMapRows();

    }

    public Directions getPlayerDirection() {
        return playerDirection;
    }

    public int getEnvironmentColumns() {
        return environmentColumns;
    }

    public int getEnvironmentRows() {
        return environmentRows;
    }

    public abstract EnvironmentType getEnvironmentType();


}
