package vue;

import javax.swing.*;
import java.awt.*;

// Interface pour le pattern Strategy qui décide quel mode d'affichage on veut.

public interface CameraStrategy {
    void paintComponent(Graphics g);
    void moveUser(int direction);
    void moveCamera(int direction);
    void moveHeight(int direction);
    void setColorMap(int index);

    InputMap getInputMap(int condition);
    ActionMap getActionMap();
}
