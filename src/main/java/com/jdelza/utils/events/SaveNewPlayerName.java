package com.jdelza.utils.events;

/**
 * When user want to save typed name this class will be called
 */
public class SaveNewPlayerName {

    //player name
    private String playerName;
    private int selectedUser;

    public SaveNewPlayerName(String playerName, int selectedUser) {

        this.playerName = playerName;
        this.selectedUser = selectedUser;

    }

    //get player name
    public String getPlayerName() {
        return playerName;
    }

    public int getSelectedUser() {
        return selectedUser;
    }
}
