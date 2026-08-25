package com.jdelza.model.enemies;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnemyType;
import com.jdelza.utils.enums.GameColor;
import com.jdelza.model.weapons.Weapon;


public class Octorock extends Enemy{

    public Octorock(Coordinates position, Weapon weapon, GameColor color, EnemyType enemyType) {
        super(position, /*weapon,*/ color, enemyType);
    }


    /*
    @Override
    public void move(Directions direction) {

    }

     */
}
