package com.jdelza.utils.events;

import com.jdelza.model.weapons.Weapon;

import java.util.List;

public class RemoveWeapons {

    private List<Weapon> deleteWeapons;


    public RemoveWeapons(List<Weapon> deleteWeapons) {
        this.deleteWeapons = deleteWeapons;
    }

    public List<Weapon> getDeleteWeapons() {
        return deleteWeapons;
    }
}
