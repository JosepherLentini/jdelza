package com.jdelza.utils.events;

import com.jdelza.model.characters.Enemy;

import java.util.List;

public class EnemyMovement {

    private List<Enemy> enemies;

    public EnemyMovement(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
}
