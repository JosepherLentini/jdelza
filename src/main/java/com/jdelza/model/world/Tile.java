package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.interfaces.Environment;

public class Tile extends Entity {


    private boolean walkable;
    private boolean portal;
    private Environment environment;
    private ItemType itemType;

    public Tile(Coordinates position) {
        super(position);

        this.walkable = true;
        this.portal = false;
    }

    //Get methods
    public boolean isWalkable() {return walkable;}
    public boolean isPortal() {return portal;}
    public Environment getEnvironment() {return environment;}
    public ItemType getItemType() {return itemType;}

    //Set methods
    public void setWalkable(boolean walkable) {this.walkable = walkable;}
    public void setPortal(boolean portal) {this.portal = portal;}
    public void setEnvironment(Environment environment) {this.environment = environment;setPortal(true);}
    public void setItemType(ItemType itemType) {this.itemType = itemType;}

    @Override
    public String toString(){
        return walkable ? "." : "X";
    }
}
