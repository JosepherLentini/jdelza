package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.model.world.WorldMap;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;


public class EnterOverworld extends EnterEnvironment {

    private EnvironmentType environmentType;
    private Position playerEntryPosition;



    public EnterOverworld(WorldMap overworld, Directions playerDirection) {
        super(overworld, playerDirection);
        this.playerEntryPosition = overworld.getEntryPosition();

        this.environmentType = EnvironmentType.OVERWORLD;
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return this.environmentType;
    }

    public Position getPlayerEntryPosition() {
        return playerEntryPosition;
    }


}
