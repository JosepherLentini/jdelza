package com.jdelza.utils.events;

import com.jdelza.model.characters.Enemy;
import java.util.List;

public class EnemyDeath {

    //private List<Enemy> deathEnemies;
    private Enemy deathEnemy;

    public EnemyDeath(/*List<Enemy> deathEnemies*/ Enemy deathEnemy) {
        //this.deathEnemies = deathEnemies;
        this.deathEnemy = deathEnemy;
    }

    public Enemy getDeathEnemy() {
        return deathEnemy;
    }
}
