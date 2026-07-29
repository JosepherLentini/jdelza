package com.jdelza.model.items;

import com.jdelza.utils.enums.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.GameColor;

public class Rupie extends Item{

    private GameColor rupieColor;
    private int value;

    /**
     * Contructor
     * @param position
     * @param rupieColor
     */
    public Rupie(Coordinates position, GameColor rupieColor) {
        super(position);
        this.rupieColor = rupieColor;
        this.value = rupieColor == GameColor.RED ? 1 : 5;
    }


    /**
     * Method that allow increments rupies
     * @param player
     */
    public void incrementRupies(Player player){
        player.setNumberOfRupies(player.getNumberOfRupies()+this.value);
    }


    @Override
    public void use() {

    }
}
