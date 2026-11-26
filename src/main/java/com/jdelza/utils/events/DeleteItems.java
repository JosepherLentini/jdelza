package com.jdelza.utils.events;

import com.jdelza.model.entities.Position;

public class DeleteItems {

    private Position itemPosition;

    public DeleteItems(Position itemPosition) {
        this.itemPosition = itemPosition;
    }


    public Position getItemPosition() {
        return itemPosition;
    }
}
