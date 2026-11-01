package com.jdelza.view.weapons;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.overworld.Overworld;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class SwordView extends WeaponView{

    private ImageView swordImageView;

    private boolean throwingFrame = true;
    private boolean throwing = false;

    //Images
    private Map<Directions, Image[]> throwingImages = new HashMap<>();

    public SwordView(String weaponID) {

        super(weaponID);

        this.swordImageView  = new ImageView();
        swordImageView.setImage(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/OKTOROCK_WEAPON.png"));

        for (Directions dir : Directions.values()) {

            String path = "file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/SWORD/SWORD_THROWING_";
            //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player/";
            Image[] images = new Image[2];

            for (int i = 0; i < 2; i++) {

                images[i] = new Image(path + dir.name()+ "_" + i + ".png");

            }

            throwingImages.put(dir, images);

        }

        swordImageView.setFitWidth(Dimensions.TILE_WIDTH.get());
        swordImageView.setFitHeight(Dimensions.TILE_HEIGT.get());
        swordImageView.setSmooth(false);

        this.getChildren().add(swordImageView);
    }


    //Get methods
    public ImageView getSwordImageView() {
        return swordImageView;
    }
    public boolean isThrowingFrame() {
        return throwingFrame;
    }

    //Set methods
    public void setSwordImageView(ImageView swordImageView) {
        this.swordImageView = swordImageView;
    }
    public void setThrowing(boolean throwing) {
        this.throwing = throwing;
    }
    public void setThrowingFrame(boolean throwingFrame) {
        this.throwingFrame = throwingFrame;
    }




    public boolean isThrowing() {
        return throwing;
    }

    public void changeSwordImage(Image image){swordImageView.setImage(image);}

    public void initializeThrowingImage(Directions direction){swordImageView.setImage(throwingImages.get(direction)[0]);}

    public void swordThrowingToggleImage(Directions direction){

        if (throwingFrame){
            swordImageView.setImage(
                    throwingImages.get(direction)[0]
            );
        }
        else{
            swordImageView.setImage(
                    throwingImages.get(direction)[1]
            );
        }

        this.throwingFrame = !throwingFrame;

    }



    public void swordCollision(Pane ow,/* WeaponView wv*/PlayerView player){
        String leftUpCollisionPath = "file:///C:/Users/Giuseppe Lentini/Downloads/SWORD_COLLISION_LEFT_UP_1.png";
        String leftDownCollisionPath = "file:///C:/Users/Giuseppe Lentini/Downloads/SWORD_COLLISION_LEFT_DOWN_1.png";
        String rightUpCollisionPath = "file:///C:/Users/Giuseppe Lentini/Downloads/SWORD_COLLISION_RIGHT_UP_1.png";
        String rightDownCollisionPath = "file:///C:/Users/Giuseppe Lentini/Downloads/SWORD_COLLISION_RIGHT_DOWN_1.png";

        StackPane lU = new StackPane();
        StackPane lD = new StackPane();
        StackPane rU = new StackPane();
        StackPane rD = new StackPane();



        ImageView leftUp = new ImageView(new Image(leftUpCollisionPath));
        ImageView leftDown = new ImageView(new Image(leftDownCollisionPath));
        ImageView rightUp = new ImageView(new Image(rightUpCollisionPath));
        ImageView rightDown = new ImageView(new Image(rightDownCollisionPath));



        lU.getChildren().add(leftUp);
        lD.getChildren().add(leftDown);
        rU.getChildren().add(rightUp);
        rD.getChildren().add(rightDown);


        this.getChildren().addAll(leftUp, leftDown, rightUp, rightDown);
        //this.getChildren().addAll(lU, lD, rU, rD);
        this.getChildren().remove(this.getSwordImageView());


        TranslateTransition leftUpTransition = new TranslateTransition(Duration.millis(200));
        TranslateTransition leftDownTransition = new TranslateTransition(Duration.millis(200));
        TranslateTransition rightUpTransition = new TranslateTransition(Duration.millis(200));
        TranslateTransition rightDownTransition = new TranslateTransition(Duration.millis(200));

        leftUpTransition.setNode(leftUp);
        leftDownTransition.setNode(leftDown);
        rightUpTransition.setNode(rightUp);
        rightDownTransition.setNode(rightDown);

        leftUpTransition.setByX(-50);
        leftUpTransition.setByY(-50);

        leftDownTransition.setByX(-50);
        leftDownTransition.setByY(50);

        rightUpTransition.setByX(50);
        rightUpTransition.setByY(-50);

        rightDownTransition.setToX(50);
        rightDownTransition.setToY(50);


        ParallelTransition pt = new ParallelTransition(leftUpTransition, leftDownTransition, rightUpTransition, rightDownTransition);
        pt.play();

        pt.setOnFinished((e)->{


            this.setLayoutY(0);
            this.setLayoutX(0);
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.getChildren().removeAll(leftUp, leftDown, rightUp, rightDown);
            this.getChildren().add(getSwordImageView());
            ow.getChildren().remove(this);
        });




    }



}
