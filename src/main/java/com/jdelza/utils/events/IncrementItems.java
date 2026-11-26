package com.jdelza.utils.events;

import com.jdelza.utils.enums.ItemType;

/**
 * This class is called whe items changed his quantity
 */
public class IncrementItems {

    private ItemType itemType;
    private double finalItemQuantity;
    private double maxQuantity;

    public IncrementItems(ItemType itemType, double finalItemQuantity) {
        this.itemType = itemType;
        this.finalItemQuantity = finalItemQuantity;
    }

    public IncrementItems(ItemType itemType, double maxQuantity, double finalItemQuantity) {
        this.itemType = itemType;
        this.finalItemQuantity = finalItemQuantity;
        this.maxQuantity = maxQuantity;
    }

    //Get methods
    public ItemType getItemType() {return itemType;}
    public double getFinalItemQuantity() {return finalItemQuantity;}
    public double getMaxQuantity() {return maxQuantity;}
}
