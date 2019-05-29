import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class GranularWavesGraphics extends JComponent implements MouseMotionListener {
  int WIDTH = 650;
  int HEIGHT = WIDTH;
  double MASS = 1.0;
  int N = 20;
  double DT = 0.0001;
  GranularWavesData gwd;

  GranularWavesGraphics() {
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    gwd = new GranularWavesData(MASS,N,DT,WIDTH);
    addMouseMotionListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for(int i=0; i<(int)(1/DT); i++) {
      gwd.update();
    }

    for(int i=0; i < gwd.masses.length; i++) {
      g.fillOval((int) (gwd.masses[i].posX-3), 
        (int) (gwd.masses[i].posY-3), 6, 6 );
    }
  }

  public void mouseMoved(MouseEvent e) {
    gwd.cursorX = e.getX();
    gwd.cursorY = e.getY();
  }

  public void mouseDragged(MouseEvent e) {
    gwd.cursorX = e.getX();
    gwd.cursorY = e.getY();
  }

  public void mouseExited(MouseEvent e) { }

  public void mouseEntered(MouseEvent e) { }

  public void mouseReleased(MouseEvent e) { }

  public void mousePressed(MouseEvent e) { }

  public void mouseClicked(MouseEvent e) { }
}
