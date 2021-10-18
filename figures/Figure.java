package figures;
import ivisible.IVisible;

import java.awt.*;
import java.awt.Graphics;

public abstract class Figure implements IVisible
{
	public int x, y;
    	public int w, h;
    	public Color BorderColor;
    	public Color BckgColor;
    	
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
}
