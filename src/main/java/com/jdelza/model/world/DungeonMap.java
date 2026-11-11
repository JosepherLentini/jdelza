package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.Level;
import com.jdelza.utils.interfaces.Environment;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DungeonMap extends GameMap implements Environment {

    //Dungeon position
    private Position dungeonOverworldPosition;

    /*
    //Dungeon exit & entry
    private Position entryExitPosition;

     */


    //Entry coordinates
    private Position entryPosition;

    //Exit coordinates
    private List<Position> exitPositions;

    //Dungeon level
    private Level dungeonLevel;


    public DungeonMap(Position dungeonOverworldPosition, Level dungeonLevel, Position entryPosition, List<Position> exitPositions) {
        this.dungeonOverworldPosition = dungeonOverworldPosition;
        this.dungeonLevel = dungeonLevel;
        //this.entryExitPosition = entryExitPosition;
        this.entryPosition = entryPosition;
        this.exitPositions = exitPositions;

        super.map = new Zone[3][3];

        for (int i = 0; i< super.map.length; i++){
            for (int j=0; j<super.map[0].length; j++){
                Coordinates zoneCoordinates = new Coordinates(j,i);
                super.map[i][j] = new Zone(zoneCoordinates);
            }
        }

        /*
        super.getMap()[entryExitPosition.getZoneCoordinates().getY()][entryExitPosition.getZoneCoordinates().getX()]
                .getTile(
                        new Coordinates(
                                entryExitPosition.getCellCoordinates().getX(),
                                entryExitPosition.getCellCoordinates().getY()
                        )
                ).setEnvironment(overworld);

        super.getMap()[entryExitPosition.getZoneCoordinates().getY()][entryExitPosition.getZoneCoordinates().getX()]
                .getTile(
                        new Coordinates(
                                entryExitPosition.getCellCoordinates().getX()+1,
                                entryExitPosition.getCellCoordinates().getY()
                        )
                ).setEnvironment(overworld);

         */





        setLogicMap();

    }


    public Level getDungeonLevel() {
        return dungeonLevel;
    }

    @Override
    public Position getEntryPosition() {
        return this.entryPosition;
    }

    @Override
    public List<Position> getExitPosition() {
        return this.exitPositions;
    }

    @Override
    public Position getEnvironmentPosition() {
        return this.dungeonOverworldPosition;
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return EnvironmentType.DUNGEON;
    }

    @Override
    public Directions getExitDirection() {
        return Directions.DOWN;
    }

    @Override
    public void setLogicMap() {

        for (int y = 0; y < this.getMap().length; y++){
            for (int x = 0; x < this.getMap()[0].length; x++){

                String path = "C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/dungeons/FIRST_LEVEL/FIRST_LEVEL_LOGIC_MAP_"+y+x+".txt";

                try(BufferedReader br = Files.newBufferedReader(Paths.get(path))){

                    String currentLine = br.readLine();
                    int row = 0;

                    while (currentLine != null){

                        String[] line = currentLine.split("");

                        //System.out.println(Arrays.toString(line));
                        //System.out.println(row);
                        //logicMap[row]  = currentLine.split("");

                        for (int column = 0; column < line.length; column++){
                            //System.out.println(this.getMap()[y][x] + " | " + "(" + column + "," + row + ")");
                            //this.getMap()[y][x].getTile(new Coordinates(column, row));
                            this.getMap()[y][x].getTile(new Coordinates(column, row)).setWalkable(Objects.equals(line[column], "0"));

                        }

                        currentLine = br.readLine();
                        row++;

                    }





                } catch (Exception e) {
                    throw new RuntimeException(e);
                }



            }
        }


        //System.out.println(Arrays.deepToString(logicMap));





    }
}
