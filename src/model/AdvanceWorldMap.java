package model;

public class AdvanceWorldMap implements WorldMap{

    private int size;
    private Case[][] worldMap;

    public AdvanceWorldMap(int size, int seed){
        this.size = size;
        int seed2 = seed;
        AdvanceNoise noise = new AdvanceNoise(seed);
        noise.SetNoiseType(AdvanceNoise.NoiseType.OpenSimplex2);

        this.worldMap = new Case[this.size][this.size];

        for(int y = 0; y < this.size; y++){
            for(int x = 0; x < this.size; x++){
                double e0 = 1 * ridgenoise(1 * x, 1 * y,noise);
                double e1 =  0.5 * ridgenoise(2 * x, 2 * y,noise) * e0;
                double e2 = 0.25 * ridgenoise(4 * x, 4 * y,noise) * (e0+e1);
                double elevation = (e0 + e1 + e2) / (1 + 0.5 + 0.25);
                this.worldMap[x][y] = new Case((float) elevation);
                /* Ancienne version de la génération aléatoire -----------------------
                float elevation = 1 * ((noise.GetNoise(x,y) + 1)/2) + 0.2f * ((noise.GetNoise(3*x,3*y) + 1) / 2) + 0.09f * ((noise.GetNoise(8*x,8*y) + 1) / 2); //Octave
                elevation = elevation/(1+0.2f+0.09f);
                elevation = (float) Math.pow(elevation,5);
                this.worldMap[x][y] = new Case(elevation);
                ------------------------------------------------------------------------
                */
            }
        }
        //Génération de la deuxième seed en fonction de la première (pour le niveau d'humidité)
        for(int i = 0; i < 16; i++){
            seed2 = (int) Math.pow(seed2,2);
            seed2 = seed2 % 100000;
        }
        AdvanceNoise noise2 = new AdvanceNoise(seed2);
        for(int y = 0; y < this.size; y++){
            for(int x = 0; x < this.size; x++){
                this.worldMap[x][y].setHumidite((noise2.GetNoise(x,y) + 1) / 2);
            }
        }
    }

    public double ridgenoise(int nx, int ny, AdvanceNoise noise) {
        return 2 * (0.5 - Math.abs(0.5 - noise.GetNoise(nx, ny)));
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
        return this.worldMap[y][x];
    }
}
