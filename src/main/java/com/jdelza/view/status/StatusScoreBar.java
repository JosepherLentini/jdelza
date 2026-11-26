package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;



public class StatusScoreBar extends VBox {


    private HBox scoreWord;
    private HBox scoreCount;

    public StatusScoreBar() {

        this.scoreWord = new HBox();
        this.scoreCount = new HBox();


        //Current class dimensions
        this.setPrefSize(136.5, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMinSize(136.5, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMaxSize(136.5, Dimensions.STATUS_BAR_HEIGT.get());


        //scoreWord settings
        scoreWord.setPrefSize(125.5, 24);
        scoreWord.setMinSize(125.5, 24);
        scoreWord.setMaxSize(125.5, 24);
        scoreWord.setAlignment(Pos.CENTER);

        writeScoreWord();


        //scoreCount settings
        scoreCount.setPrefSize(125.5, 24);
        scoreCount.setMinSize(125.5, 24);
        scoreCount.setMaxSize(125.5, 24);
        scoreCount.setAlignment(Pos.CENTER);

        ImageView zero = new ImageView(new Image("com/jdelza/view/assets/user_section/alphabet_font/CHAR_0.png"));
        zero.setFitHeight(18);
        zero.setFitWidth(18);
        scoreCount.getChildren().add(zero);



        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(scoreWord, scoreCount);
    }

    /**
     * This function writes the word "score" in to the status bar
     */
    private void writeScoreWord(){
        String score = "score";

        for (char c : score.toCharArray()){

            String path = "com/jdelza/view/assets/user_section/alphabet_font/CHAR_"
                    + Character.toString(c).toUpperCase()
                    +".png";


            ImageView currCharImage = new ImageView(new Image(path));
            currCharImage.setFitHeight(18);
            currCharImage.setFitWidth(18);


            this.scoreWord.getChildren().add(currCharImage);



        }
    }


    public void incrementPlayerScore(int score){

        scoreCount.getChildren().clear();

        String[] scoreString = Integer.toString(score).split("");

        for (String number: scoreString) {
            ImageView numberImage = new ImageView(
                    new Image("com/jdelza/view/assets/user_section/alphabet_font/CHAR_"+number.toUpperCase()+".png")
            );
            numberImage.setFitHeight(18);
            numberImage.setFitWidth(18);
            scoreCount.getChildren().add(numberImage);
        }
    }





}
