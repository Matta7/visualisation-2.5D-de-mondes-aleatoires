package model;

public class Case {
    // Représentation d'une case
    // Catégorie de case
    private float elevation; // 0 à 255
    private float humidite;

    public Case(float elevation) {
        if(elevation > 1){
            elevation = 1;
        }
        else if(elevation < 0) {
            elevation = 0;
        }
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "" + elevation;
    }

    public float getElevation() {
        return elevation;
    }

    public float getHumidite() {return humidite;}

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }
}
