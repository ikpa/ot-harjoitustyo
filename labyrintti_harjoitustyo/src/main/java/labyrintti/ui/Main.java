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
import javafx.scene.paint.Color;
import javafx.geometry.*;

import java.util.*;

import labyrintti.chars.*;

public class Main extends Application {
    private int i=0;
    private MainChara c;
    private boolean select = false;

    @Override
    public void start(Stage stage) {
        Button start = new Button("Aloita peli");
        Button exit = new Button("Poistu");
        Button lvlslct = new Button("Tasovalikko");
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        
        c = new MainChara(0, 0, 10, 1, 1);
        
        buttons.getChildren().add(start);
        buttons.getChildren().add(lvlslct);
        buttons.getChildren().add(exit);
        
        BorderPane set = new BorderPane();
        set.setCenter(buttons);
        
        BorderPane slct = new BorderPane();
        Button test = new Button("Testikenttä");
        Button test2 = new Button("Testikenttä 2");
        VBox lvlbuttons = new VBox();
        lvlbuttons.getChildren().add(test);
        lvlbuttons.getChildren().add(test2);
        slct.setCenter(lvlbuttons);
        
        BorderPane lvlset = new BorderPane();
        HBox infodisp = new HBox();
        Pane pic = new Pane();
        pic.setPrefSize(10, 10);
        Circle l = new Circle(5, 5, 10, Color.PURPLE);
        pic.getChildren().add(l);
        infodisp.getChildren().add(l);
        Label lives = new Label("" + c.getLives() + " elämää");
        infodisp.getChildren().add(lives);
        Label points = new Label("0 pistettä");
        infodisp.getChildren().add(points);
        lvlset.setTop(infodisp);
        infodisp.setSpacing(10);
        
        LvlConstructor lc = new LvlConstructor();
        ArrayList<Level> lvls = lc.allLvls();
        int max = lvls.size();
        
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
        
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                
                if (c.isDead()) {
                    Platform.exit();
                    
                } else {
                    lvls.get(i).update(buttonPress, c, 22.5);
                }
                
                if (lvls.get(i).getG().inGoal(c)) {
                    i++;
                    
                    if (i == max || select) {
                        Platform.exit();
                        return;
                    }
                    
                    lvls.get(i).initialise(c);
                    lvlset.setCenter(lvls.get(i).getStg());
                }
                
                lives.setText("" + c.getLives() + " elämää");
                points.setText("" + c.getPoints() + " pistettä");
            }
        }.start();
        
        exit.setOnAction(e-> Platform.exit());
        start.setOnAction(e -> {
            lvls.get(2).initialise(c);
            lvlset.setCenter(lvls.get(2).getStg());
            stage.setScene(lvl);
            i = 2;
        });
        lvlslct.setOnAction(e -> stage.setScene(lvlselect));
        test.setOnAction(e -> {
            lvls.get(1).initialise(c);
            lvlset.setCenter(lvls.get(1).getStg());
            stage.setScene(lvl);
            i = 0;
            select = true;
        });
        test2.setOnAction(e -> {
            lvls.get(2).initialise(c);
            lvlset.setCenter(lvls.get(2).getStg());
            stage.setScene(lvl);
            i = 1;
            select = true;
        });
        
        stage.setTitle("Labyrintti");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
