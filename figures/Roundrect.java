package figures;
import java.awt.*;

public class Roundrect extends Figure 
{
    public Roundrect (int x, int y, int w, int h, Color BorderColor, Color BckgColor) 
    {
       super(x, y, w, h, BorderColor, BckgColor);
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        
        //consertar/ajustar bordas
       	BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
	g2d.setStroke(bs1);
        
        g2d.setColor( this.BorderColor);
        g2d.drawRoundRect(this.x,this.y, this.w,this.h, 20, 20);
        
        g2d.setColor(this.BckgColor);
        g2d.fillRoundRect(this.x, this.y, this.w, this.h, 20, 20);
        
    }
}
