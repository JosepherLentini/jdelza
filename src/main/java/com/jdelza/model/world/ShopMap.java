package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.NPCType;
import com.jdelza.utils.interfaces.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represent the game shop.
 * The structure of the shop is similar to a GameMap class.
 */
public class ShopMap extends GameMap implements Environment {

    //shopMap position
    private Position shopMapPosition;

    //shopMap exit
    private Position entryExitPosition;

    //NPC
    private NPCType npcType;

    //Item
    private ItemType itemType;

    //Exit direction
    private final Directions exitDirection = Directions.DOWN;


    public ShopMap(Position shopMapPosition, NPCType npc, ItemType item, WorldMap overworld) {

        this.shopMapPosition = shopMapPosition;
        this.npcType = npc;
        this.itemType = item;

        //exit coordinates
        this.entryExitPosition = new Position(new Coordinates(0,0), new Coordinates(7,10));


        super.map = new Zone[1][1];
        Coordinates zoneCoordinates = new Coordinates(0,0);

        super.map[0][0] = new Zone(zoneCoordinates);

        //!!!BISOGNA SETTARE ANCHE ENVIRONMENT DI RITORNO QUANDO SI ESCE DA UNO SHOP O DA UN DUNGEON !!!
        getMap()[0][0].getTile(new Coordinates(7,10)).setEnvironment(overworld);
        getMap()[0][0].getTile(new Coordinates(8,10)).setEnvironment(overworld);

        //Place the item into a cell
        getMap()[0][0].getTile(new Coordinates(7,6)).setItemType(itemType);
        getMap()[0][0].getTile(new Coordinates(8,6)).setItemType(itemType);

        setLogicMap();
    }

    //Get methods
    public Directions getExitDirection() {return exitDirection;}


    @Override
    public void setLogicMap() {

        String logicMapPath = "src/main/java/com/jdelza/model/model_assets/Shop_walkable.txt";
        List<List<Integer>> logicMap = new ArrayList<>();
        String[][] loma = new String[11][16];

        File myObj = new File(logicMapPath);

        try (Scanner myReader = new Scanner(myObj)) {
            int y = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                loma[y] = data.split("");
                y++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (int y = 0; y<11; y++){
            for (int x = 0; x<16; x++){
                super.getMap()[0][0].getZone()[y][x].setWalkable(loma[y][x].equals("0"));
            }
        }

    }

    //Get methods
    public Position getEntryExitPortal() {return entryExitPosition;}
    public NPCType getNpcType() {return npcType;}
    public ItemType getItemType() {return itemType;}

    //Set methods
    public void setEntryExitPosition(Position entryExitPosition) {this.entryExitPosition = entryExitPosition;}
    public void setNpcType(NPCType npcType) {this.npcType = npcType;}
    public void setItemType(ItemType itemType) {this.itemType = itemType;}

    @Override
    public Position getEntryPosition() {
        return this.entryExitPosition;
    }

    @Override
    public Position getExitPosition() {
        return this.entryExitPosition;
    }

    @Override
    public Position getEnvironmentPosition() {
        return this.shopMapPosition;
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return EnvironmentType.SHOP;
    }
}
