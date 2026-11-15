package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.ItemType;
import com.jdelza.utils.enums.StartEndStatus;
import com.jdelza.utils.events.*;
import com.jdelza.utils.interfaces.EnvironmentSwitcher;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.OctorockView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.characters.TektiteView;
import com.jdelza.view.items_objects.ItemView;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.DungeonScreen;
import com.jdelza.view.screen.GameScreen;
import com.jdelza.view.screen.ShopScreen;
import com.jdelza.view.weapons.SwordView;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;
import java.util.List;


public class GameView extends Observable implements Observer{


    private GameScreen gamescreen;
    private GameModel gameModel;

    private PlayerView player;
    /**
     * GameView constructor
     */
    public GameView(GameModel gameModel) {

        this.player = new PlayerView();

        this.gamescreen = new GameScreen(new Overworld(), player);
        gamescreen.getOverworld().setLayoutX(-gameModel.getPlayer().getPlayerMapPosition().getX() * Dimensions.MAP_WIDTH.get());
        gamescreen.getOverworld().setLayoutY(-gameModel.getPlayer().getPlayerMapPosition().getY() * Dimensions.MAP_HEIGHT.get());

        //gamescreen.addEnemy(new EnemyView(new Coordinates(7,7),new Coordinates(5,5)));

        this.gameModel = gameModel;
        addObserver(gameModel);

        gamescreen.getPlayer().setTranslateX(gamescreen.getPlayer().getTileWidth()  * gameModel.getPlayer().getPosition().getX());
        gamescreen.getPlayer().setTranslateY(gamescreen.getPlayer().getTileHeight() * gameModel.getPlayer().getPosition().getY());


        Arrays.stream(gameModel.getCurrentMap().getMap())
                .toList()
                .stream()
                .flatMap(Arrays::stream)
                .map(z->z.getEnemies())
                .flatMap(List::stream).forEach(
                        e-> {
                            //System.out.println("add " + e.getEnemyID());
                            EnemyView newEnemyView = null;
                            //!!!FACTORY PATTERN MAYBE!!!!
                            switch(e.getEnemyType()){
                                case OCTOROK -> newEnemyView = new OctorockView(e.getEnemyID());
                                case TEKTITE -> newEnemyView = new TektiteView(e.getEnemyID());
                            }
                            //EnemyView newEnemyView = new EnemyView(e.getEnemyMapCoordinates(), e.getPosition());
                            if (newEnemyView != null){
                                gamescreen.addAndPlaceEnemy(newEnemyView, e.getEnemyMapCoordinates(), e.getPosition());
                            }



                        }
                );

        //gamescreen.placeEnemies();

    }


    //Get methods
    public GameScreen getGamescreen() {return gamescreen;}

    public void injuring(Directions direction){

        gamescreen.getPlayer().setInjuring(true);
        AnimationTimer injureAnimation = new AnimationTimer() {

            final long period = 200_000_000;
            final long end = 2_000_000_000;

            long current = 0;
            long start = 0;


            @Override
            public void handle(long l) {
                if (start == 0)start = l;

                if (l- start > end){
                    stop();

                    gamescreen.getPlayer().setTileImageView(gamescreen.getPlayer().getWalkingImage().get(gamescreen.getPlayer().getPlayerDirection())[0]);
                    gamescreen.getPlayer().setInjuring(false);

                    setChanged();
                    notifyObservers(new PlayerCollision(false));

                } else if(l - current > period){

                    player.changeInjureColor();
                    current = l;

                }

            }


        };

        injureAnimation.start();
    }

    public void playerViewAttack(Weapon weapon, boolean throwing){

        Directions direction = weapon.getWeaponDirection();
        player.attack(direction);

        AnimationTimer attackAnimation = new AnimationTimer() {

            final long period = 200_000_000;

            long current = 0;
            int count = 0;

            @Override
            public void handle(long l) {


                if (count == 0){
                    if(l - current > period){
                        changeHandle();
                        count ++;
                        current = l;

                        if (throwing && !gamescreen.getOverworldWrapper().getChildren().contains(player.getThrowingWeapon())){
                            System.out.println("muovi");
                            player.addPlayerWeaponToTheScreen(gamescreen.getOverworldWrapper(), weapon);
                            setChanged();
                            notifyObservers(new SwordThrowing(direction, StartEndStatus.START));
                        }
                    }
                }else if(count > 0 && count < 3){
                    if(l - current > period){
                        changeHandle();
                        count ++;
                        current = l;


                    }

                }
                else{

                    stop();


                    player.endAttack();
                    setChanged();
                    notifyObservers(new PlayerAttack(weapon, throwing, StartEndStatus.END));


                }


            }

            private void changeHandle(){

                player.getPlayerSword().changeSwordImage(player.getSwordAttackImages().get(direction)[count]);
                player.setTileImageView(player.getPlayerAttackImages().get(direction)[count]);
            }

        };

        attackAnimation.start();

    }

    public void endMoveThrownWeaponView(){

        gamescreen.getPlayer().getThrowingWeapon().swordCollision(gamescreen.getOverworldWrapper(), player);

        setChanged();
        notifyObservers(new SwordThrowing(Directions.DOWN, StartEndStatus.END));

    }

