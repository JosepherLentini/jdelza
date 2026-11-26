package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class StatusBar extends HBox {


    private StatusMap statusMap;
    private ItemsBar itemsBar;
    private StatusWeaponBar statusWeaponBar;
    private StatusScoreBar statusScoreBar;
    private StatusLifeBar statusLifeBar;



    public StatusBar() {

        this.statusMap = new StatusMap();
        this.itemsBar = new ItemsBar();
        this.statusWeaponBar = new StatusWeaponBar();
        this.statusScoreBar = new StatusScoreBar();
        this.statusLifeBar = new StatusLifeBar();

        //StatusBar dimensions
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.STATUS_BAR_HEIGT.get());
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.STATUS_BAR_HEIGT.get());
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.STATUS_BAR_HEIGT.get());

        this.setSpacing(20);


        this.setAlignment(Pos.CENTER_LEFT);


        this.getChildren().addAll(statusMap, itemsBar, statusWeaponBar, statusScoreBar, statusLifeBar);

    }


    public StatusMap getStatusMap() {
        return statusMap;
    }
    public ItemsBar getItemsBar() {return itemsBar;}
    public StatusWeaponBar getStatusWeaponBar() {return statusWeaponBar;}
    public StatusScoreBar getStatusScoreBar() {return statusScoreBar;}
    public StatusLifeBar getStatusLifeBar() {return statusLifeBar;}



}
