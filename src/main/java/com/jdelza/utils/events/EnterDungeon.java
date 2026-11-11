package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.model.world.DungeonMap;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.Level;
import com.jdelza.utils.interfaces.EnvironmentSwitcher;

public class EnterDungeon implements EnvironmentSwitcher {

    private Level dungeonLevel;
    private Position entryPosition;
    private Directions playerDirection;

    public EnterDungeon(DungeonMap dungeon, Directions playerDirection) {
        this.dungeonLevel = dungeon.getDungeonLevel();
        this.entryPosition = dungeon.getEntryPosition();
        this.playerDirection = playerDirection;
    }

    public Level getDungeonLevel() {
        return dungeonLevel;
    }

    public Position getEntryPosition() {
        return entryPosition;
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return EnvironmentType.DUNGEON;
    }

    public Directions getPlayerDirection() {
        return playerDirection;
    }
}
