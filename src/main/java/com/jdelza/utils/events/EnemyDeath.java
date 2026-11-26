package com.jdelza.utils.events;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.entities.Position;

import java.util.List;

/**
 * This class is used to create an event tah occurs when an enemy is dead
 */
public class EnemyDeath {

    //private List<Enemy> deathEnemies;
    private Enemy deathEnemy;
    private int playerScore;
    private Position deathEnemyPosition;



    public EnemyDeath(Enemy deathEnemy, int playerScore, Position deathEnemyPosition) {
        //this.deathEnemies = deathEnemies;
        this.deathEnemy = deathEnemy;
        this.playerScore = playerScore;
        this.deathEnemyPosition = deathEnemyPosition;
    }

    public Enemy getDeathEnemy() {
        return deathEnemy;
    }
    public int getPlayerScore() {return playerScore;}

    public Position getDeathEnemyPosition() {
        return deathEnemyPosition;
    }
}
