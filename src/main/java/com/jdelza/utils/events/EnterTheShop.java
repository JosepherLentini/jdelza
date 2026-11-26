package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.model.world.ShopMap;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.NPCType;

/**
 *This class represent the event to enter in a shop
 */
public class EnterTheShop extends EnterEnvironment {

    private EnvironmentType environmentType;
    private NPCType npcType;
    private ItemType itemType;
    private Position playerEntryPosition;
    private Directions playerDirection;
    private int cost;


    public EnterTheShop(ShopMap shopMap, Directions playerDirection) {
        super(shopMap, playerDirection);
        this.environmentType = EnvironmentType.SHOP;
        this.npcType = shopMap.getNpcType();
        this.itemType = shopMap.getItemType();
        this.playerEntryPosition = shopMap.getEntryPosition();
        this.playerDirection = playerDirection;
        this.cost = shopMap.getCost();


    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    //Get methods
    public NPCType getNpcType() {
        return npcType;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public Position getPlayerEntryPosition() {
        return playerEntryPosition;
    }
    public Directions getPlayerDirection() {return playerDirection;}
    public int getCost(){return this.cost;}
}
