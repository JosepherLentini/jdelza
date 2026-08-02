package com.jdelza.model.world;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represent the current logic view of the game
 */
public class WorldMap {
    public Zone[][] map;
    public Player player;
    /**
     * Contructor
     */
    public WorldMap(Player player) {

        int mapHeigt = Dimensions.MAP_ROWS.get();
        int mapWidth = Dimensions.MAP_COLUMNS.get();

        this.map = new Zone[mapHeigt][mapWidth ];
        this.player = player;

        for (int i = 0; i< mapHeigt; i++){
            for (int j=0; j<mapWidth; j++){
                Coordinates zoneCoordinates = new Coordinates(j,i);
                map[i][j] = new Zone(player.getPlayerMapPosition().equals(zoneCoordinates) ? player : null, zoneCoordinates);
            }
        }
        System.out.println("crea mappa");

        setLogicMap();
    }

    //Get methods
    public Zone[][] getMap() {
        return map;
    }
    public Zone getZone(Coordinates coordinates){return map[coordinates.getY()][coordinates.getX()];}

    //Set methods
    public void setMap(Zone[][] map) {
        this.map = map;
    }

    /**
     * Add zone in to the map by coordinates expressed by an object of type "Coordinates"
     * @param zone
     * @param coordinates
     */
    public void addZone(Zone zone, Coordinates coordinates){
        int y = coordinates.getY();
        int x = coordinates.getX();
        map[y][x] = zone;
    }

    /**
     * Add zone in to the map by coordinates int y and int x
     * @param zone      a zone type object to be inserted
     * @param y         height position
     * @param x         width position
     */
    public void addZone(Zone zone, int y, int x){
        map[y][x] = zone;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int h = 0; h < map.length; h++){

            for (int w = 0; w < map[0].length; w++){
                if (player.getPlayerMapPosition().equals(new Coordinates(w,h))){
                    sb.append(" # ");
                }
                else{
                    sb.append(" + ");
                }

            }
            sb.append("\n");

        }

        return sb.toString();

    }


    public void setLogicMap(){
        System.out.println("crea logic");
        String percorso = "C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/logic-world/Overworld_collisioni.csv";
        List<List<Integer>> logicMap = new ArrayList<>();

        try {
            // Legge tutte le righe del file e le elabora una alla volta
            Files.lines(Paths.get(percorso)).forEach(riga -> {
                String[] valori = riga.split(","); // Separa per VIRGOLA

                List<Integer> rig = Arrays.stream(valori).map(n -> Integer.parseInt(n.equals("0") ? "0" : "1")).collect(Collectors.toList());

                logicMap.add(rig);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int y = 0; y<8; y++) {
            for (int x = 0; x < 16; x++) {

                for (int i = y * 11; i < (y * 11) + 11; i++) {
                    for (int j = x * 16; j < (x * 16) + 16; j++) {

                       //System.out.print(logicMap.get(i).get(j) == 0);

                        //this.getZone(new Coordinates(x,y)).getZone()[i/11][j/16].setWalkable(logicMap.get(i).get(j) == 0); //logicMap.get(i).get(j) == 0
                        int localY = i % 11;
                        int localX = j % 16;
                        this.getMap()[y][x].getZone()[localY][localX].setWalkable(logicMap.get(i).get(j) == 0);


                    }

                    System.out.println();

                }
                System.out.print(x + " ");
                System.out.print(y);
                System.out.println();

            }



        }
    }



}
