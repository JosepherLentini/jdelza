package com.jdelza.view.characters;

import com.jdelza.model.entities.Position;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.view.items_objects.ItemView;
import com.jdelza.view.weapons.SwordView;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;


public class PlayerView extends CharacterView {


    private ImageView tileImageView;             //Player imageView
    boolean frame = false;                      //This is used to switch player movement image
    boolean colorFrame = false;

    //Sword
    SwordView playerSword = new SwordView("PLAYER_WEAPON");
    SwordView throwingWeapon =  new SwordView("PLAYER_THROW_WEAPON");

    //Images
    private Map<Directions, Image[]> walkingImage = new HashMap<>();    //Key: direction, Value: Image of direction
    private Map<Directions, Image[][]> injureImages = new HashMap<>();
    private Map<Directions, Image[]> playerAttackImages = new HashMap<>();
    private Map<Directions, Image[]> swordAttackImages = new HashMap<>();

    //events
    private boolean injuring = false;
    private boolean attacking = false;


    //Direction
    private Directions playerDirection;


    public PlayerView() {

        for (Directions dir : Directions.values()) {

            String path = "com/jdelza/view/assets/player/player_walk/";
                        //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player/";
            Image[] images = new Image[2];

            for (int i = 1; i < 3; i++) {

                images[i - 1] = new Image(path + dir.name()+ "_" + i + ".png");

            }

            walkingImage.put(dir, images);

        }

        for (Directions dir : Directions.values()) {

            String path = "com/jdelza/view/assets/player/PLAYER_INJURE/";
            Image[][] images = new Image[2][2];

            //com/jdelza/view/assets/player/PLAYER_INJURE/DOWN_1_1.png

            for (int i = 1;  i<3; i++){
                for (int j = 1; j<3; j++){
                    images[i-1][j-1] = new Image(path + dir.name()+"_"+i+"_"+j+".png");
                }
            }

            injureImages.put(dir, images);

        }

        addAttackImages();

        playerDirection = Directions.DOWN;

        tileImageView = new ImageView();
        tileImageView.setImage(walkingImage.get(playerDirection)[0]);

        tileImageView.setFitWidth(super.getTileWidth());
        tileImageView.setFitHeight(super.getTileHeight());
        tileImageView.setSmooth(false);
        tileImageView.toFront();

        this.getChildren().add(tileImageView);

        this.toFront();


    }


    //Get methods
    public Map<Directions, Image[]> getWalkingImage() {return walkingImage;}
    public boolean isInjuring() {return injuring;}
    public boolean isColorFrame() {return colorFrame;}
    public Directions getPlayerDirection() {return playerDirection;}
    public boolean isAttacking() {return attacking;}
    public SwordView getPlayerSword() {
        return playerSword;
    }
    public SwordView getThrowingWeapon() {
        return throwingWeapon;
    }
    public ImageView getTileImageView() {return tileImageView;}
    public Map<Directions, Image[]> getPlayerAttackImages() {return playerAttackImages;}
    public Map<Directions, Image[]> getSwordAttackImages() {return swordAttackImages;}

    //Set methods
    public void setInjuring(boolean injuring) {this.injuring = injuring;}
    public void setTileImageView(Image image){tileImageView.setImage(image);}
    public void setColorFrame(boolean colorFrame) {this.colorFrame = colorFrame;}
    public void setPlayerDirection(Directions playerDirection) {this.playerDirection = playerDirection;}
    public void setAttacking(boolean attacking) {this.attacking = attacking;}


    /**
     * This method occurs when player hit with weapons or enemies
     */
    public void playerInjured(Directions direction){

        //int color = colorFrame? 0 : 1;
        int frame = this.frame ? 0 : 1;

        tileImageView.setImage(injureImages.get(direction)[colorFrame ? 0 : 1][frame]);

        //this.colorFrame = !colorFrame;
        this.frame = !this.frame;

    }

    public void changeInjureColor(){
        tileImageView.setImage(injureImages.get(playerDirection)[colorFrame? 0 : 1][this.frame ? 0 : 1]);
        this.colorFrame = !this.colorFrame;
    }

