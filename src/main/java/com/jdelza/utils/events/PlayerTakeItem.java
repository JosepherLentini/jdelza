package com.jdelza.utils.events;

import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.StartEndStatus;

public class PlayerTakeItem {

    private ItemType item;
    private StartEndStatus status;

    public PlayerTakeItem(ItemType item, StartEndStatus status) {
        this.item = item;
        this.status = status;
    }

    public ItemType getItem() {
        return item;
    }

    public StartEndStatus getStatus() {
        return status;
    }
}
