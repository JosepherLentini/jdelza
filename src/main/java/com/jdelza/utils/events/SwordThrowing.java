package com.jdelza.utils.events;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;

public class SwordThrowing {

    private Directions direction;
    private StartEndStatus status;

    //Coordinates
    private Coordinates swordZoneCoordinates;
    private Coordinates swordMapCoordinates;

    //Weapon
    private Weapon playerWeapon;

    public SwordThrowing(/*Weapon playerWeapon*/ Directions direction, StartEndStatus status) {

        this.direction = direction;
        this.status = status;

        //Coordinates
        //this.swordZoneCoordinates  = playerWeapon.getPosition();
        //this.swordMapCoordinates = playerWeapon.getWeaponMapPosition();

        //Weapon
        //this.playerWeapon = playerWeapon;

    }

    public Directions getDirection() {
        return direction;
    }
    public StartEndStatus getStatus() {return status;}
    //public Coordinates getSwordZoneCoordinates() {return swordZoneCoordinates;}
    //public Coordinates getSwordMapCoordinates() {return swordMapCoordinates;}
    //public Weapon getPlayerWeapon() {return playerWeapon;}

    //public SwordThrowing setDirection(Directions direction) {this.direction = direction;return this;}
}
