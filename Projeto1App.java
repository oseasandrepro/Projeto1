import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class Projeto1App
{
	public static void main(String[] args)
	{
		ListFrame frame = new ListFrame();
		frame.setVisible(true);
	}	
}

class ListFrame extends JFrame
{
	Point pointOfmouse = null;
	Figure selectedFigure = null;
	Figure auxFigure = null;
	Point prevPt = null;
	
	int defaultH = 80;
	int defaultW = 80;
	
	int windowH = 500;
	int windowW = 500;
	
	
	ArrayList<Figure> fg = new ArrayList<Figure>();
	Random rand = new Random();
	
	ListFrame() 
	{
  		
		this.addWindowListener (
		    new WindowAdapter() 
		    {
		        public void windowClosing (WindowEvent e) {
		            System.exit(0);
		        }
		    }
		);
		
		this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt)
				{
				int x = (int)pointOfmouse.getX();
				int y = (int)pointOfmouse.getY();
				int w = defaultH;
				int h = defaultW;
				if( evt.getKeyChar() == 'r')
				{ 
					fg.add( new Rect(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( evt.getKeyChar() == 'e')
				{
					fg.add( new Ellipse(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( evt.getKeyChar() == 't' )
				{
					fg.add(new Triangle(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}else if( evt.getKeyChar() == 'w')
				{
					fg.add( new Roundrect(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( (evt.getKeyCode() == KeyEvent.VK_DELETE) ||  evt.getKeyChar() == 'x')
				{
					if( selectedFigure != null){
						fg.remove(selectedFigure);
						auxFigure = null;
						selectedFigure = null;
						repaint();
					}
				}
			}
		}
			
		);
		
		this.addMouseListener(
		new MouseAdapter(){
			 public void mousePressed(MouseEvent evt) 
			 {
			  prevPt = evt.getPoint();
			  for( Figure fig: fg)
			  {
				if( (prevPt.getX()>=fig.x && prevPt.getX()<=fig.x+fig.w) && (prevPt.getY()>=fig.y && prevPt.getY()<=fig.y+fig.h) ){
					selectedFigure = fig;
					break;
				}
				else
				{
					selectedFigure = null;
					auxFigure = null;
				}
			  }
			  repaint();
			 }
			 
			 public void mouseReleased(MouseEvent evt)
			 {
					
			 }
		}
		);
		
		this.addMouseMotionListener(
			new MouseMotionAdapter(){
				public void mouseDragged(MouseEvent evt){
   					if( selectedFigure!=null)
   					{
			   		Point currentPt = evt.getPoint();
			   		selectedFigure.x = (int)currentPt.getX();
			   		selectedFigure.y = (int)currentPt.getY();
			   		
			   		prevPt = currentPt;
			   		repaint();
			   		}
			   	}  
			  
			  public void mouseMoved(MouseEvent evt)
			  {
			  pointOfmouse = evt.getPoint();
			  prevPt = pointOfmouse;
			  for( Figure fig: fg)
			  {
				if( (prevPt.getX()>=fig.x && prevPt.getX()<=fig.x+fig.w) && (prevPt.getY()>=fig.y && prevPt.getY()<=fig.y+fig.h) ){
					auxFigure = fig;
					repaint();
					break;
			    }else{
			    	auxFigure = null;
			    }
			  }
			  
			  repaint();
			}
		   }
		);
		
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Projeto1App");
        	this.setSize(500, 500);
      
   }

	public void paint(Graphics g)
	{
		super.paint(g);
		for( Figure fig: this.fg)
		{
			fig.paint(g);
		}
		if( auxFigure != null ){
			rectFormouseOverFigure(g);
		}
		if( selectedFigure != null){
			rectForselectedFigure(g);
		}
	}
	
	public void rectFormouseOverFigure(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		g2d.setStroke(bs1);
	
		g2d.setColor(Color.RED);
		
		g2d.drawRect(auxFigure.x-5,auxFigure.y-5, auxFigure.w+10,auxFigure.h+10);
	}
	
	public void rectForselectedFigure(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		g2d.setStroke(bs1);
	
		g2d.setColor(Color.BLUE);
		
		g2d.drawRect(selectedFigure.x-5,selectedFigure.y-5, selectedFigure.w+10,selectedFigure.h+10);
	}
}
