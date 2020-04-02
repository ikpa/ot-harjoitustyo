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
import levels.*;

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
        
        lvlconstructor lc = new lvlconstructor();
        level testlvl = lc.testlvl();
        
        lvlset.setCenter(testlvl.getStg());
        
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
                
                testlvl.update(buttonPress);
                
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
