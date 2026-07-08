package com.jdelza.model.enemies;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.enums.Directions;
import com.jdelza.model.enums.EnemyType;
import com.jdelza.model.enums.SkinColor;
import com.jdelza.model.weapons.Weapon;

public class Tektite extends Enemy {

    public Tektite(Coordinates position, Weapon weapon, SkinColor color, EnemyType enemyType) {
        super(position, weapon, color, enemyType);
    }


    @Override
    public void move(Directions direction) {

    }
}
