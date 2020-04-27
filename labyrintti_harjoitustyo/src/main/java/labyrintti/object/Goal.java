/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.object;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import labyrintti.chars.MainChara;
/**
 * Maali, johon pelihahmon on pyrittävä. Vie seuraavaan kenttään.
 * @author ikpa
 */
public class Goal {
    private Circle area;
    
    /**
     * Luo uuden Goal-olion.
     * @param x Maalin x-koordinaatti
     * @param y Maalin y-koordinaatti
     * @param r Maalin säde
     */
    public Goal(int x, int y, int r) {
        area = new Circle(x, y, r, Color.FORESTGREEN);
    }
    
    /**
     * Kertoo, onko MainChara-olio maalin sisällä
     * @param chara MainChara-olio, jota tarkistetaan
     * @return true jos olio on maalin sisällä, false jos ei
     */
    public boolean inGoal(MainChara chara) {
        Shape sec = Shape.intersect(area, chara.getCircle());
        double x = sec.getBoundsInLocal().getWidth();
        
        return x >= chara.getCircle().getRadius() * 2;
    }

    public Circle getArea() {
        return area;
    }
    
    
}
