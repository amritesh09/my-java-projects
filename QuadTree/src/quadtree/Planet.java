package quadtree;
import java.awt.geom.*;

import com.roots.map.MapPanel;


public class Planet {
	Ellipse2D circe;
	public Planet(int x, int y, int r){
		this.circe = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
		
	}
	void draw(MapPanel mp, int scale){
		
	}
}
