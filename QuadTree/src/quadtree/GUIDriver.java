package quadtree;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import com.roots.map.MapPanel;

public class GUIDriver extends Frame {
	static QuadTree tree;
	static Graphics g;
	static int flag =0;
	
   public GUIDriver(){
      super("Map");
      prepareGUI();
   }

   public static void main(String[] args){
	   GUIDriver  gui = new GUIDriver();  
      gui.setVisible(true);
      makePOV(gui, 20, 500);
   }

   private void prepareGUI(){
      setSize(500,500);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      }); 
      JPanel panel = new JPanel();
		MapPanel mp = new MapPanel();
		mp.removeAll();
		mp.setBounds(0, 0, 500, 500);
//		panel.add(mp);
		this.add(panel);
		this.setVisible(true);   
		SpaceSimulation ss = new SpaceSimulation();
		 tree = new QuadTree(10000, 10000,2, 10);
		ss.add(tree,2000,2000,10);
		ss.add(tree,4100,2300,10);
		ss.add(tree,6600,2400,10);
		ss.add(tree,6100,5890,10);
		ss.add(tree,8100,7890,10);
		ss.add(tree,2500,6000,10);
		// tree has all planets
	
   }    

   @Override
   public void paint(Graphics g) {
	   if(flag==1) {
		   g.setColor(Color.RED);
		   g.drawString("Collision detected", 200, 20);System.out.println("draw");
	   }
      g.setColor(Color.BLACK);
      Font font = new Font("Serif", Font.PLAIN, 24);
      g.setFont(font);
//      
//      g.setColor (Color.red);
      tree.draw(g, 10);
//      g.fillOval(10,10,100,100);
   }
   private void moveRight() {
		// TODO Auto-generated method stub
		
	}
   private void moveLeft() {
		// TODO Auto-generated method stub
		
	}
   private void moveDown() {
		// TODO Auto-generated method stub
		
	}
   private void moveUp() {
		// TODO Auto-generated method stub
		
	}
   public static  void makePOV(GUIDriver s, int scale, int dimen) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JFrame f = new JFrame();
	    f.setSize(300, 300);
//	    f.setLocation(screenSize.width - 500, 0);
	    f.setTitle("POV Panel");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    f.setUndecorated(true);
	    POVPanel povP = new POVPanel(s, 20, dimen);
	    povP.setLayout(new BorderLayout());
	    povP.setFocusable(true);

	    povP.addKeyListener(new KeyListener() {

	        @Override
	        public void keyPressed(KeyEvent arg0) {
	            final int i = arg0.getKeyCode();
	            Thread th = new Thread() {
	                public void run() {
	                    if (i == 39) {
	                        this.moveRight();
	                        flag =0;
	                        collision(f,tree.root);
	                    } else if (i == 37) {
	                    	this.moveLeft();
	                    	flag =0;
	                    	collision(f,tree.root);
	                    } else if (i == 40) {
	                    	this.moveDown();
	                    	flag =0;
	                    	collision(f,tree.root);
	                    } else if (i == 38) {
	                    	this.moveUp();
	                    	flag =0;
	                    	collision(f,tree.root);
	                    }
	                    try {
	                        Thread.sleep(500);
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }

	                }

	                private void moveRight() {
						f.setLocation((int)f.getLocationOnScreen().getX()+5,(int)f.getLocationOnScreen().getY());
						
					}
	                private void moveLeft() {
						f.setLocation((int)f.getLocationOnScreen().getX()-5,(int)f.getLocationOnScreen().getY());
						
					}
	                private void moveDown() {
						f.setLocation((int)f.getLocationOnScreen().getX(),(int)f.getLocationOnScreen().getY()+5);
						
					}
	                private void moveUp() {
						f.setLocation((int)f.getLocationOnScreen().getX(),(int)f.getLocationOnScreen().getY()-5);
						
					}

					
	            };
	            th.start();
	        }

	        @Override
	        public void keyReleased(KeyEvent arg0) {

	        }

	        @Override
	        public void keyTyped(KeyEvent arg0) {

	        }

	    });

	    f.add(povP);
	    f.setVisible(true);
	    Insets i = f.getInsets();
	    f.setSize(dimen / 20 + (i.left + i.right) + 2, dimen / 20
	            + (i.top + i.bottom) + 2);

	    Thread th = new Thread() {
	        public void run() {
	            while (true) {
	                povP.repaint();
	            }

	        }
	    };
	    th.start();

	}
   public static void collision(JFrame f, QTNode n){
	   int x = (int)f.getLocation().getX()*20;
	   int y = (int)f.getLocation().getY()*20;
	   Rectangle fRectangle = new Rectangle(x, y, f.getWidth(), f.getHeight());
	   // check for collision
	   if(n==null){
			return;
		}
	   if(n.planets.size()>0){
			for(int i=0;i<n.planets.size();i++){
				Planet p = n.planets.get(i);
//				System.out.println(p.circe.getBounds().toString()+"here"+fRectangle.getBounds().toString());
				if(p.circe.intersects(fRectangle.getBounds())) {
					System.out.println("COLLISION DETECTED");
					flag=1;
					return;
				}
			}
		}
		// draw even if no children
		if(n.nodes!=null){
			for(QTNode c:n.nodes){
				collision(f,c);
			}
		}
	   
   }
}