    public void playerTakeItemAnimation(ItemType itemType){

        if(gamescreen.getCurrentEnvironment() instanceof ShopScreen sc){
            sc.removeShopItem();
            //player.takeItemAnimation(itemType);

            ItemView item = new ItemView(itemType);

            item.setLayoutY(-Dimensions.TILE_HEIGT.get()-10);

            player.takeItem(item);
            //player.setLayoutY( -Dimensions.TILE_HEIGT.get());

            TranslateTransition transition  = new TranslateTransition();
            transition.setNode(item);

            transition.setDuration(Duration.seconds(1));

            transition.play();

            transition.setOnFinished(e->{
                player.getChildren().remove(item);
                player.setTileImageView(player.getWalkingImage().get(Directions.DOWN)[0]);

                setChanged();
                notifyObservers(new PlayerTakeItem(itemType, StartEndStatus.END));

            });


        }

    }

    public void changeZone(Directions direction, Pane currentEnvironment){

        int addPlayerPositionIfInDungeon = 0;

        if (currentEnvironment instanceof DungeonScreen){
            player.setOpacity(0);
            addPlayerPositionIfInDungeon = 1;
        }

        //setZoneChanging(true);

        //Create traslate transition
        TranslateTransition zoneSwitchTransition = new TranslateTransition();

        TranslateTransition playerSwitchTransition = new TranslateTransition();



        //Add node to transition
        zoneSwitchTransition.setNode(currentEnvironment);
        playerSwitchTransition.setNode(player);

        //Combine two transition witch works at the same time
        ParallelTransition parallel = new ParallelTransition(
                zoneSwitchTransition,playerSwitchTransition
        );

        switch (direction) {
            case UP:
                //World translation and player movement in tandem with the world
                zoneSwitchTransition.setToY(currentEnvironment.getTranslateY() + Dimensions.MAP_HEIGHT.get());
                playerSwitchTransition.setToY((Dimensions.ZONE_ROWS.get() - 1 - addPlayerPositionIfInDungeon) * player.getTileHeight() );

                break;

            case DOWN:
                //World translation and player movement in tandem with the world

                zoneSwitchTransition.setToY(currentEnvironment.getTranslateY() - Dimensions.MAP_HEIGHT.get());
                playerSwitchTransition.setToY(  addPlayerPositionIfInDungeon*player.getTileHeight());


                break;

            case LEFT:
                //World translation and player movement in tandem with the world
                zoneSwitchTransition.setToX(currentEnvironment.getTranslateX() + Dimensions.MAP_WIDTH.get());
                playerSwitchTransition.setToX((Dimensions.ZONE_COLUMNS.get() - 1 - addPlayerPositionIfInDungeon) * player.getTileWidth() );
                break;

            case RIGHT:
                //World translation and player movement in tandem with the world
                zoneSwitchTransition.setToX(currentEnvironment.getTranslateX() - Dimensions.MAP_WIDTH.get() );
                playerSwitchTransition.setToX(addPlayerPositionIfInDungeon*player.getTileWidth() );

                break;
        }



        //Transition duration
        zoneSwitchTransition.setDuration(Duration.millis(1000));
        playerSwitchTransition.setDuration(Duration.millis(1000));

        //Start transition
        parallel.play();

        //When transition ends
        parallel.setOnFinished(e-> {
            //setZoneChanging(false);
            setChanged();
            notifyObservers(new ChangeZone(direction, StartEndStatus.END));

            player.setOpacity(100);
            player.changeSprite(direction);



        });


    }



    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PlayerMovement pm){
            gamescreen.movePlayer(pm);
        }
        if (arg instanceof WalkMovement walkMovement){
            player.changeSprite(walkMovement.getDirection());
        }

        if (arg instanceof ChangeZone cz){
            changeZone(cz.getDirection(), gamescreen.getCurrentEnvironment());
        }

        else if (arg instanceof EnemyMovement em){
            gamescreen.moveEnemies(em.getEnemies());

        }
        else if (arg instanceof Attack a){
            gamescreen.moveWeapons(a.getWeapons());
        }

        else if(arg instanceof Weapon w){
            gamescreen.addWeapon(w);

        } else if (arg instanceof RemoveWeapons remove) {
            gamescreen.removeEnemyWeapon(remove.getDeleteWeapons());

        }else if(arg instanceof PlayerCollision pc){
            injuring(pc.getPlayerDirection());

        }

        if(arg instanceof PlayerAttack pa){
            playerViewAttack(pa.getPlayerWeapon(), pa.isThrowWeapon());
        }

        if (arg instanceof SwordThrowing) {
            SwordThrowing s = (SwordThrowing) arg;
            if (s.getStatus() == StartEndStatus.END){
                endMoveThrownWeaponView();
            }else{

                player.moveThrownWeaponView(s.getDirection());
            }

        }

        if(arg instanceof EnemyDeath ed){
            gamescreen.removeEnemies(ed.getDeathEnemy());

        }

        if (arg instanceof EnvironmentSwitcher env){
            switch (env.getEnvironmentType()){
                case SHOP -> {
                    EnterTheShop esh = (EnterTheShop) env;
                    gamescreen.enterTheShopView(esh.getPlayerEntryPosition(), esh.getNpcType(), esh.getItemType(), esh.getPlayerDirection());
                }
                case DUNGEON -> {

                    EnterDungeon edu = (EnterDungeon) env;
                    gamescreen.enterDungeon(edu.getEntryPosition(), edu.getDungeonLevel(), edu.getPlayerDirection());
                }
                case OVERWORLD -> {

                    EnterOverworld eov = (EnterOverworld) env;
                    gamescreen.enterOverworld(eov.getPlayerEntryPosition(), eov.getPlayerDirection());
                }
            }
        }

        if (arg instanceof PlayerTakeItem pti){
            playerTakeItemAnimation(pti.getItem());
        }





    }


}


