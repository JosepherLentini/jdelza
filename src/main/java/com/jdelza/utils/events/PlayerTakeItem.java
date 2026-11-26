package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.model.world.GameMap;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.StartEndStatus;

public class PlayerTakeItem {

    private ItemType itemType;
    private StartEndStatus status = StartEndStatus.START;
    private boolean isShop;
    private Position position;
    private int itemQuantity;
    public PlayerTakeItem(ItemType itemType, StartEndStatus status, boolean isShop) {
        this.itemType = itemType;
        this.status = status;
        this.isShop = isShop;
    }

    public PlayerTakeItem(ItemType itemType, boolean isShop, Position position ) {
        this.itemType = itemType;
        this.isShop = isShop;
        this.position = position;
    }

    public PlayerTakeItem(ItemType itemType, int itemQuantity,  boolean isShop, Position position) {
        this.itemType = itemType;
        this.isShop = isShop;
        this.position = position;
        this.itemQuantity = itemQuantity;
    }

    public PlayerTakeItem(ItemType item, StartEndStatus status) {
        this.itemType = item;
        this.status = status;
    }

    //Get methods
    public ItemType getItem() {
        return itemType;
    }
    public StartEndStatus getStatus() {
        return status;
    }
    public boolean isShop() {
        return isShop;
    }
    public Position getPosition() {
        return position;
    }
    public int getItemQuantity(){return itemQuantity;}


}
