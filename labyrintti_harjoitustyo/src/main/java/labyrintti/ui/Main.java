/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.ui;

import labyrintti.levels.LvlConstructor;
import labyrintti.levels.Level;
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

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Button start = new Button("Aloita peli");
        Button exit = new Button("Poistu");
        Button lvlslct = new Button("Tasovalikko");
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        
        buttons.getChildren().add(start);
        //buttons.getChildren().add(lvlslct);
        buttons.getChildren().add(exit);
        
        BorderPane set = new BorderPane();
        set.setCenter(buttons);
        
        BorderPane slct = new BorderPane();
        Button test = new Button("Testikenttä");
        VBox lvlbuttons = new VBox();
        lvlbuttons.getChildren().add(test);
        slct.setCenter(lvlbuttons);
        
        BorderPane lvlset = new BorderPane();
        LvlConstructor lc = new LvlConstructor();
        Level testlvl = lc.testlvl();
        lvlset.setCenter(testlvl.getStg());
        
        Scene lvl = new Scene(lvlset);
        Scene menu = new Scene(set);
        Scene lvlselect = new Scene(slct);
        
        Map<KeyCode, Boolean> buttonPress = new HashMap();
        
        lvl.setOnKeyPressed(e -> {
            buttonPress.put(e.getCode(), Boolean.TRUE);
        });
        
        lvl.setOnKeyReleased(e -> {
            buttonPress.put(e.getCode(), Boolean.FALSE);
        });
        
        
        
        
        exit.setOnAction(e-> Platform.exit());
        start.setOnAction(e -> {
            stage.setScene(lvl);
            testlvl.play(buttonPress);
        });
        /**lvlslct.setOnAction(e -> stage.setScene(lvlselect));
        test.setOnAction(e -> {
            stage.setScene(lvl);
            testlvl.play(buttonPress);
        });*/
        
        stage.setTitle("Labyrintti");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
