package com.jdelza.model.enemies;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.EnemyType;
import com.jdelza.utils.enums.GameColor;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.interfaces.Movable;

import java.util.Random;

public class Tektite extends Enemy implements Movable {

    public Tektite(Coordinates position/* Weapon weapon*/, GameColor color, EnemyType enemyType) {
        super(position, /*weapon,*/ color, enemyType);
    }


    @Override
    public void move(Zone zone) {
        if (super.getSteps() == 0){
            this.setPosition(chooseNewCoordinates());
            chooseandSetNuberOfSteps();
        }
        else{
            this.setSteps(this.getSteps() - 1);
        }
    }

    @Override
    public boolean collisionWithPlayer(Player player) {
        return getPosition().equals(player.getPosition());
    }


    private Coordinates chooseNewCoordinates(){
        Random random = new Random();

       int nextX = random.nextInt(Dimensions.MAP_COLUMNS.get()-1);
       int nextY = random.nextInt(Dimensions.MAP_ROWS.get()-1);

       return new Coordinates(nextX, nextY);

    }
}
