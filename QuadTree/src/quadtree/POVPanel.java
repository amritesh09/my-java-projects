package quadtree;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JPanel;

public class POVPanel extends JPanel {
	//and this is the POVPanel 
	GUIDriver s;
	int dimen;
	public POVPanel(GUIDriver s, int scale, int dimen) {
	    super();
	    this.s = s;
	    // this.scale = scale;
	    this.dimen = dimen;
	    this.setSize(200, 200);
	}


	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
//	    g.fillRect(0, 0, dimen / 20, dimen / 20);
	    g.fillOval(this.getWidth()/2-this.dimen/40, this.getWidth()/2-this.dimen/40, this.dimen/20, this.dimen/20);
	    
	}
}
