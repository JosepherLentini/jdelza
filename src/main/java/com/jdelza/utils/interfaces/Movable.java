package com.jdelza.utils.interfaces;

import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.Directions;

public interface Movable {

    void move(Directions direction);
    void move(Zone currentZone);
}
