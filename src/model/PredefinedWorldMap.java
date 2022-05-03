package model;

public class PredefinedWorldMap implements WorldMap {
    private Case[][] worldMap;
    public PredefinedWorldMap(){
        Case[][] worldmap = new Case[5][5];

        worldmap[0][0] = new Case(255);
        worldmap[0][1] = new Case(255);
        worldmap[0][2] = new Case(255);
        worldmap[0][3] = new Case(255);
        worldmap[0][4] = new Case(255);
        worldmap[1][0] = new Case(255);
        worldmap[1][1] = new Case(255);
        worldmap[1][2] = new Case(255);
        worldmap[1][3] = new Case(255);
        worldmap[1][4] = new Case(255);
        worldmap[2][0] = new Case(255);
        worldmap[2][1] = new Case(255);
        worldmap[2][2] = new Case(60);
        worldmap[2][3] = new Case(255);
        worldmap[2][4] = new Case(255);
        worldmap[3][0] = new Case(255);
        worldmap[3][1] = new Case(255);
        worldmap[3][2] = new Case(255);
        worldmap[3][3] = new Case(255);
        worldmap[3][4] = new Case(255);
        worldmap[4][0] = new Case(255);
        worldmap[4][1] = new Case(255);
        worldmap[4][2] = new Case(255);
        worldmap[4][3] = new Case(255);
        worldmap[4][4] = new Case(255);
    }

    @Override
    public Case[][] getWorldMap() {
        return this.worldMap;
    }

    @Override
    public Case getCase(int x, int y) {
        while(x >= this.worldMap.length){
            x -= this.worldMap.length;
        }
        while(y >= this.worldMap.length){
            y -= this.worldMap.length;
        }
        while(x < 0){
            x += this.worldMap.length;
        }
        while(y < 0){
            y += this.worldMap.length;
        }
        return this.worldMap[y][x]; // TODO Voir avec le modulo
    }
}
