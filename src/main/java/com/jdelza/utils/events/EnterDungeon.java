package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.model.world.DungeonMap;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.Level;


public class EnterDungeon extends EnterEnvironment {

    private Level dungeonLevel;
    private Position entryPosition;

    public EnterDungeon(DungeonMap dungeon, Directions playerDirection) {
        super(dungeon, playerDirection);
        this.dungeonLevel = dungeon.getDungeonLevel();
        this.entryPosition = dungeon.getEntryPosition();

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


}
