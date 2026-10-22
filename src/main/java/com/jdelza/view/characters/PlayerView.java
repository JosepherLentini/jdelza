package com.jdelza.view.characters;

import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.view.weapons.RockView;
import com.jdelza.view.weapons.SwordView;
import com.jdelza.view.weapons.WeaponView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class PlayerView extends CharacterView {


    private ImageView tileImageView;             //Player imageView
    boolean frame = false;                      //This is used to switch player movement image
    boolean colorFrame = false;

    //Sword
    SwordView playerSword = new SwordView("PLAYER_WEAPON");
    SwordView throwingWeapon= new SwordView("PLAYER_THROW_WEAPON");

    //Images
    private Map<Directions, Image[]> walkingImage = new HashMap<>();    //Key: direction, Value: Image of direction
    private Map<Directions, Image[][]> injureImages = new HashMap<>();
    private Map<Directions, Image[]> attackImages = new HashMap<>();

    //events
    private boolean injuring = false;
    private boolean attacking = false;
    private boolean isThrowingWeapon = false;
    private int attackImageIndex = 0;

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
    public int getAttackImageIndex() {
        return attackImageIndex;
    }
    public boolean isThrowingWeapon() {return isThrowingWeapon;}

    public SwordView getThrowingWeapon() {
        return throwingWeapon;
    }

    //Set methods
    public void setInjuring(boolean injuring) {this.injuring = injuring;}
    public void setTileImageView(Image image){tileImageView.setImage(image);}
    public void setColorFrame(boolean colorFrame) {this.colorFrame = colorFrame;}
    public void setPlayerDirection(Directions playerDirection) {this.playerDirection = playerDirection;}
    public void setAttacking(boolean attacking) {this.attacking = attacking;}
    public void setAttackImageIndex(int attackImageIndex) {
        this.attackImageIndex = attackImageIndex;
    }
    public void setThrowingWeapon(boolean throwingWeapon) {this.isThrowingWeapon = isThrowingWeapon;}

    public void setThrowingWeapon(SwordView throwingWeapon) {
        this.throwingWeapon = throwingWeapon;
    }

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
            System.out.println("attack&injure");
        } else if (injuring){
            playerInjured(direction);
            System.out.println(attacking);
        }
        else{
            if(frame)
                tileImageView.setImage(walkingImage.get(direction)[0]);
            else
                tileImageView.setImage(walkingImage.get(direction)[1]);

            frame = !frame;

        }


    }

    public void attack(Directions direction){
        //com/jdelza/view/assets/player/PLAYER_INJURE/DOWN_1_2.png

        if (!isAttacking()){
            showSword(direction);
            setAttacking(true);

        }

        //System.out.println(this.attackImageIndex);
        Image[] attackImages = new Image[]{new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/SWORD/SWORD_" +direction.name()+ "_1.png"),
                new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/SWORD/SWORD_" +direction.name()+ "_2.png"),
                new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/SWORD/SWORD_" +direction.name()+ "_3.png")};


        Image[] playerAttackImages = new Image[]{new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player-attack/ATTACK_" +direction.name()+ "_1.png"),
                new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player-attack/ATTACK_" +direction.name()+ "_2.png"),
                new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player-attack/ATTACK_" +direction.name()+ "_3.png")};


        this.playerSword.changeSwordImage(attackImages[attackImageIndex]);

        this.tileImageView.setImage(playerAttackImages[attackImageIndex]);

        this.attackImageIndex += 1;



    }


    public void endAttack(){
        setAttacking(false);
        this.getChildren().remove(this.playerSword);
        this.playerSword.setLayoutY(0);
        this.playerSword.setLayoutX(0);
        setAttackImageIndex(0);
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

    public void addThrowingWeapon(Directions direction){

        switch (direction){
            case UP -> this.playerSword.setLayoutY(-super.getTileHeight());
            case DOWN -> this.playerSword.setLayoutY(super.getTileHeight());
            case LEFT -> this.playerSword.setLayoutX(-super.getTileWidth());
            case RIGHT -> this.playerSword.setLayoutX(super.getTileWidth());
        }


        this.getChildren().add(this.throwingWeapon);
        throwingWeapon.initializeThrowingImage(direction);
    }



    public void throwWeapon(Directions direction){
        //com/jdelza/view/assets/player/PLAYER_INJURE/DOWN_1_2.png
        Image[] playerAttackImages = new Image[]{new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player-attack/ATTACK_" +direction.name()+ "_1.png"),
                new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player-attack/ATTACK_" +direction.name()+ "_2.png"),
                new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player-attack/ATTACK_" +direction.name()+ "_3.png")};
        this.tileImageView.setImage(playerAttackImages[attackImageIndex]);

        this.attackImageIndex += 1;

    }

    public void endThrowAttack(){
        setAttacking(false);
        setAttackImageIndex(0);
        this.tileImageView.setImage(walkingImage.get(getPlayerDirection())[0]);
    }


    public void endWeaponThrowing(){
        this.playerSword.setThrowing(false);
        this.getChildren().remove(this.playerSword);

        this.playerSword.setLayoutY(0);
        this.playerSword.setLayoutX(0);
        this.playerSword.setTranslateX(0);
        this.playerSword.setTranslateY(0);



    }








}
