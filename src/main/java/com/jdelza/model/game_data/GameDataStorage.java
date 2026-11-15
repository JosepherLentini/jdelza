package com.jdelza.model.game_data;

import com.jdelza.utils.enums.PlayerID;

import java.io.IOException;

public class GameDataStorage {

    private PlayerID selectedPlayer = PlayerID.PLAYER_1;
    private int playerSelectedIndex = 1;

    private PlayerInitializationData PLAYER_1;
    private PlayerInitializationData PLAYER_2;
    private PlayerInitializationData PLAYER_3;





    public GameDataStorage() throws IOException {

        PLAYER_1 = new PlayerInitializationData();
        PLAYER_2 = new PlayerInitializationData();
        PLAYER_3 = new PlayerInitializationData();

    }


    public PlayerInitializationData getPlayerData(int playerIndex){
        return switch (playerIndex) {
            case 1 -> PLAYER_1;
            case 2 -> PLAYER_2;
            case 3 -> PLAYER_3;
            default -> null;
        };

    }

    public PlayerInitializationData getPlayerData(PlayerID id){
        return switch (id) {
            case PLAYER_1 -> this.PLAYER_1;
            case PLAYER_2 -> this.PLAYER_2;
            case PLAYER_3 -> this.PLAYER_3;

        };

    }


    public PlayerInitializationData getSelectedPlayerData(){
        return getPlayerData(selectedPlayer);
    }


    public void setSelectedPlayer(PlayerID selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }


    public void setSelectedPlayer(int id) {
        switch (id) {
            case 1 -> this.selectedPlayer = PlayerID.PLAYER_1;
            case 2 -> this.selectedPlayer = PlayerID.PLAYER_2;
            case 3 -> this.selectedPlayer = PlayerID.PLAYER_3;

        };

        this.playerSelectedIndex = id;
    }

}
