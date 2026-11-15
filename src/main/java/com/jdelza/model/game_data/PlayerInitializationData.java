package com.jdelza.model.game_data;

import com.jdelza.model.enemies.Octorock;
import com.jdelza.model.enemies.Tektite;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.model.entities.Position;
import com.jdelza.model.world.WorldMap;
import com.jdelza.utils.enums.EnemyType;
import com.jdelza.utils.enums.GameColor;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class PlayerInitializationData {

    private WorldMap worldMap;
    private List<Coordinates> excludedZones;
    private String playerName;

    private int playerPoints;
    private Position playerStartPosition;
    private boolean playerRegistered = false;


    public PlayerInitializationData() throws IOException {

        this.worldMap = new WorldMap();
        this.excludedZones = generateExcludedZone();

        generate(EnemyType.OCTOROK,GameColor.BLUE, 3, 4, 4, 7, 14);
        generate(EnemyType.TEKTITE,GameColor.BLUE, 3, 0, 2, 3, 9);

    }

    /**
     * Player name
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    };
    public boolean isPlayerRegistered() {return playerRegistered;}

    /**
     * Player name
     * @param playerName name of the player
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setPlayerRegistered(boolean registered) {this.playerRegistered = registered;}

    /**
     * This function return a list of zone which enemy can't appear in the gamemap
     * @return
     */
    private List<Coordinates> generateExcludedZone(){
        return this.excludedZones = List.of(
                new Coordinates(0,0),
                new Coordinates(14,0),
                new Coordinates(15,0),
                new Coordinates(7,1),
                new Coordinates(8,1),
                new Coordinates(9,1),
                new Coordinates(13,1),
                new Coordinates(15,1),
                new Coordinates(0,2),
                new Coordinates(1,2),
                new Coordinates(2,2),
                new Coordinates(15,2),
                new Coordinates(0,3),
                new Coordinates(1,3),
                new Coordinates(7,3),
                new Coordinates(12,3),
                new Coordinates(0,4),
                new Coordinates(1,4),
                new Coordinates(5,4),
                new Coordinates(2,7),
                new Coordinates(4,7),
                new Coordinates(7,7)
        );
    }



    public void generate(EnemyType enemyType, GameColor color, int quantity,  int startRow, int startColumn, int endRow, int endColumn){


        for (int y = startRow; y < endRow; y++){
            for (int x = startColumn; x < endColumn; x++){

                Coordinates currentCoordinates = new Coordinates(x, y);

                if(excludedZones.stream().noneMatch(ex -> ex.equals(currentCoordinates))) {

                    //worldMap.getZone(currentCoordinates).addEnemy();

                    for (int k = 0; k < quantity; k++){
                        Random random = new Random();

                        int row = random.nextInt(9)+1;
                        int column = random.nextInt(13)+1;

                        Coordinates cellCoordinates = new Coordinates(column, row);


                        if (enemyType != EnemyType.TEKTITE){

                            boolean isCollocable = worldMap.getZone(currentCoordinates).getTile(cellCoordinates).isWalkable();
                            Coordinates finalCellCoordinates = cellCoordinates;
                            boolean enemyInSamePosition = worldMap.getZone(currentCoordinates).getEnemies()
                                    .stream()
                                    .map(Entity::getPosition)
                                    .anyMatch(p-> p.equals(finalCellCoordinates));

                            while (!isCollocable && !enemyInSamePosition){
                                row = random.nextInt(9)+1;
                                column = random.nextInt(13)+1;
                                cellCoordinates = new Coordinates(column, row);
                                isCollocable = worldMap.getZone(currentCoordinates).getTile(cellCoordinates).isWalkable();

                            }

                        }



                        switch (enemyType){
                            case OCTOROK ->  worldMap.getZone(currentCoordinates)
                                    .addEnemy(
                                            new Octorock(cellCoordinates, color, enemyType)
                                                    .setEnemyMapCoordinates(currentCoordinates)
                                                    .setEnemyID()
                                    );
                            case TEKTITE ->  worldMap.getZone(currentCoordinates)
                                    .addEnemy(
                                            new Tektite(cellCoordinates, color, enemyType)
                                                    .setEnemyMapCoordinates(currentCoordinates)
                                                    .setEnemyID()
                                    );


                        }
                    }


                }


            }
        }



    }


    public WorldMap getWorldMap() {
        return worldMap;
    }
}
