import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.awt.Point;

import figures.*;
import button_.*;
import toolbox.*;

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
	int SIZE = 8;
	int defaultH = 80;
	int defaultW = 80;
	
	int windowH = 500;
	int windowW = 500;
	int pos = -1;
	int buttonId = -1;
	
	Point2D[] lastPoints = new Point2D[3];
	Rectangle2D.Double[] points = {	new Rectangle2D.Double(50, 50, SIZE, SIZE), 
                                   			new Rectangle2D.Double(150, 100, SIZE, SIZE),
                                   			new Rectangle2D.Double(100, 75, SIZE, SIZE) };
	
	Point pointOfmouse = null;
	Figure selectedFigure = null;
	Figure auxFigure = null;
	Point prevPt = null;
	
	ArrayList<Button_> buttons = new ArrayList<Button_>();
	Toolbox mainToolbox;
	
	ArrayList<Figure> fg = new ArrayList<Figure>();
		
	ListFrame() 
	{
		buttons.add( new Button_(1, new Rect(1, 2, 3, 4, Color.BLACK,  Color.BLACK)) );
		buttons.add( new Button_(2, new Roundrect(1, 2, 3, 4, Color.BLACK,  Color.BLACK)) );
		buttons.add( new Button_(3, new Ellipse(1, 2, 3, 4, Color.BLACK,  Color.BLACK)) );
		buttons.add( new Button_(4, new Triangle(1, 2, 3, -1, Color.BLACK,  Color.BLACK)) );
					      
		mainToolbox = new Toolbox(10, 50, buttons);
		
		try
		{
			FileInputStream f = new FileInputStream("proj.bin");
			ObjectInputStream o = new ObjectInputStream(f);
			
			this.fg = (ArrayList<Figure>)o.readObject();
			
			o.close();
			
		}catch(Exception x)
		{
			System.out.println("ERRO! ao abrir arquivo");
			System.out.println(x.getMessage());
			
		}
  		
		this.addWindowListener 
		(
		    new WindowAdapter() 
		    {
		        public void windowClosing (WindowEvent e) 
		        {
		        	try
		        	{
		        		FileOutputStream f = new FileOutputStream("proj.bin");
		        		ObjectOutputStream o = new ObjectOutputStream(f);
		        		o.writeObject(fg);
		        		o.flush();
		        		o.close();
		        		
		        	}
		        	catch(Exception x)
		        	{
		        		System.out.println("ERRO! ao salvar arquivo");
		        		System.out.println(x.getMessage());
		        	}
		            System.exit(0);
		        }
		    }
		);
		
		repaint();
		
		this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt)
				{
				int x = (int)pointOfmouse.getX();
				int y = (int)pointOfmouse.getY();
				
				if( evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R')
				{ 
					fg.add( new Rect(x, y, defaultW, defaultH, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E')
				{
					fg.add( new Ellipse(x, y, defaultW, defaultH, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( evt.getKeyChar() == 't' || evt.getKeyChar() == 'T' )
				{
					fg.add(new Triangle(x, y, defaultW, -1, Color.BLACK, Color.WHITE) );
					repaint();
				}else if( evt.getKeyChar() == 'w' || evt.getKeyChar() == 'W')
				{
					fg.add( new Roundrect(x, y, defaultW, defaultH, Color.BLACK, Color.WHITE) );
					repaint();
				}
				else if( (evt.getKeyCode() == KeyEvent.VK_DELETE) ||  
					  evt.getKeyChar() == 'x' || evt.getKeyChar() == 'X')
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
			 	pointOfmouse = evt.getPoint();
			 	for( Button_ bt: buttons){
			 		if( bt.clicked( (int)pointOfmouse.getX(), (int)pointOfmouse.getY() ) )
			 		{
			 			buttonId = bt.id;
			 			repaint();
			 			return;
			 		}
			 	}
			 	
			 	if( buttonId != -1)
			 	{
			 		int x = (int)pointOfmouse.getX();
					int y = (int)pointOfmouse.getY();
			 		if(buttonId == 1)
			 			fg.add( new Rect(x, y, defaultW, defaultH, Color.BLACK, Color.WHITE) );
			 		else if( buttonId == 2)
			 			fg.add( new Roundrect(x, y, defaultW, defaultH, Color.BLACK, Color.WHITE) );
			 		else if( buttonId == 3)
			 			fg.add( new Ellipse(x, y, defaultW, defaultH, Color.BLACK, Color.WHITE) );
			 		else if(buttonId == 4)
			 			fg.add(new Triangle(x, y, defaultW, -1, Color.BLACK, Color.WHITE) );
			 			
			 		buttonId = -1;
			 		repaint();
			 		return;
			 	}
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
			 		
				 	if(  fig.clicked( (int)prevPt.getX(), (int)prevPt.getY() ) )
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
            			if( fig.clicked((int)prevPt.getX(), (int)prevPt.getY()) ){
            			
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
		
		mainToolbox.Show(g, buttonId);

		for( Figure fig: this.fg)
		{
			fig.paint(g, fig.equals(selectedFigure) );
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
		
		points = selectedFigure.GetPointsOfSelection();
	
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < points.length; i++) 
      			g2d.fill(points[i]);
	}
}
