package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.BasicStroke;

public class Triangle extends Figure
{
	public Triangle(int x,int y, int w,int h, Color BorderColor, Color BckgColor)
	{
		super(x, y, w, h, BorderColor, BckgColor);
	}
	
	public void paint (Graphics g) 
	{
		Graphics2D g2d = (Graphics2D)g;
		
		//consertar/ajustar bordas
		BasicStroke bs1 = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		
		int x[] = {this.x, this.x-(int)(w/2.0), this.x+(int)(w/2.0)};
		int y[] = {this.y, this.y+w, this.y+w};
		
		g2d.setColor(this.BorderColor);
		g2d.drawPolygon(x,y,3);
		
		g2d.setColor(this.BckgColor);
		g2d.fillPolygon(x,y,3);
        }
        
        @Override
        public boolean clicked (int x, int y) 
        {
        	return ( x>=(this.x-(this.w/2)) && x<=(this.x+(this.w/2)) && y>=this.y && y<=this.y+w );
    	}
        
}
