package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.NPCType;
import com.jdelza.view.characters.NpcView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.items_objects.FIreView;
import com.jdelza.view.items_objects.ItemView;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import static com.jdelza.utils.enums.Dimensions.TILE_HEIGT;

/**
 * This class represent the shop graphic
 */
public class ShopScreen extends Pane {

    //Dimensions
    private final double larghezzaMappa = Dimensions.MAP_WIDTH.get();
    private final double altezzaMappa = Dimensions.MAP_HEIGHT.get();

    //Item
    private ItemView shopItem;

    //Item cost
    private HBox costBox;


    public ShopScreen(NPCType npcType, ItemType itemType) {

        this.costBox = new HBox();
        costBox.setAlignment(Pos.CENTER);
        costBox.setPrefSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());
        costBox.setMinSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());
        costBox.setMaxSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());
        costBox.setLayoutX(Dimensions.TILE_WIDTH.get()*7.5);
        costBox.setLayoutY(Dimensions.TILE_HEIGT.get()*6.5);




        ImageView iw = new ImageView(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/Shop.png"));

        iw.setFitHeight(altezzaMappa);
        iw.setFitWidth(larghezzaMappa);

        this.setPrefSize(larghezzaMappa, altezzaMappa);

        //If you set minimum and maximum dimensions, the component will not adapt to its parent
        this.setMinSize(this.larghezzaMappa, this.altezzaMappa);
        this.setMaxSize(this.larghezzaMappa, this.altezzaMappa);

        /*
        costBox.setStyle(
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
        //this.getChildren().add(playerView);

        if(itemType != ItemType.SWORD && itemType != null){
            /*
            ItemView rupia = new ItemView(ItemType.RUPEE);
            rupia.setPosition(3, 6.5);
            this.getChildren().add(rupia);

             */

            HBox costLine = new HBox();
            costLine.setPrefSize(Dimensions.TILE_WIDTH.get()*2, TILE_HEIGT.get());
            costLine.setMinSize(Dimensions.TILE_WIDTH.get()*2, TILE_HEIGT.get());
            costLine.setMaxSize(Dimensions.TILE_WIDTH.get()*2, TILE_HEIGT.get());

            costLine.setAlignment(Pos.CENTER_LEFT);

            ImageView rupiaImage = new ImageView();
            rupiaImage.setImage(new Image("com/jdelza/view/assets/items_objects/rupie/RUPIA_YELLOW.png"));
            rupiaImage.setFitWidth(Dimensions.TILE_WIDTH.get());
            rupiaImage.setFitHeight(TILE_HEIGT.get());


            ImageView xImage = new ImageView();
            xImage.setImage(new Image("com/jdelza/view/assets/user_section/alphabet_font/CHAR_X.png"));
            xImage.setFitWidth(30);
            xImage.setFitHeight(30);


            costLine.getChildren().addAll(rupiaImage, xImage);

            costLine.setLayoutX(Dimensions.TILE_WIDTH.get()*3);
            costLine.setLayoutY(Dimensions.TILE_HEIGT.get()*6.5);

            this.getChildren().addAll(costLine, costBox);



        }

        if (itemType != null){
            shopItem = new ItemView(itemType);
            shopItem.setPosition(7.5, 5.5);
            this.getChildren().add(shopItem);

        }
    }


    public void removeShopItem(){
        this.getChildren().remove(this.shopItem);
    }

    public void drawCost(int cost){

            costBox.getChildren().clear();

            String[] costString = Integer.toString(cost).split("");

            for (String number: costString) {
                ImageView numberImage = new ImageView(
                        new Image("com/jdelza/view/assets/user_section/alphabet_font/CHAR_"+number.toUpperCase()+".png")
                );
                numberImage.setFitHeight(24);
                numberImage.setFitWidth(24);
                costBox.getChildren().add(numberImage);
                }

    }







}
