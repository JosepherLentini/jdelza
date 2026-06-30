package com.jdelza.model.characters;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;

public class Character extends Entity {

    public Character(Coordinates position) {
        super(position);
    }

    public Character(int x, int y) {
        super(x, y);
    }
}
