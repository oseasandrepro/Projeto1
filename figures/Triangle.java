package figures;

import java.awt.*;

public class Triangle extends Figure
{
	public Triangle(int x,int y, int w,int h, Color BorderColor, Color BckgColor)
	{
		super(x, y, w, h, BorderColor, BckgColor);
	}
	
	public void paint (Graphics g, boolean focused) 
	{
		Graphics2D g2d = (Graphics2D)g;
		
		
		//consertar/ajustar bordas
		BasicStroke bs1 = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		
		int xs[] = {this.x, this.x-(int)(w/2.0), this.x+(int)(w/2.0)};
		int ys[] = {this.y, this.y+w, this.y+w};
		
		g2d.setColor(this.BorderColor);
		g2d.drawPolygon(xs,ys,3);
		
		g2d.setColor(this.BckgColor);
		g2d.fillPolygon(xs,ys,3);
		
		if( focused )
		{
			int x,y; int w,h;
			int SIZE = 8;
			
			bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
			g2d.setStroke(bs1);
			
			x = this.x; 
			y = this.y;
			w = h = this.w;
			
			this.pointsIfSelected[0].x = (double)x-(w/2)-SIZE;
			this.pointsIfSelected[0].y = (double)y+w;
		
			this.pointsIfSelected[1].x = (double)x+(w/2);
			this.pointsIfSelected[1].y = (double)y+h;
			
			//Calculando o baricentro do triangulo
			double Gx = (x + (x-(w/2.0)) + (x+(w/2.0))-SIZE )/3.0 ;
			double Gy = (y + (y+w) + (y+h) -SIZE )/3.0;
		
			this.pointsIfSelected[2].x = Gx;
			this.pointsIfSelected[2].y = Gy;
		}
        }
        
        @Override
        public boolean clicked (int x, int y) 
        {
        	return ( x>=(this.x-(this.w/2)) && x<=(this.x+(this.w/2)) && y>=this.y && y<=this.y+w );
    	}
        
}
