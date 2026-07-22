package com.jdelza.model.items;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.model.interfaces.Usable;

public abstract class Item extends Entity implements Usable {

    public Item(Coordinates position) {
        super(position);
    }





}
