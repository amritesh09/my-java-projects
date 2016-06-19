package quadtree;

import java.awt.Rectangle;
import java.util.ArrayList;

public class QTNode {
	Rectangle bounds;
	ArrayList<Planet> planets;// only if leaf
	QTNode[] nodes;
	int planetsPerLeaf;
	int numOfChild =0;
	
	public QTNode(int planetsPerLeaf, Rectangle b) {
		this.bounds =b;
		this.planetsPerLeaf = planetsPerLeaf;
		nodes = new QTNode[4];
		planets = new ArrayList<>();
	}
	
	public void addPlanet(Planet p){
		if(this.planets.size() == this.planetsPerLeaf){
			//split and add to children
			
		} else{
			this.planets.add(p);
		}
	}
}
