package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorMap {

    private Color[][] colorMap;
    private Color[][] colors;

    public ColorMap(WorldMap map, int imageIndex) {

        Case[][] worldMap = map.getWorldMap();

        colorMap = new Color[worldMap.length][worldMap[0].length];

        BufferedImage image = Images.colorMapPatterns[imageIndex];
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();
        colors = new Color[imageHeight][imageWidth];

        for(int x = 0; x < imageHeight; x++) {
            //for(int y = imageWidth-1; y > 0; y--) {
            for(int y = 0; y < imageWidth; y++) {
                int pixel = image.getRGB(y,x);
                Color color = new Color(pixel, true);
                colors[x][y] = color;
            }
        }


        for(int i = 0; i < worldMap.length; i++){
            for(int j = 0; j < worldMap[0].length; j++){
                float elevation = worldMap[i][j].getElevation();
                float humidite = worldMap[i][j].getHumidite();

                int x = Math.round(elevation * imageHeight);
                int y = Math.round(humidite*imageWidth);
                if(x > imageHeight-1) x = imageHeight-1;
                if(y > imageWidth-1) y = imageWidth-1;


                this.colorMap[j][i] = colors[imageHeight - x - 1][y];


                /*if(elevation <= 60) {
                    colorMap[i][j] = new int[]{elevation, elevation, elevation};
                }

                else if(elevation <= 120) {
                    colorMap[i][j] = new int[]{(int) Math.round(elevation*0.85), (int) Math.round(elevation*0.4), 0};
                }

                else if(elevation <= 160) {
                    colorMap[i][j] = new int[]{(int) Math.round(elevation*0.15), (int) Math.round(elevation*0.6), (int) Math.round(elevation*0.15)};
                }

                else if(elevation <= 225) {
                    colorMap[i][j] = new int[]{120,120,120};
                }
                else {
                    colorMap[i][j] = new int[]{255,255,255};
                }*/
            }
        }
    }

    public Color[][] getColorMap() {
        return this.colorMap;
    }

    public Color getColor(int x, int y) {
        while(x >= this.colorMap.length){
            x -= this.colorMap.length;
        }
        while(y >= this.colorMap.length){
            y -= this.colorMap.length;
        }
        while(x < 0){
            x += this.colorMap.length;
        }
        while(y < 0){
            y += this.colorMap.length;
        }
        return this.colorMap[x][y]; // TODO Voir avec le modulo
    }
}
