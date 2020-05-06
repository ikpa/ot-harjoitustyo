/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import javafx.scene.paint.*;
import java.util.*;
import javafx.scene.shape.*;
/**
 *
 * @author ikpa
 */
public class Enemy extends FreeMover {
    private double aggrorange;
    
    public Enemy(double x, double y, int r, double aggro, double s) {
        super(x, y, r, s, Color.RED);
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            x, y - (3 * r / 5),
            x + r, y - r,
            x + (3 * r / 5), y,
            x + r, y + r,
            x, y + (3 * r / 5),
            x - r, y + r,
            x - (3 * r / 5), y,
            x - r, y - r
        });
        
        Shape newshape = Shape.union(polygon, getArea());
        newshape.setFill(Color.RED);
        
        setArea(newshape);
        
        aggrorange = aggro;
    }
    
    @Override
    public void setLocation(double x, double y) {
        double r = getRadius();
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            x, y - (3 * r / 5),
            x + r, y - r,
            x + (3 * r / 5), y,
            x + r, y + r,
            x, y + (3 * r / 5),
            x - r, y + r,
            x - (3 * r / 5), y,
            x - r, y - r
        });
        
        Circle newcircle = new Circle(x, y, r);
        
        Shape newshape = Shape.union(polygon, newcircle);
        newshape.setFill(Color.RED);
        
        setArea(newshape);
    }
   
    
    public double xDistance(MainChara chara) {
        double enemyx = getCenterX() + getArea().getTranslateX();
        double charax = chara.getCenterX() + chara.getArea().getTranslateX();
        
        return charax - enemyx;
    }
    
    public double yDistance(MainChara chara) {
        double enemyy = getCenterY() + getArea().getTranslateY();
        double charay = chara.getCenterY() + chara.getArea().getTranslateY();
        
        return charay - enemyy;
    }
    
    public double totalDistance(MainChara chara) {
        double xdistance = xDistance(chara);
        double ydistance = yDistance(chara);
        double value = Math.pow(xdistance, 2) + Math.pow(ydistance, 2);
        
        return Math.sqrt(value);
    }
    
    public ArrayList<Boolean> enemyAllowedDirections(ArrayList<Rectangle> walls, 
            ArrayList<Enemy> enemies, double voffset) {
        ArrayList<Boolean> allowed = allowedDirections(walls, voffset);
        
        for (Enemy e: enemies) {
            if (e.equals(this) || e.isHit()) {
                continue;
            }
            
            if (!collision(e.getArea())) {
                continue;
            }
            
            double xloc = intersectXLoc(e.getArea());
            double yloc = intersectYLoc(e.getArea(), voffset);
            
            if (yloc < -8) {
                allowed.set(0, Boolean.FALSE);
            }
                
            if (xloc > 1) {
                allowed.set(1, Boolean.FALSE);
            }
                
            if (yloc > 6) {
                allowed.set(2, Boolean.FALSE);
            }
                
            if (xloc < -7) {
                allowed.set(3, Boolean.FALSE);
            }
        }
        
        return allowed;
    }
    
    public void move(ArrayList<Rectangle> walls, MainChara chara,
            ArrayList<Enemy> enemies, double voffset) {
        double xdist = xDistance(chara);
        double ydist = yDistance(chara);
        double totdist = totalDistance(chara);
        
        if (totdist > aggrorange) {
            return;
        }
        
        ArrayList<Boolean> allowed = enemyAllowedDirections(walls, enemies, voffset);
        
        double sine = ydist / totdist;
        double cosine = xdist / totdist;
        
        if (allowed.get(0) && ydist < 0) {
            moveUP(-sine);
        }
        
        if (allowed.get(1) && xdist > 0) {
            moveRIGHT(cosine);
        }
        
        if (allowed.get(2) && ydist > 0) {
            moveDOWN(sine);
        }
        
        if (allowed.get(3) && xdist < 0) {
            moveLEFT(-cosine);
        }
    }
    
}
