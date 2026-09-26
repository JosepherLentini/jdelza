package com.jdelza.utils.events;

import com.jdelza.model.weapons.Weapon;

import java.util.List;

public class Attack {

    private final List<Weapon> weapons;

    public Attack(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    //Get methods
    public List<Weapon> getWeapons() {
        return weapons;
    }
}
