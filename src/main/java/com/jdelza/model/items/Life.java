package com.jdelza.model.items;

import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.interfaces.Buyable;

public class Life extends Item implements Buyable {

    private final int cost = 10;

    public Life(Position position) {
        super(position, ItemType.LIFE);
    }

    //Get methods
    @Override
    public int getCost() {
        return cost;
    }


}
