/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labyrintti_harjoitustyo;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.geometry.*;

import java.util.*;

import labyrintti.chars.*;
import object.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Button start = new Button("Aloita peli");
        Button exit = new Button("Poistu");
        VBox buttons = new VBox();
        buttons.setSpacing(20);
        
        buttons.getChildren().add(start);
        buttons.getChildren().add(exit);
        
        BorderPane set = new BorderPane();
        set.setCenter(buttons);
        
        BorderPane lvlset = new BorderPane();
        
        Pane testlvl = new Pane();
        testlvl.setPrefSize(1000, 1000);
        wallConstructor wc = new wallConstructor();
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(600, 200, 200));
        walls.add(wc.vWall(600, 205, 200));
        
        walls.forEach(w -> testlvl.getChildren().add(w));
        mainChara c = new mainChara(500,500,10,1,1);
        testlvl.getChildren().add(c.getChara());
        lvlset.setCenter(testlvl);
        
        Scene lvl = new Scene(lvlset);
        Scene menu = new Scene(set);
        
        Map<KeyCode, Boolean> buttonPress = new HashMap();
        
        lvl.setOnKeyPressed(e -> {
            buttonPress.put(e.getCode(), Boolean.TRUE);
        });
        
        lvl.setOnKeyReleased(e -> {
            buttonPress.put(e.getCode(), Boolean.FALSE);
        });
        
        
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                
                if (c.arrayCollision(walls)) {
                    c.collisionMove(buttonPress, walls);
                } else {
                    c.move(buttonPress);
                }
                
                
            }
        }.start();
        
        exit.setOnAction(e-> Platform.exit());
        start.setOnAction(e -> stage.setScene(lvl));
        
        stage.setTitle("Labyrintti");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
