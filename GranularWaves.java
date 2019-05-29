import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GranularWaves implements ActionListener {
  JFrame jfrm;
  GranularWavesGraphics gwg;

  GranularWaves() {
    jfrm = new JFrame("GranularWaves");
    gwg = new GranularWavesGraphics();

    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setBackground(Color.white);
    jfrm.add(gwg, BorderLayout.CENTER);
    jfrm.pack();
    jfrm.setResizable(false);
    jfrm.setVisible(true);

    int delay = 50;
    Timer timer = new Timer(delay,this);
    while(true) {
      timer.start();
    }
  }

  public void actionPerformed(ActionEvent e) {
    gwg.repaint();
  }

  public static void main(String args[]) {
    new GranularWaves();
  }
}
