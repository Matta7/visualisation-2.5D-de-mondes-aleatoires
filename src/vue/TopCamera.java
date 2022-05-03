package vue;

import model.Case;
import model.ColorMap;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra en vue de dessus. Pour l'instant avec des nuances de gris en noir et blanc.
// S'adapte à la taille de la map.

public class TopCamera extends JPanel implements CameraStrategy {

    private WorldMap map;
    private ColorMap colorMap;
    private int x;
    private int y;
    private int screenWidth;
    private int screenHeight;

    public TopCamera(WorldMap map, ColorMap colorMap) { // Pas besoins de savoir la taille de la map
        this.map = map;
        this.colorMap = colorMap;
        this.screenWidth = 600;
        this.screenHeight = 600;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                //Case square = map[x][y];
                g.setColor(this.colorMap[x][y]);
                g.fillRect(x*this.taille, y*this.taille, this.taille,this.taille);
            }
        }*/

        for(int i = 1; i < this.screenHeight; i++) {
            for(int j = 1; j < this.screenWidth; j++) {
                drawPoint(g, i, j, this.colorMap.getColor(this.x+i, this.y+j));
                /*drawPoint(g, this.x - i, this.y, this.colorMap.getColor(this.x - i, this.y));
                drawPoint(g, this.x + i, this.y, this.colorMap.getColor(this.x + i, this.y));
                drawPoint(g, this.x, this.y + i, this.colorMap.getColor(this.x, this.y + i));
                drawPoint(g, this.x, this.y - i, this.colorMap.getColor(this.x, this.y - i));
                drawPoint(g, this.x + i, this.y - i, this.colorMap.getColor(this.x + i, this.y - i));
                drawPoint(g, this.x + i, this.y + i, this.colorMap.getColor(this.x + i, this.y + i));
                drawPoint(g, this.x - i, this.y + i, this.colorMap.getColor(this.x - i, this.y + i));
                drawPoint(g, this.x - i, this.y - i, this.colorMap.getColor(this.x - i, this.y - i));*/
            }
        }
    }

    public void drawPoint(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, 1, 1);

    }

    @Override
    public void moveUser(int direction) {
        switch (direction) {
            case 0:
                this.y-=10;
                break;
            case 1:
                this.x-=10;
                break;
            case 2:
                this.y+=10;
                break;
            case 3:
                this.x+=10;
                break;
        }
        repaint();
    }

    @Override
    public void moveCamera(int direction) {

    }

    @Override
    public void moveHeight(int direction) {

    }

    @Override
    public void setColorMap(int index) {
        this.colorMap = new ColorMap(this.map, index);
        repaint();
    }
}
