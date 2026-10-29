package com.jdelza.utils.interfaces;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.EnvironmentType;

public interface Environment {
    Position getEntryPosition();
    Position getExitPosition();
    Position getEnvironmentPosition();
    EnvironmentType getEnvironmentType();
}
