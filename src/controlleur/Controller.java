package controlleur;

import model.AdvanceWorldMap;
import model.BasicWorldMap;
import model.ColorMap;
import model.WorldMap;
import vue.*;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Controller {

    //Classe qui créer le model et la vue.

    protected CameraStrategy camera;
    protected WorldMap map;
    protected Keyboard keyboard;
    protected Vue vue;

    public Controller() {

        //this.map = new BasicWorldMap(40);


        //this.map = new AdvanceWorldMap(1024, rand.nextInt());
        /*
        for(int i = 0; i < map.getWorldMap().length; i++){
            for(int j = 0; j < map.getWorldMap().length; j++){
                System.out.print("[" + map.getWorldMap()[i][j] + "] ");
            }
            System.out.println("");
        }

         */

        new VueOptions(this);

        this.keyboard = new Keyboard(this.camera);

        this.vue = new Vue(this.map, this.camera, this);
    }

    public void createWorldMap(int worldMap, int size, int seed) {
        if(worldMap == 0) { // Si l'option "AdvanceWorldMap a été choisie
            if(seed == 0) {
                Random rand = new Random();
                this.map = new AdvanceWorldMap(size, rand.nextInt());
            }
            else {
                this.map = new AdvanceWorldMap(size, seed);
            }
        }

        /*else {
            this.map = new BasicWorldMap(size);
        }*/
    }

    public void setCamera(int camera) {
        if(camera!=0) { // Si l'option "1ere personne" a été choisie
            this.camera = new TopCamera(this.map, new ColorMap(this.map, 0));
        }
        else {
            this.camera = new UserCamera(this.map, new ColorMap(this.map, 0));
        }
    }

    public void changeCamera(CameraStrategy camera) {
        this.keyboard = new Keyboard(camera);
        this.vue.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.vue.dispatchEvent(new WindowEvent(this.vue, WindowEvent.WINDOW_CLOSING)); // Ferme cette fenêtre.
        this.vue = new Vue(this.map, camera, this);
    }
}
