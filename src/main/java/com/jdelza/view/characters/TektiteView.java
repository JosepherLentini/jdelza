package com.jdelza.view.characters;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Directions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TektiteView extends EnemyView{

    private final Image[] walkingImage;
    private boolean frame = false;

    private boolean isJumping;

    public TektiteView(Coordinates enemyViewMapPosition, Coordinates enemyViewZonePosition) {
        super(enemyViewMapPosition, enemyViewZonePosition);
        String path = "file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/tektite/tektite_0.png";
        //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/tektite";

        this.isJumping = false;

        this.walkingImage = new Image[2];
        for (int i = 0; i <2; i++) {
            Image tek = new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/tektite/tektite_" + i + ".png");

            walkingImage[i] = tek;
        }

        ;


        tileImageView = new ImageView();
        tileImageView.setImage(new Image(path));

        tileImageView.setFitWidth(super.getTileWidth());
        tileImageView.setFitHeight(super.getTileHeight());
        tileImageView.setSmooth(false);

        this.getChildren().add(tileImageView);
    }

    public boolean isFrame() {
        return frame;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public void setFrame(boolean frame) {
        this.frame = frame;
    }

    @Override
    public void changeSprite(Directions direction){


            if(frame){
                tileImageView.setImage(walkingImage[0]);
            } else{
                tileImageView.setImage(walkingImage[1]);
            }
            frame = !frame;



    }


}
