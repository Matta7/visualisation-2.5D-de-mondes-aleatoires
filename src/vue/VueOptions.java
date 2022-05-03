package vue;

import controlleur.Controller;

import javax.swing.*;
import java.awt.event.WindowEvent;

// Classe qui est lancée au tout début pour définir les options du programme.

public class VueOptions extends JFrame {

    public VueOptions(Controller controller) {

        /*Object[] optionsWorldMap = {"AdvanceWorldMap (avec seed)", "BasicWorldMap (sans seed)"};
        int worldMap = afficheOptionPaneAvecOptions("Type de map ?", "Choisissez une option", optionsWorldMap);*/

        String stringSeed;
        /*if(worldMap == 0) {
            stringSeed = afficheInputDialog("Seed de génération de la map ?\n\nNe mettez que des chiffres.\n\"0\", rien ou alors des lettres\nseront considérés comme une seed aléatoire. ", "Graine ");
            if(stringSeed.isEmpty()) {
                stringSeed = "0";
            }
        }
        else {
            stringSeed = "0";
        }*/

        stringSeed = afficheInputDialog("Seed de génération de la map ?\n\nNe mettez que des chiffres.\n\"0\", rien ou alors des lettres\nseront considérés comme une seed aléatoire. ", "Graine ");
        if(stringSeed.isEmpty()) {
            stringSeed = "0";
        }


        Object[] possibleSize = new Object[100];
        for(int i = 0; i < 100; i++) {
            possibleSize[i] = i*50+400;
        }

        int size = (int) afficheInputDialogAvecPossibles("Veuillez choisir la taille de la map :", "Sélectionnez une option", possibleSize);

        Object[] optionsCamera = {"Vue 1ere personne", "Vue par dessus"};
        int camera = afficheOptionPaneAvecOptions("Type de caméra ?", "Choisissez une option", optionsCamera);


        int intSeed;
        try {
            intSeed = Integer.parseInt(stringSeed);
        }
        catch (NumberFormatException e) {
            intSeed = 0;
        }

        //controller.createWorldMap(worldMap, size, intSeed);
        controller.createWorldMap(0, size, intSeed);
        controller.setCamera(camera);

        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Ferme cette fenêtre.
    }


    // Affiche un "pop up" avec JOptionPane qui permet à l'utilisateur d'entrer une valeur et de la récupérer.
    public String afficheInputDialog(String message, String titre) {
        return JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE);
    }

    // Affiche un "pop up" avec JOptionPane qui permet à l'utilisateur d'entrer une valeur et de la récupérer.
    public int afficheOptionPaneAvecOptions(String message, String titre, Object[] options) {
        return JOptionPane.showOptionDialog(null, message, titre, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public Object afficheInputDialogAvecPossibles(String message, String titre, Object[] possibles) {
        return JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE, null, possibles, possibles[0]);
    }
}
