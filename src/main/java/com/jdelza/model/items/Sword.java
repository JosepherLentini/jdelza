package com.jdelza.model.items;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.ItemType;

/**
 * This class represent sword item type
 */
public class Sword extends Item{

    /**
     * Constructor
     * @param position item position in the map
     */
    public Sword(Position position) {
        super(position, ItemType.SWORD);
    }


}
