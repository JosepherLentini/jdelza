package com.jdelza.model.items;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.GameColor;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.interfaces.Storable;

public class Rupie extends Item {

    protected int quantity;

    /**
     * Contructor
     * @param position
     */
    public Rupie(Position position, int quantity) {
        super(position,ItemType.RUPEE);
        this.quantity = quantity;
    }



    //set methods
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public int getQuantity() {return quantity;}

    /**
     * Method that allow increments rupies
     * @param player
     */
    public void incrementRupies(Player player){
        player.setNumberOfRupies(player.getNumberOfRupies()+quantity);
    }






}
