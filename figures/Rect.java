package figures;
import java.awt.*;

public class Rect extends Figure 
{

    public Rect (int x, int y, int w, int h, Color BorderColor, Color BckgColor) 
    {
       super(x, y, w, h, BorderColor, BckgColor);
    }
    
    public void paint (Graphics g, boolean focused) 
    {                           
        Graphics2D g2d = (Graphics2D) g;
        
        
        //consertar/ajustar bordas
        BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
	g2d.setStroke(bs1);
        
        g2d.setColor( this.BorderColor);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        
        g2d.setColor(this.BckgColor);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        
        
        if( focused )
	{
		int SIZE = 8;
		int x,y; int w,h;
		
		bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		g2d.setStroke(bs1);
		
		
		
		x = this.x; 
		y = this.y;
		w = this.w;
		h = this.h;
	
		this.pointsIfSelected[0].x = (double)x-SIZE; 
		this.pointsIfSelected[0].y = (double)y-SIZE;
		
		this.pointsIfSelected[1].x = (double)x+w;
		this.pointsIfSelected[1].y = (double)y+h; 
		
		this.pointsIfSelected[2].x = (double)((x+w)+(x-SIZE))/2;
		this.pointsIfSelected[2].y = (double)((y+h)+y-SIZE)/2;
	}
        
    }
}
