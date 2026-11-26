package com.jdelza.utils.events;

import com.jdelza.utils.enums.ItemType;

/**
 * This class is called when player buy something in a shop
 */
public class BuyItemEvent {

    private int remainingRupies;
    private ItemType itemType;

    public BuyItemEvent(int itemCost, ItemType itemType) {
        this.remainingRupies = itemCost;
        this.itemType = itemType;
    }

    //Get methods
    public int getRemainingRupies() {return remainingRupies;}
    public ItemType getItemType() {return itemType;}
}
