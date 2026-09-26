package com.jdelza.model.enemies;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.*;
import com.jdelza.model.weapons.Weapon;

import java.util.Random;


public class Octorock extends Enemy{

    public Octorock(Coordinates position /*Weapon weapon*/, GameColor color, EnemyType enemyType) {
        super(position, /*weapon,*/ color, enemyType);
        super.setWeaponType(WeaponType.ROCK);
    }



    @Override
    public void move(Zone zone) {
        if (this.steps > 0){
            octorockWalk(zone);
        }
        else{
            this.chooseAndSetAnAction();
        }

    }




    public void chooseandSetNuberOfSteps(){
        Random random = new Random();
        int i = random.nextInt(10)+2;
        this.steps = i;

    }

    public void chooseAndSetAnAction(){
        Random random = new Random();
        int i = random.nextInt(10);
        this.enemyAction = i%2 == 0 ? Action.ATTACK : Action.WALK;

        if (this.getEnemyAction() ==  Action.WALK) {chooseandSetNuberOfSteps(); chooseAndSetDirection();}
    }

    public void chooseAndSetDirection(){
        Random random = new Random();
        int i = random.nextInt(4);
        this.enemyDirection = Directions.values()[i];
    }

    @Override
    public boolean collisionWithPlayer(Player player) {

        Coordinates newPosition = new Coordinates(0,0);

        switch (super.enemyDirection){
            case UP    -> newPosition =  new Coordinates(this.getPosition().getX(), this.getPosition().getY()-1);
            case DOWN  -> newPosition =  new Coordinates(this.getPosition().getX(), this.getPosition().getY()+1);
            case LEFT  -> newPosition =  new Coordinates(this.getPosition().getX()-1, this.getPosition().getY());
            case RIGHT -> newPosition =  new Coordinates(this.getPosition().getX()+1, this.getPosition().getY());
        }

        return newPosition.equals(player.getPosition());


    }


    public void octorockWalk(Zone zone){
        Coordinates newPosition = new Coordinates(0,0);

        switch (super.enemyDirection){
            case UP    -> newPosition =  new Coordinates(this.getPosition().getX(), this.getPosition().getY()-1);
            case DOWN  -> newPosition =  new Coordinates(this.getPosition().getX(), this.getPosition().getY()+1);
            case LEFT  -> newPosition =  new Coordinates(this.getPosition().getX()-1, this.getPosition().getY());
            case RIGHT -> newPosition =  new Coordinates(this.getPosition().getX()+1, this.getPosition().getY());
        }

        boolean isWalkable = false;

        try {
            isWalkable = zone.getTile(newPosition).isWalkable();
        } catch (IndexOutOfBoundsException e) {

            isWalkable = false;
        }


        if (isWalkable) {
            this.setPosition(newPosition);
            this.setSteps(this.getSteps() - 1);
        } else {
            this.chooseAndSetDirection();
            this.chooseandSetNuberOfSteps();
        }


    }



}
