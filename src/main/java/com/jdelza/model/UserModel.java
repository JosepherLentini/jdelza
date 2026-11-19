package com.jdelza.model;

import com.jdelza.model.game_data.GameDataStorage;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.GameKey;
import com.jdelza.utils.events.SaveNewPlayerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class UserModel extends Observable {

    private int selectedUser = 1;
    private GameDataStorage gameDataStaorage;

    private int numberOfTypedKeys = 0;
    private List<String> currentName;

    public UserModel(GameDataStorage gameDataStorage) {

        this.gameDataStaorage = gameDataStorage;
        this.currentName = new ArrayList<>();


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


    public void nameRegistration(){
        //set registered
        gameDataStaorage.getPlayerData(selectedUser).setPlayerRegistered(true);

        String name = String.join("", currentName);

        //set player name
        gameDataStaorage.getPlayerData(selectedUser).setPlayerName(name);

        //reset typed name
        this.currentName.removeAll(this.currentName);

        //reset number of typed keys
        this.numberOfTypedKeys = 0;

        System.out.println(name);
        setChanged();
        notifyObservers(new SaveNewPlayerName(name, selectedUser));


    }

    /**
     * This function is called when the user type his name
     * @param currentKey key typed
     */
    public void writeName(GameKey currentKey){
      if (numberOfTypedKeys <= 20){
            setChanged();
            notifyObservers(currentKey);
            numberOfTypedKeys++;
            currentName.add(currentKey.getValue());
        }

      System.out.println(this.currentName.toString() + " " + numberOfTypedKeys);

    }

    /**
     * When user types backspace key,last name char will be deleted
     */
    public void cancelKeys(){
        if(numberOfTypedKeys > 0){
            setChanged();
            notifyObservers("CANCEL");
            numberOfTypedKeys--;
            currentName.removeLast();

        }

        System.out.println(this.currentName.toString() + " " + numberOfTypedKeys);
    }



}
