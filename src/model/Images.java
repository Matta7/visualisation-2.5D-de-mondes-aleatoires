package model;

import javax.imageio.ImageIO;
import javax.sound.midi.SysexMessage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Images {

    public static BufferedImage[] colorMapPatterns;
    public static File[] imagesTab;

    public static String DELIMITEUR_LINUX = "/";
    public static String DELIMITEUR_WINDOWS = "\\";
    public static String delimiteur;
    static {
        if (System.getProperty("os.name").equals("Windows")) {
            delimiteur = DELIMITEUR_WINDOWS;
        } else {
            delimiteur = DELIMITEUR_LINUX;
        }

        try {
            // Lis l'image (dans l'exemple de la ligne ci-dessous l'image de la case) dans le ficher images.
            // La variable delimiteur est faite pour pouvoir charger les images dans le fichier, pour les utilisateurs de Windows ou de Linux.

            File dir = new File("./." + delimiteur + "images");
            File[] files = dir.listFiles();


            Set<File> imagesSet = new HashSet<>();
            for(File file : files) {
                if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg"))) {
                    imagesSet.add(file);
                }
            }
            imagesTab = new File[imagesSet.size()];
            colorMapPatterns = new BufferedImage[imagesSet.size()];

            imagesTab[0]= new File("./images" + delimiteur + "colorMapPattern.png");

            int i = 1;
            for(File file : imagesSet) {
                if(!file.getName().equals(imagesTab[0].getName())) {
                    imagesTab[i] = file;
                    i++;
                }
            }

            for(i = 0; i < imagesTab.length; i++) {
                colorMapPatterns[i] = ImageIO.read(imagesTab[i]);
            }

            //colorMapPatterns[0] = ImageIO.read(new File("./images" + delimiteur + "colorMapPattern.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
