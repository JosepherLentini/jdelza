package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.NPCType;
import com.jdelza.utils.interfaces.EnvironmentSwitcher;

public class EnterOverworld implements EnvironmentSwitcher {

    private EnvironmentType environmentType;
    private Position playerEntryPosition;

    public EnterOverworld(Position playerEntryPosition) {
        this.playerEntryPosition = playerEntryPosition;
        this.environmentType= EnvironmentType.OVERWORLD;
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return this.environmentType;
    }

    public Position getPlayerEntryPosition() {
        return playerEntryPosition;
    }
}
