package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.BasicStroke;

public class Ellipse extends Figure
{
    public Ellipse (int x, int y, int w, int h, Color BorderColor, Color BckgColor)
    {
    	super(x, y, w, h, BorderColor, BckgColor);
    }

    public void paint (Graphics g) 
    {
        Graphics2D g2d = (Graphics2D)g;
        
        //consertar/ajustar bordas
        BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
	g2d.setStroke(bs1);
	
	g2d.setColor(this.BorderColor);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
	
	g2d.setColor(this.BckgColor);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
        
    }
}
