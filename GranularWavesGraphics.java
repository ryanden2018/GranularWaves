import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class GranularWavesGraphics extends JComponent implements MouseMotionListener {
  int WIDTH = 650;
  int HEIGHT = WIDTH;
  double MASS = 1.0;
  int N = 40;
  double DT = 0.01;
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


    for(int i=0; i < N-1; i++) {
      for(int j=0; j < N-1; j++) {

        double midptX = ( (gwd.masses[N*i+j].posX+gwd.masses[N*(i+1)+(j+1)].posX)/2 );
        double midptY = ( (gwd.masses[N*i+j].posY+gwd.masses[N*(i+1)+(j+1)].posY)/2 );

        double[] xVals = { (gwd.masses[N*i+j].posX), (gwd.masses[N*i+(j+1)].posX), midptX };
        double[] yVals = { (gwd.masses[N*i+j].posY), (gwd.masses[N*i+(j+1)].posY), midptY };
        ((Graphics2D)g).setColor(
          makeColor(xVals,yVals)
        );
        g.fillPolygon(castToInt(xVals),castToInt(yVals),3);


        double[] xVals2 = { (gwd.masses[N*i+j].posX), (gwd.masses[N*(i+1)+j].posX), midptX };
        double[] yVals2 = { (gwd.masses[N*i+j].posY),  (gwd.masses[N*(i+1)+j].posY), midptY };
        ((Graphics2D)g).setColor(
          makeColor(xVals2,yVals2)
        );
        g.fillPolygon(castToInt(xVals2),castToInt(yVals2),3);

        double[] xVals3 = { (gwd.masses[N*(i+1)+(j+1)].posX), (gwd.masses[N*(i+1)+j].posX), midptX };
        double[] yVals3 = { (gwd.masses[N*(i+1)+(j+1)].posY), (gwd.masses[N*(i+1)+j].posY), midptY };
        ((Graphics2D)g).setColor(
          makeColor(xVals3,yVals3)
        );
        g.fillPolygon(castToInt(xVals3),castToInt(yVals3),3);

        double[] xVals4 = {  (gwd.masses[N*(i+1)+(j+1)].posX),  (gwd.masses[N*i+(j+1)].posX), midptX };
        double[] yVals4 = {  (gwd.masses[N*(i+1)+(j+1)].posY),  (gwd.masses[N*i+(j+1)].posY), midptY };
        ((Graphics2D)g).setColor(
          makeColor(xVals4,yVals4)
        );
        g.fillPolygon(castToInt(xVals4),castToInt(yVals4),3);
      }
    }
  }

  int[] castToInt(double[] vals) {
    int[] result = {(int) vals[0], (int) vals[1], (int) vals[2]};
    return result;
  }

  Color makeColor(double[] xVals,double[] yVals) {
    float val = Math.min((float)0.99, (float)Math.pow(triangleArea(xVals,yVals)*(float)(3.0/Math.pow(WIDTH/(N-1),2)),4));
    return new Color(val,val,val);
  }

  // compute area of triangle using Heron's formula
  public double triangleArea(double[] xVals, double[] yVals) {
    double a = Math.sqrt( Math.pow(xVals[1]-xVals[0],2) + Math.pow(yVals[1]-yVals[0],2) );
    double b = Math.sqrt( Math.pow(xVals[2]-xVals[1],2) + Math.pow(yVals[2]-yVals[1],2) );
    double c = Math.sqrt( Math.pow(xVals[0]-xVals[2],2) + Math.pow(yVals[0]-yVals[2],2) );
    double s = (a+b+c)/2;
    return Math.sqrt( s * Math.abs(s-a) * Math.abs(s-b) * Math.abs(s-c) ); 
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
