package button_;

import ivisible.IVisible;
import figures.Figure;
import java.awt.*;

public class Button_ implements IVisible 
{
    public  int    id;
    public Figure fig;
    
    public Button_ (int id, Figure fig) 
    {
        this.id = id;
        this.fig = fig;
        this.fig.w = this.fig.h = 32;
    }

    public boolean clicked (int x, int y) 
    {
        return ( x>=fig.x && x<=fig.x+fig.w && y>=fig.y && y<=fig.y+fig.h );
    }

    public void paint (Graphics g, boolean focused) 
    {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(focused ? Color.GRAY : Color.LIGHT_GRAY);
        g2d.fillRect(fig.x-8, fig.y-4, 48, 48);
	
        this.fig.paint(g, false);
    }
}
