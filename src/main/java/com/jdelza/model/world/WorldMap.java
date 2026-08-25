package com.jdelza.model.world;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.EnemyType;
import com.jdelza.utils.enums.GameColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
    public WorldMap(Player player) throws IOException {

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

        /*
        this.getZone(new Coordinates(6, 6)).addEnemy(
                new Enemy(new Coordinates(6, 6), GameColor.BLUE, EnemyType.OCTOROK)
                        .setEnemyMapCoordinates(new Coordinates(6, 6)).setEnemyID(0)
        );

         */
        setLogicMap();

        createEnemiesFromFile().stream().forEach(e-> this.getZone(e.getEnemyMapCoordinates()).addEnemy(e));




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

    public List<Enemy> createEnemiesFromFile() throws IOException {
        final String percorso =  "C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/Nuovo Documento di testo.txt";
        ArrayList<List<Enemy>> enemies = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(Paths.get(percorso))) {

            String line = br.readLine();

            while (line != null) {

                enemies.add(createEnemyListFromTxtFile(line.split("_")));

                line = br.readLine();

            }
        }

        return enemies.stream().flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
    }

    public Coordinates randomEnemyZonePosition(Coordinates mapCoordinates){
        int x,y;
        Random random = new Random();

        x = random.nextInt(14);
        y = random.nextInt(9);

        if (!this.getZone(mapCoordinates).getZone()[y][x].isWalkable()){System.out.println("walkable"+"("+x+","+y+")");}

        while (!this.getZone(mapCoordinates).getZone()[y][x].isWalkable()){
            x = random.nextInt(14)+1;
            y = random.nextInt(9)+1;
        }



        return new Coordinates(x,y);

    }

    public List<Enemy> createEnemyListFromTxtFile(String[] enemyTxTData){
        List<Enemy> zoneEnemies = new ArrayList<>();

        int numberOfEnemies = Integer.parseInt(enemyTxTData[1]);
        String[] coordFromData = enemyTxTData[0].split(",");
        Coordinates mapCoordinates = new Coordinates(Integer.parseInt(coordFromData[0]),Integer.parseInt(coordFromData[1]));
        EnemyType enemyType = Arrays.stream(EnemyType.values()).filter(v->v.getName().equals(enemyTxTData[2])).findFirst().get();

        System.out.println(Arrays.toString(enemyTxTData));
        System.out.println(Arrays.toString(coordFromData));
        System.out.println(mapCoordinates);
        System.out.println(randomEnemyZonePosition(mapCoordinates));

        for (int i = 0; i< numberOfEnemies; i++){
            Coordinates newZoneEnemyCoordinates = randomEnemyZonePosition(mapCoordinates);
            GameColor enemyColor = enemyTxTData.equals("BLUE") ? GameColor.BLUE : GameColor.RED;

            /*
            Coordinates finalNewZoneEnemyCoordinates = newZoneEnemyCoordinates;

            boolean enemySamePosition = zoneEnemies.stream().anyMatch(enemy -> enemy.getPosition().equals(finalNewZoneEnemyCoordinates));

            while (!enemySamePosition){
                newZoneEnemyCoordinates = randomEnemyZonePosition(mapCoordinates);

            }

             */



            zoneEnemies.add(new Enemy(newZoneEnemyCoordinates,enemyColor,enemyType).setEnemyMapCoordinates(mapCoordinates));

        }




        return zoneEnemies;

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
        String collisionPath = "C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/logic-world/Overworld_collisioni.csv";
        List<List<Integer>> logicMap = new ArrayList<>();

        try {
            // Legge tutte le righe del file e le elabora una alla volta
            Files.lines(Paths.get(collisionPath)).forEach(riga -> {
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

                    //System.out.println();

                }
                //System.out.print(x + " ");
                //System.out.print(y);
                //System.out.println();

            }



        }
    }



}
