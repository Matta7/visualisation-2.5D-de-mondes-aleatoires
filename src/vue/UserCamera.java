package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import model.ColorMap;
import model.WorldMap;

public class UserCamera extends JPanel implements CameraStrategy {
    private int x;
    private int y;
    private final int distance;
    private double height;
    private final float scaleHeight;
    private int horizon;
    private float phi;
    private final int sensibiliteRotation ;
    private final WorldMap map;
    private ColorMap colorMap;
    private final int screenWidth;
    private final int screenHeight;

    public UserCamera(WorldMap map, ColorMap colorMap) {
        this.map = map;
        this.colorMap = colorMap;
        this.height = 1;
        this.scaleHeight = 10000;
        this.distance = 200;
        this.horizon = 60;
        this.sensibiliteRotation = 10;
        this.phi = 0;
        this.screenWidth = 600;
        this.screenHeight = 600;
        this.setBackground(new Color(119, 181, 254));

        this.x = this.map.getWorldMap().length / 2;
        this.y = this.map.getWorldMap().length / 2; // Pour se placer au milieu de la map
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        float sinphi = (float)Math.sin(this.phi);
        float cosphi = (float)Math.cos(this.phi);

        for(int distanceActu = this.distance; distanceActu != 1; distanceActu--){
            Point pleft = new Point((-cosphi*distanceActu - sinphi*distanceActu) + this.x, (sinphi*distanceActu - cosphi*distanceActu) + this.y); // Point le plus a gauche
            Point pright = new Point((cosphi*distanceActu - sinphi*distanceActu) + this.x,(-sinphi*distanceActu - cosphi*distanceActu) + this.y);
            float dx = (pright.getX() - pleft.getX()) / this.screenWidth; // Ratio du nombre de point
            float dy = (pright.getY() - pleft.getY()) / this.screenWidth;
            for(int i = 0; i < this.screenWidth; i++){ // on boucle sur la taille de l'écran
                double heightOnScreen = (this.height - this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation()) / distanceActu * this.scaleHeight + this.horizon;
                Color color = this.colorMap.getColor(Math.round(pleft.getX()), Math.round(pleft.getY()));
                drawVerticalLine(g, (int) (255-heightOnScreen), i,color);
                pleft.setX(pleft.getX() + dx); // Problème a régler par rapport au débordement
                pleft.setY(pleft.getY() + dy);
            }
        }
        /* Version avec plus de performance, mais ne marche pas correctement -----------------------------------------------
        double[] ybuffer = new double[screenWidth];// Tableau de taille screenHeight contenant que des 0
        for(int i = 0; i < screenWidth; i++){
            ybuffer[i] = screenHeight;
        }
        double dz = 1.0;
        double z = 1.0;
        while(z < this.distance){
            Point pleft = new Point((float) ((-cosphi*z - sinphi*z) + this.x), (float) ((sinphi*z - cosphi*z) + this.y)); // Point le plus a gauche
            Point pright = new Point((float) ((cosphi*z - sinphi*z) + this.x), (float) ((-sinphi*z - cosphi*z) + this.y));
            float dx = (pright.getX() - pleft.getX()) / this.screenWidth; // Ratio du nombre de point
            float dy = (pright.getY() - pleft.getY()) / this.screenWidth;
            for(int i = 0; i < this.screenWidth; i++){
                double heightOnScreen = (this.height - this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation()) / z * this.scaleHeight + this.horizon;
                //int[] RGB =  new int[]{this.colorMap.getCase(Math.round(pleft.getX()), Math.round(pleft.getY()))[0], this.colorMap.getCase(Math.round(pleft.getX()), Math.round(pleft.getY()))[1], this.colorMap.getCase(Math.round(pleft.getX()), Math.round(pleft.getY()))[2]};
                Color color = this.colorMap.getColor(Math.round(pleft.getX()), Math.round(pleft.getY()));
                //drawVerticalLine(g, (int) (255-heightOnScreen), i, new Color(RGB[0],RGB[1],RGB[2]));
                drawVerticalLine(g,(int) (heightOnScreen),ybuffer[i],i, color);
                if(heightOnScreen < ybuffer[i]){
                    ybuffer[i] = heightOnScreen;
                }
                pleft.setX(pleft.getX() + dx); // Problème a régler par rapport au débordement
                pleft.setY(pleft.getY() + dy);
            }
            z += dz;
            dz += 0.2;
        }
        ------------------------------------------------------------------------------------------------
        */
    }

