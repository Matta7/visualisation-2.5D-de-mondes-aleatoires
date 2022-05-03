package controlleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Ecouteur de la souris.

public class Mouse extends MouseAdapter {

    private Controller controller;

    public Mouse(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xSouris = e.getX();
        int ySouris = e.getY();
    }
}
