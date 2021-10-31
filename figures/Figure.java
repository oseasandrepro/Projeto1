package figures;
import ivisible.IVisible;

import java.io.Serializable;
import java.awt.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Figure implements IVisible, Serializable
{
	public int x, y;
    	public int w, h;
    	public Color BorderColor;
    	public Color BckgColor;
    	protected Rectangle2D.Double[] pointsIfSelected = { new Rectangle2D.Double(50, 50, 8, 8), 
                                   			  new Rectangle2D.Double(150, 100, 8, 8),
                                   			  new Rectangle2D.Double(100, 75, 8, 8) };
    	
    	public Figure (int x, int y, int w, int h, Color BorderColor, Color BckgColor)
    	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.BorderColor = BorderColor;
		this.BckgColor = BckgColor;
    	}
    	
    	public boolean clicked (int x, int y) 
    	{
        	return ( x>=this.x && x<=this.x+this.w && y>=this.y && y<=this.y+h );
    	}
    	
    	public Rectangle2D.Double[] GetPointsOfSelection()
    	{
    		return pointsIfSelected;
    	}
}
