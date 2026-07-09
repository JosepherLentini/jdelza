package com.jdelza;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.enums.Directions;
import com.jdelza.model.world.Tile;
import com.jdelza.model.world.Zone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        List<String> righe = new ArrayList<>();
        Player p = Player.getPlayerInstance();


        try {
            Path path = Paths.get("src/test/zone_test");
            righe = Files.readAllLines(path);
            System.out.println();



        } catch (IOException e) {
            e.printStackTrace();
        }

        Zone z = new Zone(righe.size(),righe.get(0).length(), p);

        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 6; x++){

            }
        }

        for (int y = 0; y < righe.size(); y++) {
            for (int x = 0; x < righe.get(y).length(); x++) {
                z.addTile(new Tile(new Coordinates(x,y), ""), y, x);
                if (righe.get(y).split("")[x].equals("1") ) {z.getZone()[y][x].setWalkable(false);}
            }
        }



        for (Directions d : Directions.values()){
            //System.out.println(d);
        }

        Integer[] x = new Integer[3];

        System.out.println(x[2]);



    }
}