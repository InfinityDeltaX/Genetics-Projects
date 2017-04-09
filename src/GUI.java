import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class GUI {
	
	public static void GUI(){
		JFrame f = new JFrame("Genetics!");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		JApplet applet = new MyApplet();
		f.getContentPane().add("Center", applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(1000, 1000));
		f.setVisible(true);
	}
	static class MyApplet extends JApplet {
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			
	        Color fg3D = Color.lightGray;
	        
	        g2.setPaint(fg3D);
	        g2.draw3DRect(0, 0, 100, 100, true);
	        g2.draw3DRect(3, 3, 40, 40, false);
	        g2.setPaint(Color.white);
		}
	}
}


