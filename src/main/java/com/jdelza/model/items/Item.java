package com.jdelza.model.items;

import com.jdelza.model.entities.Entity;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.ItemType;
import java.util.Objects;

public abstract class Item extends Entity {

    protected Position zoneCellposition;
    protected ItemType itemType;


    public Item(Position zoneCellposition, ItemType itemType) {

        super(zoneCellposition.getZoneCoordinates());

        this.zoneCellposition = zoneCellposition;
        this.itemType = itemType;
    }

    //Get methods
    public ItemType getItemType() {return itemType;}

    public Position getZoneCellPosition(){return this.zoneCellposition;}




    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(zoneCellposition, item.zoneCellposition) && itemType == item.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneCellposition, itemType);
    }





}
