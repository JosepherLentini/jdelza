package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;
import com.jdelza.model.world.ShopMap;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.NPCType;
import com.jdelza.utils.interfaces.EnvironmentSwitcher;

/**
 *This class represent the event to enter in a shop
 */
public class EnterTheShop implements EnvironmentSwitcher {

    private EnvironmentType environmentType;
    private NPCType npcType;
    private ItemType itemType;
    private Position playerEntryPosition;


    public EnterTheShop(ShopMap shopMap) {
        this.environmentType = shopMap.getEnvironmentType();
        this.npcType = shopMap.getNpcType();
        this.itemType = shopMap.getItemType();
        this.playerEntryPosition = shopMap.getEntryPosition();
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public NPCType getNpcType() {
        return npcType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Position getPlayerEntryPosition() {
        return playerEntryPosition;
    }
}
