/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.ui;

import labyrintti.logic.freemovers.MainChara;
import labyrintti.logic.level.LvlConstructor;
import labyrintti.logic.level.Level;
import labyrintti.dao.HighScoreDao;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.scene.paint.Color;

import java.util.*;


public class Main extends Application {
    private int i=0;
    private MainChara c;
    private boolean select = false;
    private boolean over = false;
    private HighScoreDao s;

    @Override
    public void start(Stage stage) {
        c = new MainChara(0, 0, 10, 1);
        s = new HighScoreDao();
        
        Button start = new Button("Aloita peli");
        Button exit = new Button("Poistu");
        Button high = new Button("Pistelista");
        Button lvlslct = new Button("Tasovalikko");
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        
        buttons.getChildren().add(start);
        buttons.getChildren().add(lvlslct);
        buttons.getChildren().add(high);
        buttons.getChildren().add(exit);
        
        BorderPane set = new BorderPane();
        set.setCenter(buttons);
        
        BorderPane slct = new BorderPane();
        Button test = new Button("Testikenttä");
        Button test2 = new Button("Testikenttä 2");
        Button lvl3 = new Button("Taso 3");
        Button lvl4 = new Button("Taso 4");
        Button lvl5 = new Button("Taso 5");
        VBox lvlbuttons = new VBox();
        lvlbuttons.getChildren().add(test);
        lvlbuttons.getChildren().add(test2);
        lvlbuttons.getChildren().add(lvl3);
        lvlbuttons.getChildren().add(lvl4);
        lvlbuttons.getChildren().add(lvl5);
        slct.setCenter(lvlbuttons);
        
        BorderPane lvlset = new BorderPane();
        HBox infodisp = new HBox();
        Circle l = new Circle(5, 5, 10, Color.PURPLE);
        infodisp.getChildren().add(l);
        Label lives = new Label("" + c.getLives() + " elämää");
        infodisp.getChildren().add(lives);
        Label points = new Label("0 pistettä");
        infodisp.getChildren().add(points);
        Label coor = new Label("x: y:");
        infodisp.getChildren().add(coor);
        lvlset.setTop(infodisp);
        infodisp.setSpacing(10);
        
        BorderPane highs = new BorderPane();
        VBox list = new VBox();
        list.setSpacing(10);
        highs.setCenter(list);
        VBox highbuttons = new VBox();
        highbuttons.setSpacing(10);
        Button back = new Button("Takaisin");
        Button clear = new Button("Poista pistetilastot");
        highbuttons.getChildren().add(clear);
        highbuttons.getChildren().add(back);
        highs.setBottom(highbuttons);
        
        BorderPane endscreen = new BorderPane();
        HBox fields = new HBox();
        TextField namefield = new TextField();
        fields.getChildren().add(namefield);
        Button ready = new Button("Valmis");
        fields.getChildren().add(ready);
        endscreen.setCenter(fields);
        endscreen.setTop(new Label("Kirjoita nimesi ja paina 'Valmis'"));
        
        LvlConstructor lc = new LvlConstructor();
        ArrayList<Level> lvls = lc.allLvls();
        int max = lvls.size();
        
        Scene lvl = new Scene(lvlset);
        Scene menu = new Scene(set);
        Scene lvlselect = new Scene(slct);
        Scene score = new Scene(highs);
        Scene end = new Scene(endscreen);
        
        Map<KeyCode, Boolean> buttonPress = new HashMap();
        
        lvl.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.UP) 
                    || e.getCode().equals(KeyCode.DOWN)
                    || e.getCode().equals(KeyCode.LEFT) 
                    || e.getCode().equals(KeyCode.RIGHT)) {
                buttonPress.put(e.getCode(), Boolean.TRUE);
            }
            
        });
        
        lvl.setOnKeyReleased(e -> {
            if (e.getCode().equals(KeyCode.UP) 
                    || e.getCode().equals(KeyCode.DOWN)
                    || e.getCode().equals(KeyCode.LEFT) 
                    || e.getCode().equals(KeyCode.RIGHT)) {
                buttonPress.put(e.getCode(), Boolean.FALSE);
            }
        });
        
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                
                if (c.isDead()) {
                    if (c.getLives() > 0) {
                        c.addPoints(c.getLives() * 50);
                    }
                    
                    stage.setScene(end);
                    over = true;
                    return;
                } else if (! over) {
                    lvls.get(i).update(buttonPress, c, 22.5);
                    
                    if (lvls.get(i).getGoal().inGoal(c)) {
                        i++;
                        
                        if (!select) {
                            c.addPoints(200);
                        }
                    
                        if (i == max || select) {
                            if (c.getLives() > 0) {
                                c.addPoints(c.getLives() * 50);
                            }
                            
                            stage.setScene(end);
                            over = true;
                            return;
                        }
                        
                        
                    
                        lvls.get(i).initialise(c);
                        lvlset.setCenter(lvls.get(i).getStg());
                    }
                }
                
                lives.setText("" + c.getLives() + " elämää");
                points.setText("" + c.getPoints() + " pistettä");
                coor.setText("x: " + (c.getCenterX() + c.getArea().getTranslateX())
                + " y: " + (c.getCenterY() + c.getArea().getTranslateY()));
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
            lvls.get(0).initialise(c);
            lvlset.setCenter(lvls.get(0).getStg());
            stage.setScene(lvl);
            i = 0;
            select = true;
        });
        test2.setOnAction(e -> {
            lvls.get(1).initialise(c);
            lvlset.setCenter(lvls.get(1).getStg());
            stage.setScene(lvl);
            i = 1;
            select = true;
        });
        lvl3.setOnAction(e -> {
            lvls.get(4).initialise(c);
            lvlset.setCenter(lvls.get(4).getStg());
            stage.setScene(lvl);
            i = 4;
            select = true;
        });
        lvl4.setOnAction(e -> {
            lvls.get(5).initialise(c);
            lvlset.setCenter(lvls.get(5).getStg());
            stage.setScene(lvl);
            i = 5;
            select = true;
        });
        lvl5.setOnAction(e -> {
            lvls.get(6).initialise(c);
            lvlset.setCenter(lvls.get(6).getStg());
            stage.setScene(lvl);
            i = 6;
            select = true;
        });
        high.setOnAction(e -> {
            ArrayList<String> scores = s.neatScores();
            
            for (String v: scores) {
                Label h = new Label(v);
                list.getChildren().add(h);
            }
            
            stage.setScene(score);
        });
        back.setOnAction(e -> {
            stage.setScene(menu);
        });
        clear.setOnAction(e -> {
            s.clear();
            list.getChildren().clear();
            list.getChildren().add(new Label("Pistetilastot poistettu"));
            highbuttons.getChildren().remove(clear);
        });
        ready.setOnAction(e -> {
            String name = namefield.getText();
            s.writeScore(name, c.getPoints());
            
            endscreen.setTop(null);
            endscreen.setCenter(null);
            VBox newlist = new VBox();
            
            ArrayList<String> scores = s.neatScores();

            for (String v: scores) {
                Label h = new Label(v);
                newlist.getChildren().add(h);
            }
            
            newlist.getChildren().add(exit);
            endscreen.setCenter(newlist);
            stage.setHeight(scores.size() * 20 + 60);
            stage.setWidth(newlist.getWidth());
        });
        
        stage.setTitle("Labyrintti");
        stage.setScene(menu);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(Main.class);
    }
}
