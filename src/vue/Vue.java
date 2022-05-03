package vue;

import controlleur.Controller;
import model.ColorMap;
import model.Images;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

// Avoir pluriseurs visu différentes (pattern strategy)
// Visu en 2D de niveau de gris
// RayCasting: Voir voxel space
public class Vue extends JFrame {

    // Classe représentant la fenêtre principale, prend un WorldMap, une CameraStrategy (pour le pattern Strategy) et le controller pour plus tard.

    private WorldMap map;
    protected CameraStrategy camera;
    private int screenWidth;
    private int screenHeight;
    protected Controller controller;

    public Vue(WorldMap map, CameraStrategy camera, Controller controller) {

        this.map = map;
        this.camera = camera;
        this.controller = controller;

        this.screenWidth = 600;
        this.screenHeight = 600;

        this.GUI();
    }

    public void GUI() {

        setTitle("Visualisation de monde aléatoire");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add((Component) this.camera, BorderLayout.CENTER);

        setContentPane(contentPane);
        setJMenuBar(menuBar());

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu cameraMenu = new JMenu("Camera");
        JMenuItem item1 = new JMenuItem("1ere personne");
        item1.addActionListener(new SwitchCameraAction(new UserCamera(this.map, new ColorMap(this.map, 0)), this.controller));
        JMenuItem item2 = new JMenuItem("Vue de dessus");
        item2.addActionListener(new SwitchCameraAction(new TopCamera(this.map, new ColorMap(this.map, 0)), this.controller));

        cameraMenu.add(item1);
        cameraMenu.add(item2);


        JMenu colorsMenu = new JMenu("Couleurs");

        JMenuItem[] items = new JMenuItem[Images.colorMapPatterns.length];

        for(int i = 0; i < Images.colorMapPatterns.length; i++) {
            items[i] = new JMenuItem(Images.imagesTab[i].getName());
            items[i].addActionListener(new ColorsMenuAction(this.camera, i));
            colorsMenu.add(items[i]);
        }

        menuBar.add(cameraMenu);
        menuBar.add(colorsMenu);

        return menuBar;
    }

    private static class SwitchCameraAction extends AbstractAction {
        CameraStrategy camera;
        Controller controller;

        SwitchCameraAction(UserCamera camera, Controller controller) {
            this.camera = camera;
            this.controller = controller;
        }

        SwitchCameraAction(TopCamera camera, Controller controller) {
            this.camera = camera;
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.controller.changeCamera(this.camera);
        }
    }


    private static class ColorsMenuAction extends AbstractAction {
        CameraStrategy camera;
        int index;

        ColorsMenuAction(CameraStrategy camera, int index) {
            this.camera = camera;
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.camera.setColorMap(this.index);
        }
    }
}
