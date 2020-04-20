/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.highscore;

import java.io.*;
import java.util.*;
/**
 *
 * @author ikpa
 */
public class ScoreReader {
    private File f;
    
    public ScoreReader() {
        f = new File("high_score.txt");
    }
    
    public void writeScore(String name, int score) {
        try {
            FileWriter fw = new FileWriter("high_score.txt", true);
            fw.write(name + "," + score + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Virhe tiedostoa lukiessa");
            e.printStackTrace();
        }
    }
    
    public void clear() {
        try {
            f.delete();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Virhe tiedostoa puhdistettaessa");
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> readScore() {
        ArrayList<String> arr = new ArrayList<>();
        
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                arr.add(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Virhe tiedostoa lukiessa");
            e.printStackTrace();
        }
        
        arr.sort((s1, s2) -> {
            String[] s1a = s1.split(",");
            String[] s2a = s2.split(",");
            Integer x = Integer.valueOf(s2a[1]) - Integer.valueOf(s1a[1]);
            return x;
        });
        
        return arr;
    }
    
    public ArrayList<String> neatScores() {
        ArrayList<String> scores = readScore();
        ArrayList<String> newscore = new ArrayList<>();
        
        for (int i = 0; i < scores.size(); i++) {
            String[] s = scores.get(i).split(",");
            String newS = s[0] + ": " + s[1] + " pistettä";
            newscore.add(i, newS);
        }
        
        return newscore;
    }
}
