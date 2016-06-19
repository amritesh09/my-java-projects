package quadtree;

import java.awt.*;
import java.util.*;

public class QuadTree {
	QTNode root;
	int width;
	int height;
	int scale;
	public QuadTree(int w, int h, int planetsPerLeaf,int scale){
//		w/=scale;
//		h/=scale;
		this.scale = scale;
		Rectangle r =  new Rectangle(0, 0, w,h);
		root = new QTNode(planetsPerLeaf,r);
	}
	
	/*
	 * Splits the node into 4 subnodes
	 */
	 private void split(QTNode root) {
	   int subWidth = (int)(root.bounds.getWidth() / 2);
	   int subHeight = (int)(root.bounds.getHeight() / 2);
	   int x = (int)root.bounds.getX();
	   int y = (int)root.bounds.getY();
	 
	   root.nodes[0] = new QTNode(4, new Rectangle(x + subWidth, y, subWidth, subHeight));
	   root.nodes[1] = new QTNode(4, new Rectangle(x, y, subWidth, subHeight));
	   root.nodes[2] = new QTNode(4, new Rectangle(x, y + subHeight, subWidth, subHeight));
	   root.nodes[3] = new QTNode(4, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	 }
	 
	 /*
	  * Determine which node the object belongs to. -1 means
	  * object cannot completely fit within a child node and is part
	  * of the parent node
	  */
	  private int getIndex(QTNode root,Planet planet) {
		  Rectangle p = new Rectangle(planet.circe.getBounds());
	    int index = -1;
	    double verticalMidpoint = root.bounds.getX() + (root.bounds.getWidth() / 2);
	    double horizontalMidpoint = root.bounds.getY() + (root.bounds.getHeight() / 2);
	  
	    // Object can completely fit within the top quadrants
	    boolean topQuadrant = (p.getY() < horizontalMidpoint && p.getY() + p.getHeight() < horizontalMidpoint);
	    // Object can completely fit within the bottom quadrants
	    boolean bottomQuadrant = (p.getY() > horizontalMidpoint);
	  
	    // Object can completely fit within the left quadrants
	    if (p.getX() < verticalMidpoint && p.getX() + p.getWidth() < verticalMidpoint) {
	       if (topQuadrant) {
	         index = 1;
	       }
	       else if (bottomQuadrant) {
	         index = 2;
	       }
	     }
	     // Object can completely fit within the right quadrants
	     else if (p.getX() > verticalMidpoint) {
	      if (topQuadrant) {
	        index = 0;
	      }
	      else if (bottomQuadrant) {
	        index = 3;
	      }
	    }
	  
	    return index;
	  }
	  
	  /*
	   * Insert the object into the quadtree. If the node
	   * exceeds the capacity, it will split and add all
	   * planets to their corresponding nodes.
	   */
	   public void insert(QTNode root,Planet p) {
//		   QTNode root = qt.root;
		   if(root==null){
			   root = new QTNode(this.root.planetsPerLeaf, null);
		   }
	     if (root.nodes[0] != null) {
	       int index = getIndex(root,p);
	   
	       if (index != -1) {
	    	   insert(root.nodes[index],p);
	   
	         return;
	       }
	     }
	     root.planets.add(p);
//	     System.out.println(root.planets.size());
	     if (root.planets.size() > root.planetsPerLeaf) {
//	    	 System.out.println("size exceeded");
	        if (root.nodes[0] == null) { 
	           split(root); 
//	           System.out.println("splitted");
	        }
	   
	       int i = 0;
	       while (i < root.planets.size()) {
	         int index = getIndex(root,root.planets.get(i));
	         if (index != -1) {
	        	 insert(root.nodes[index],root.planets.remove(i));	// recursively insert in child
//	        	 System.out.println("added at index "+index+" root.planet size "+root.planets.size());
//	        	 System.out.println(this.root.nodes[1]);
	         }
	         else {
	           i++;
	         }
	       }
	     }
	    
	   }
	public void draw(Graphics g, int scale){
		drawNode(root,g,scale);
	}
	public void drawNode(QTNode n, Graphics g, int scale){
//		System.out.println("in draw node "+n);
		if(n==null){
			return;
		}
		if(n.planets.size()>0){
			g.setColor(Color.BLACK);
			for(int i=0;i<n.planets.size();i++){
				Planet p = n.planets.get(i);
				g.fillOval((int)p.circe.getX()/20,(int)p.circe.getY()/20,(int)p.circe.getHeight(),(int)p.circe.getWidth());
//				System.out.println((int)p.circe.getX()/20+" "+(int)p.circe.getY()/20);
			}
			g.setColor(Color.BLUE);
			// draw if it has planets
			g.drawRect(n.bounds.x/20,n.bounds.y/20,n.bounds.width/20,n.bounds.height/20);
		}
		// draw even if no children
		g.drawRect(n.bounds.x/20,n.bounds.y/20,n.bounds.width/20,n.bounds.height/20);
		if(n.nodes!=null){
			for(QTNode c:n.nodes){
				drawNode(c,g,scale);
			}
		}
	}
	public void addPlanet(Planet p){
		insert(root,p);
//	     System.out.println(root.planets.size()+" after insert");

	}
	
	public ArrayList<Planet> findLocalPlanets(Rectangle r){
		
		
		return null;
	}
}
