package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.NPCType;
import com.jdelza.view.characters.NpcView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.items_objects.FIreView;
import com.jdelza.view.items_objects.ItemView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import static com.jdelza.utils.enums.Dimensions.TILE_HEIGT;

/**
 * This class represent the shop graphic
 */
public class ShopScreen extends Pane {
    private final double larghezzaMappa = Dimensions.MAP_WIDTH.get();
    private final double altezzaMappa = Dimensions.MAP_HEIGHT.get();

    private PlayerView playerView;



    public ShopScreen(PlayerView playerView, NPCType npcType, ItemType itemType) {

        this.playerView = playerView;

        ImageView iw = new ImageView(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/Shop.png"));

        iw.setFitHeight(altezzaMappa);
        iw.setFitWidth(larghezzaMappa);

        this.setPrefSize(larghezzaMappa, altezzaMappa);

        //If you set minimum and maximum dimensions, the component will not adapt to its parent
        this.setMinSize(this.larghezzaMappa, this.altezzaMappa);
        this.setMaxSize(this.larghezzaMappa, this.altezzaMappa);

        /*
        this.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "

        );

         */

        FIreView leftFire = new FIreView(4.5, 4.0);
        FIreView rightFire = new FIreView(10.5, 4.0);
        NpcView npc = new NpcView(npcType);



        this.getChildren().add(iw);
        this.getChildren().addAll(leftFire, rightFire, npc);
        this.getChildren().add(playerView);

        if(itemType != ItemType.SWORD){
            ItemView rupia = new ItemView(ItemType.RUPEE);
            rupia.setPosition(3, 6.5);
            this.getChildren().add(rupia);
        }

        ItemView item = new ItemView(itemType);
        item.setPosition(7.5, 5.5);
        this.getChildren().add(item);

    }







}