    public void drawVerticalLine(Graphics g, int height, int x, Color color) {
        g.setColor(color);
        g.fillRect(x, this.screenHeight - height, 1, height); //a voir avec drawline
    }

    /* Version avec drawline----------------------------------------------
    public void drawVerticalLine(Graphics g, double heightBottom, double heightTop, int x, Color color) {
        if(heightTop <= heightBottom) return;
        if (heightTop < 0) {
            heightTop = 0;
        }
        g.setColor(color);
        g.drawLine(x, (int)Math.floor(heightTop), x, (int)Math.floor(heightBottom));
        //g.fillRect(x, this.screenHeight - height, 1, height); //a voir avec drawline
    }
    ----------------------------------------------------------------------
    */

    @Override
    public void moveUser(int direction) {
        int angle = (int) Math.round(this.phi * (180/Math.PI));
        boolean diagonal = false;
        if (angle >= 30 && angle < 60) {
            diagonal = true;
        } else if (angle >= 60 && angle < 120) {
            direction = (direction + 1) % 4;
        } else if (angle >= 120 && angle < 150) {
            direction = (direction + 1) % 4;
            diagonal = true;
        } else if (angle >= 150 && angle < 210) {
            direction = (direction + 2) % 4;
        } else if (angle >= 210 && angle < 240) {
            direction = (direction + 2) % 4;
            diagonal = true;
        } else if (angle >= 240 && angle < 300) {
            direction = (direction + 3) % 4;
        } else if (angle >= 300 && angle < 330) {
            direction = (direction + 3) % 4;
            diagonal = true;
        }

        switch (direction) {
            case 0:
                if (diagonal) {
                    --this.x;
                }
                --this.y;
                break;
            case 1:
                --this.x;
                if (diagonal) {
                    ++this.y;
                }
                break;
            case 2:
                if (diagonal) {
                    ++this.x;
                }
                ++this.y;
                break;
            case 3:
                ++this.x;
                if (diagonal) {
                    --this.y;
                }
                break;
        }
        float actualHeight = this.map.getCase(this.x,this.y).getElevation();
        if(this.height < actualHeight){
            this.height = actualHeight;
        }
        this.repaint();
    }

    @Override
    public void moveCamera(int direction) {
        //HORIZON VERTICAL -----------------
        if(direction == 0 && this.horizon < 1200){
            this.horizon+=10;
        }
        if(direction == 1 && this.horizon > -600){
            this.horizon-=10;
        }
        //ROTATION ----------------------
        if(direction == 2){//rotation a droite
            this.phi -= (Math.PI / 180) * this.sensibiliteRotation;
            if(this.phi < 0){
                this.phi += (Math.PI / 180) * 360;
            }
        }
        if(direction == 3){//rotation a gauche
            this.phi+= (Math.PI / 180) * this.sensibiliteRotation;
            if(this.phi > (Math.PI / 180) * 360){
                this.phi-=(Math.PI / 180) * 360;
            }
        }
        System.out.println(Math.round(this.phi * (180/Math.PI)));
        repaint();
    }

    @Override
    public void moveHeight(int direction) {
        this.height += direction*0.1;
        float actualHeight = this.map.getCase(this.x,this.y).getElevation();
        if(this.height < actualHeight){
            this.height = actualHeight;
        }
        this.repaint();
    }

    @Override
    public void setColorMap(int index) {
        this.colorMap = new ColorMap(this.map, index);
        repaint();
    }
}
