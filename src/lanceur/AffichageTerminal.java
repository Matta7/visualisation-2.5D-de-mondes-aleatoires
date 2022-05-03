package lanceur;

import controlleur.Controller;
import model.BasicWorldMap;
import model.WorldMap;

public class AffichageTerminal {
    public void lancement(){

        WorldMap worldMap = new BasicWorldMap(10);
        for(int i = 0; i < worldMap.getWorldMap().length; i++){
            for(int j = 0; j < worldMap.getWorldMap().length; j++){
                System.out.print("[" + worldMap.getWorldMap()[i][j] + "] ");
            }
            System.out.println("");
        }

        new Controller();
    }
}
