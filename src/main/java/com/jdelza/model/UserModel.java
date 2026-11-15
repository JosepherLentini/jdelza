package com.jdelza.model;

import com.jdelza.model.game_data.GameDataStorage;
import com.jdelza.utils.enums.Directions;

import java.util.Observable;

public class UserModel extends Observable {

    private int selectedUser = 1;
    private GameDataStorage gameDataStaorage;


    public UserModel(GameDataStorage gameDataStorage) {
        this.gameDataStaorage = gameDataStorage;
    }


    public void selectUser(Directions direction){

        if (direction == Directions.UP && this.selectedUser > 1 ){
            this.selectedUser--;

        }
        else if (direction == Directions.DOWN && this.selectedUser < 3){
            this.selectedUser++;
        };

        //CHANGE STORAGE SELECTED USER
        gameDataStaorage.setSelectedPlayer(selectedUser);

        setChanged();
        notifyObservers(selectedUser);


    }


    public void goToGame(){
        setChanged();
        notifyObservers("START_GAME");
    }



    public void registerOrPlay(){
        if (gameDataStaorage.getPlayerData(selectedUser).isPlayerRegistered()){

            setChanged();
            notifyObservers("START_GAME");

        }else{
            //Register selected player
            setChanged();
            notifyObservers("REGISTER");
        }
    }

}
