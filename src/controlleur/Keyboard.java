package controlleur;

import vue.CameraStrategy;
import vue.UserCamera;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Ecouteur de clavier.

/*
Nous avons utilisé des InputMap.
Ici les commandes sont :
Z : Avancer
Q : Gauche
S : Reculer
D : Droite

Flèche de gauche : Bouger la caméra à gauche
Flèche de droite : Bouger la caméra à droite
Flèche du haut : Bouger la caméra du haut
Flèche du bas : Bouger la caméra du bas

A : Monter
E : Descendre

 */
public class Keyboard {

    protected final CameraStrategy camera;
    private static final int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;

    public Keyboard(CameraStrategy camera) {
        this.camera = camera;

        InputMap inputMap = this.camera.getInputMap(condition); // Permet de récupérer les InputMap du composant component (ici un JPanel). Les InputMap permettent d'associer des variables à des touches du clavier. La variable condition est un entier qui permet de faire en sorte que les fonctions activées par ce système sont mises en marche seulement si cette fenêtre est ciblée par l'utilisateur.
        ActionMap actionMap = this.camera.getActionMap(); // Permet de récupérer les ActionMap du composant component (ici un JPanel). Les ActionMap permettent d'associer une fonction à une variable définie par les InputMap.

        String z = "Z";
        inputMap.put(KeyStroke.getKeyStroke("Z"), z); // Permet d'associer à la variable arrowU la touche flèche du haut du clavier, définie avec KeyStroke.getKeyStroke("UP").
        String q = "Q";
        inputMap.put(KeyStroke.getKeyStroke("Q"), q);
        String s = "S";
        inputMap.put(KeyStroke.getKeyStroke("S"), s);
        String d = "D";
        inputMap.put(KeyStroke.getKeyStroke("D"), d);

        String arrowU = "arrowU";
        inputMap.put(KeyStroke.getKeyStroke("UP"), arrowU); // Permet d'associer à la variable arrowU la touche flèche du haut du clavier, définie avec KeyStroke.getKeyStroke("UP").
        String arrowD = "arrowD";
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), arrowD);
        String arrowR = "arrowR";
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), arrowR);
        String arrowL = "arrowL";
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), arrowL);

        String a = "A";
        inputMap.put(KeyStroke.getKeyStroke("A"), a);
        String e = "E";
        inputMap.put(KeyStroke.getKeyStroke("E"), e);

        actionMap.put(z, new UserAction(this.camera, 0)); // Permet de lancer une action si la touche "Z" du clavier est détectée.
        actionMap.put(q, new UserAction(this.camera, 1));
        actionMap.put(s, new UserAction(this.camera, 2));
        actionMap.put(d, new UserAction(this.camera, 3));

        actionMap.put(arrowU, new CameraAction(this.camera, 0)); // Permet de lancer une action si la touche flèche du haut du clavier est détectée.
        actionMap.put(arrowD, new CameraAction(this.camera, 1));
        actionMap.put(arrowR, new CameraAction(this.camera, 2));
        actionMap.put(arrowL, new CameraAction(this.camera, 3));

        actionMap.put(a, new HeightAction(this.camera, 1));
        actionMap.put(e, new HeightAction(this.camera, -1));
    }

    // Action qui permet de bouger l'utilisateur.
    private static class UserAction extends AbstractAction {
        CameraStrategy camera;
        int direction;

        UserAction(CameraStrategy camera, int direction) {
            this.camera = camera;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.camera.moveUser(this.direction); // Déplace le joueur sélectionné de la direction this.direction.
        }
    }

    // Action qui permet de bouger la caméra
    private static class CameraAction extends AbstractAction {
        CameraStrategy camera;
        int direction;

        CameraAction(CameraStrategy camera, int direction) {
            this.camera = camera;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.camera.moveCamera(this.direction); // Déplace le joueur sélectionné de la direction this.direction.
        }
    }

    private static class HeightAction extends AbstractAction {
        CameraStrategy camera;
        int direction;

        HeightAction(CameraStrategy camera, int direction) {
            this.camera = camera;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.camera.moveHeight(this.direction); // Déplace le joueur sélectionné de la direction this.direction.
        }
    }
}