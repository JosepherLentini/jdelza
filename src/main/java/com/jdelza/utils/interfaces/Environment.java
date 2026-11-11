package com.jdelza.utils.interfaces;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;

import java.util.List;

public interface Environment {
    Position getEntryPosition();
    List<Position> getExitPosition();
    Position getEnvironmentPosition();
    EnvironmentType getEnvironmentType();
    Directions getExitDirection();
}
