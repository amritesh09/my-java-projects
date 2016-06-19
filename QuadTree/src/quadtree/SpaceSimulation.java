package quadtree;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.roots.map.MapPanel;

public class SpaceSimulation {
	public static void main(String[] s){
		QuadTree tree = new QuadTree(10000, 10000,2, 10);
		tree.addPlanet(new Planet(100, 100, 10));
		tree.addPlanet(new Planet(1000, 2600, 130));
		tree.addPlanet(new Planet(1200, 2200, 1));
		tree.addPlanet(new Planet(500, 2300, 20));
		tree.addPlanet(new Planet(2340, 860, 10));
		tree.addPlanet(new Planet(1300, 2320, 130));
		tree.addPlanet(new Planet(3400, 5600, 1));
		tree.addPlanet(new Planet(5000, 2340, 20));
//		System.out.println("Planets added successfully");
//		System.out.println("root.size= "+tree.root.planets.size());
//		System.out.println("root children size:");
//		for(int i=0;i<4;i++){
//			System.out.println("Child number "+(i+1)+" "+tree.root.nodes[i].planets.size());
//			for(int j=0;j<4;j++){
//				if(tree.root.nodes[i].nodes[j]!=null){
//				System.out.println("Child child number "+(i*4+j)+" "+tree.root.nodes[i].nodes[j].planets.size());
//				for(int k=0;k<4;k++){
//					if(tree.root.nodes[i].nodes[j].nodes[k]!=null)
//						System.out.println("Child child child number "+(i*4+j*4+k)+" "+tree.root.nodes[i].nodes[j].nodes[k].planets.size());
//				}
//			}}
//		}
		add(tree,500,500,10);
		add(tree,1100,1300,10);
		add(tree,6600,2400,10);
		add(tree,9100,7890,10);
		add(tree,500,5000,10);
//		for(int i=0;i<4;i++){
//			System.out.println("Child number "+(i+1)+" "+tree.root.nodes[i].planets.size());
//			for(int j=0;j<4;j++){
//				if(tree.root.nodes[i].nodes[j]!=null){
//				System.out.println("Child child number "+(i*4+j)+" "+tree.root.nodes[i].nodes[j].planets.size());
//				for(int k=0;k<4;k++){
//					if(tree.root.nodes[i].nodes[j].nodes[k]!=null)
//						System.out.println("Child child child number "+(i*4+j*4+k)+" "+tree.root.nodes[i].nodes[j].nodes[k].planets.size());
//				}
//			}}
//		}
		
	   
	}
	static void add(QuadTree q, int x1, int y1,int scale){
		double a = Math.random();
		double b = Math.random();
		int r =1000;
		double pi = Math.PI;
		for(int i=0;i<2000;i++){
			a = Math.random();
			b = Math.random();
			double p1 = b*r*Math.cos(2*pi*a/b);
			double p2 = b*r*Math.sin(2*pi*a/b);
			long x= Math.round(p1)+x1;
			long y= Math.round(p2)+y1;
//			System.out.println("p1= "+x+" p2= "+y);
			Planet p = new Planet((int)x,(int) y, 1);
			q.addPlanet(p);
		}
	}
}