    /**
     * This method occur when player is moving. It is used to change image for animate the player.
     * @param direction
     */
    public void changeSprite(Directions direction){
        setPlayerDirection(direction);
        if(injuring && attacking){
            tileImageView.setImage(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/OKTOROCK_WEAPON.png"));

        } else if (injuring){
            playerInjured(direction);
        }
        else{
            if(frame)
                tileImageView.setImage(walkingImage.get(direction)[0]);
            else
                tileImageView.setImage(walkingImage.get(direction)[1]);

            frame = !frame;

        }


    }

    private void addAttackImages(){


        for (Directions dir : Directions.values()){

            String swordPath = "com/jdelza/view/assets/items_objects/attack_sword/SWORD_";
            String playerPath = "com/jdelza/view/assets/player/player_attack/ATTACK_";

            Image[] si = new Image[3];
            Image[] pi = new Image[3];

            for (int i = 0; i < 3; i++){
                int imgIndex = i+1;
                si[i] = new Image(swordPath+dir.name()+"_"+imgIndex+".png");
                pi[i] = new Image(playerPath+dir.name()+"_"+imgIndex+".png");
            }
            this.swordAttackImages.put(dir, si);
            this.playerAttackImages.put(dir, pi);

        }
    }

    public void attack(Directions direction){

        if (!isAttacking()){
            showSword(direction);
            setAttacking(true);

        }

    }

    public void addPlayerWeaponToTheScreen(Pane overworld, Weapon weapon){

        getThrowingWeapon().setLayoutY(0);
        getThrowingWeapon().setLayoutX(0);

        int x = weapon.getWeaponMapPosition().getX()*Dimensions.MAP_COLUMNS.get()/*+weapon.getPosition().getX()*Dimensions.TILE_WIDTH.get()*/;
        int y = weapon.getWeaponMapPosition().getY()*Dimensions.MAP_ROWS.get()/*+weapon.getPosition().getY()*Dimensions.TILE_HEIGT.get()*/;

        System.out.println(weapon.getPosition().getX() + " " + weapon.getPosition().getY());

        getThrowingWeapon().setLayoutY(weapon.getPosition().getY()*Dimensions.TILE_HEIGT.get());
        getThrowingWeapon().setLayoutX(weapon.getPosition().getX()*Dimensions.TILE_WIDTH.get());

        overworld.getChildren().add(getThrowingWeapon());
    }

    public void moveThrownWeaponView(Directions direction){

        getThrowingWeapon().swordThrowingToggleImage(direction);

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(getThrowingWeapon());

        //Setting transition duration
        transition.setDuration(Duration.millis(50));


        switch (direction){
            //Move the player in the indicated direction; translate the player by a certain distance
            case UP:    transition.setByY(-Dimensions.TILE_HEIGT.get());break;
            case DOWN:  transition.setByY(+Dimensions.TILE_HEIGT.get());break;
            case LEFT:  transition.setByX(-Dimensions.TILE_WIDTH.get());break;
            case RIGHT: transition.setByX(+Dimensions.TILE_WIDTH.get());break;
        }

        //Start transition
        transition.play();

        //When transition ends
        transition.setOnFinished(e->{});



    }

    public void endAttack(){
        setAttacking(false);
        this.getChildren().remove(this.playerSword);
        this.playerSword.setLayoutY(0);
        this.playerSword.setLayoutX(0);
        this.tileImageView.setImage(walkingImage.get(getPlayerDirection())[0]);
    }

    public void showSword(Directions direction){

        switch (direction){
            case UP -> this.playerSword.setLayoutY(-super.getTileHeight());
            case DOWN -> this.playerSword.setLayoutY(super.getTileHeight());
            case LEFT -> this.playerSword.setLayoutX(-super.getTileWidth());
            case RIGHT -> this.playerSword.setLayoutX(super.getTileWidth());
        }


        this.getChildren().add(this.playerSword);
    }

    public void playerChangePosition(Position position){
        int x = position.getCellCoordinates().getX();
        int y = position.getCellCoordinates().getY();

        this.setTranslateX(x*Dimensions.TILE_WIDTH.get());
        this.setTranslateY(y*Dimensions.TILE_HEIGT.get());


    }


    public void takeItem(ItemView item){
       getChildren().add(item);
       setTileImageView(new Image("com/jdelza/view/assets/player/PLAYER_TAKE_ITEM.png"));

    }







}
