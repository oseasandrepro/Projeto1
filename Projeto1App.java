import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Point;

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

	Point2D[] lastPoints = new Point2D[3];	
	
	Point pointOfmouse = null;
	Figure selectedFigure = null;
	Figure auxFigure = null;
	Point prevPt = null;
	int pos = -1;
	
	int SIZE = 8;
	int defaultH = 80;
	int defaultW = 80;
	
	int windowH = 500;
	int windowW = 500;
	
	Rectangle2D.Double[] points = { new Rectangle2D.Double(50, 50,SIZE, SIZE), 
                                   new Rectangle2D.Double(150, 100,SIZE, SIZE),
                                   new Rectangle2D.Double(100, 75,SIZE, SIZE)};
	
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
				if( evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R')
				{ 
					fg.add( new Rect(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E')
				{
					fg.add( new Ellipse(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( evt.getKeyChar() == 't' || evt.getKeyChar() == 'T' )
				{
					fg.add(new Triangle(x, y, w, -1, Color.BLACK, Color.WHITE) );
					repaint();
				}else if( evt.getKeyChar() == 'w' || evt.getKeyChar() == 'W')
				{
					fg.add( new Roundrect(x, y, w, h, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( (evt.getKeyCode() == KeyEvent.VK_DELETE) ||  evt.getKeyChar() == 'x' || evt.getKeyChar() == 'X')
				{
					if( selectedFigure != null){
						fg.remove(selectedFigure);
						auxFigure = null;
						selectedFigure = null;
						repaint();
					}
				}
				else if( evt.getKeyChar() == 'f' || evt.getKeyChar() == 'F')
				{
					if( selectedFigure != null){
						Color newColor = JColorChooser.showDialog(null, "Choose a color", selectedFigure.BorderColor);
						selectedFigure.BorderColor = newColor;
						repaint();
					}
				}
				else if( evt.getKeyChar() == 'b' || evt.getKeyChar() == 'B')
				{
					if( selectedFigure != null){
						Color newColor = JColorChooser.showDialog(null, "Choose a color", selectedFigure.BorderColor);
						selectedFigure.BckgColor = newColor;
						repaint();
					}
				}
				else if (evt.getKeyCode() == KeyEvent.VK_UP) 
				{
					if( selectedFigure != null){
						selectedFigure.y -= 1;
						repaint();
					}
				}
				else if(evt.getKeyCode() == KeyEvent.VK_DOWN) 
				{
					if( selectedFigure != null ){
						selectedFigure.y += 1;
						repaint();
					}
				}
				else if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
				{
					if( selectedFigure != null){
						selectedFigure.x -= 1;
						repaint();
					}
				}
				else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
				{
					if( selectedFigure != null){
						selectedFigure.x += 1;
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
			 	boolean flag1 = false;
			 	boolean flag2 = false;
			 	for (int i = 0; i < points.length; i++) 
			 	{
					if (points[i].contains(prevPt)) 
					{
					  pos = i;
					  flag1 = true;
					  
					  //System.out.printf("point %d\n",pos);
					 
					  for(int j = 0; j < 3; j++){
					      lastPoints[j] = new Point2D.Double(points[j].getX(), points[j].getY());
					  }
					  
					  break;
					}
			      }
			      
			      ListIterator<Figure> li = fg.listIterator(fg.size());
			      Figure fig = null;
			  	while( li.hasPrevious() )
			 	{
			 		fig = li.previous();
			 		int h,w;
				 	w = fig.w;
				 	//Tratamento especial para o triangulo
				 	if(fig.h == -1)
				 		h = fig.w;
				 	else
				 		h = fig.h;
				 	/////////////////////////////////
				 	if( (prevPt.getX()>=fig.x && prevPt.getX()<=fig.x+w) && (prevPt.getY()>=fig.y && prevPt.getY()<=fig.y+h) )
				 	{
				 		flag2 = true;
						selectedFigure = fig;
						int index = fg.indexOf(selectedFigure);
						fg.remove(index);
						fg.add(selectedFigure);
						break;
					}
        		 	}
        		 	
        		 	if( !flag1 && !flag2){
        		 		 selectedFigure = null;
        		 	}
			  
			  repaint();
			 }
			 
			 public void mouseReleased(MouseEvent evt)
			 {
				pos = -1;	
			 }
		}
		);
		
		this.addMouseMotionListener(
			new MouseMotionAdapter(){
				public void mouseDragged(MouseEvent evt)
				{
					Point currentPt = evt.getPoint();
   					
			   		if (pos == -1)
        					return;
        				if( pos != 2)
        				{
        					
          					points[pos].x = currentPt.x; 
          					points[pos].y = currentPt.y;
          					
          					int otherEnd = (pos==1)?2:1;
          					
          					//System.out.printf("otherEnd %d\n",otherEnd);
          					
          					double newPoint2X = points[otherEnd].getX() + (points[pos].getX() - points[otherEnd].getX())/2;
          					double newPoint2Y = points[otherEnd].getY() + (points[pos].getY() - points[otherEnd].getY())/2;
          
          					points[2].x = newPoint2X; 
          					points[2].y = newPoint2Y;
          					
          					selectedFigure.w = (int)Math.abs(points[1].getCenterX()-points[0].getCenterX());
          					
				   		//Tratamento especial para o triangulo
				   		if( selectedFigure.h != -1)
				   			selectedFigure.h = (int)Math.abs(points[1].getCenterY()-points[0].getCenterY());
        				}
        				else
        				{
        					double deltaX = currentPt.x - lastPoints[2].getX();
        					double deltaY = currentPt.y - lastPoints[2].getY();
        					
          					
				   		 for(int j = 0; j < 3; j++){
				   		 	points[j].x = lastPoints[j].getX() + deltaX;
				   		 	points[j].y = lastPoints[j].getY() + deltaY;
				   		 }
				   		 
				   		//Tratamento especial para o triangulo
				   		if( selectedFigure.h == -1)
				   		{
				   			selectedFigure.x = (int)points[0].getCenterX()+(selectedFigure.w/2);
				   			selectedFigure.y = (int)points[0].getCenterY()-selectedFigure.w-SIZE;
				   		}
				   		else
				   		{
				   			selectedFigure.x = (int)points[0].getCenterX();
				   			selectedFigure.y = (int)points[0].getCenterY();
				   		}
				   		
        				}
        				
			   		repaint();
			   	
			   }  
			  
			  public void mouseMoved(MouseEvent evt)
			  {
			  pointOfmouse = evt.getPoint();
			  prevPt = pointOfmouse;
			  
			  ListIterator<Figure> li = fg.listIterator(fg.size());
			  Figure fig = null;
			  
			 while( li.hasPrevious() )
			 {
			 	fig = li.previous();
			 	int h,w;
			 	w = fig.w;
			 	//Tratamento especial para o triangulo
			 	if(fig.h == -1)
			 		h = fig.w;
			 	else
			 		h = fig.h;
			 	/////////////////////////////////	
            			if( (prevPt.getX()>=fig.x && prevPt.getX()<=fig.x+w) && (prevPt.getY()>=fig.y && prevPt.getY()<=fig.y+h) ){
					auxFigure = fig;
					repaint();
					break;
			    	}
			    	else
			    	{
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
		
		//Tratamento especial para o triangulo
		if( auxFigure.h == -1 )
		{
			int w = auxFigure.w;
			
			g2d.drawRect(auxFigure.x-(w/2)-5, auxFigure.y-5, auxFigure.w+10, auxFigure.w+10);
		}
		else
			g2d.drawRect(auxFigure.x-5,auxFigure.y-5, auxFigure.w+10,auxFigure.h+10);
		
		
	}
	
	public void rectForselectedFigure(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		g2d.setStroke(bs1);
		
		int x,y; int w,h;
		
		x = selectedFigure.x; y = selectedFigure.y;
		w = selectedFigure.w;
		
		//Tratamento especial para o triangulo
		if(selectedFigure.h == -1 )
			h = selectedFigure.w;
		else
			h = selectedFigure.h;
		
		if( selectedFigure.h == -1 )
		{
		
			points[0].x = (double)x-(w/2)-SIZE; points[0].y = (double)y+w;
			points[1].x = (double)x+(w/2); points[1].y = (double)y+h; 
			
			//Calculando o baricentro do triangulo
			double Gx = (x + (x-(w/2.0)) + (x+(w/2.0))-SIZE )/3.0 ;
			double Gy = (y + (y+w) + (y+h) -SIZE )/3.0;
			points[2].x = Gx; points[2].y = Gy;
		}
		else
		{
			points[0].x = (double)x-SIZE; points[0].y = (double)y-SIZE;
			points[1].x = (double)x+w; points[1].y = (double)y+h; 
			points[2].x = (double)((x+w)+(x-SIZE))/2; points[2].y = (double)((y+h)+y-SIZE)/2;
		}
	
		g2d.setColor(Color.BLUE);
		for (int i = 0; i < points.length; i++) {
      			g2d.fill(points[i]);
   		}
	}
}
