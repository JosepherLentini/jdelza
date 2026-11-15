package com.jdelza.utils.enums;

public enum PlayerID {
    PLAYER_1(1), PLAYER_2(2), PLAYER_3(3);

    int id;
    PlayerID(int id) {

        this.id = id;
    }
    public int getId() {
        return id;
    }


}
