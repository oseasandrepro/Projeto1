package toolbox;

import java.awt.*;
import java.util.ArrayList;

import figures.Figure;
import button_.Button_;

public class Toolbox
{
	private int x,y;
	public ArrayList<Button_> buttons = new ArrayList<Button_>();
	
	public Toolbox (int x, int y, ArrayList<Button_> bts)
	{
		this.x = x;
		this.y = y;
		this.buttons = bts;
	}
	
	public void Show(Graphics g, int selectedId)
	{
		int SIZE = 48;
		Graphics2D g2d = (Graphics2D) g;
		
		int numButtons = buttons.size();
		int w = SIZE;
		int h = numButtons*SIZE;
		
		BasicStroke bs1 = new BasicStroke(6, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		g2d.setStroke(bs1);
        
        	g2d.setColor( Color.BLACK);
        	g2d.drawRect(this.x, this.y, w, h);
        
        	g2d.setColor(Color.LIGHT_GRAY);
        	g2d.fillRect( this.x,  this.y,  w,  h);
        	
        	int x = this.x+8;
        	int y = this.y+8;
        	boolean isSelected = false;
        	for( Button_ bt: this.buttons)
        	{
        		bt.fig.x = x;		
        		bt.fig.y = y;
        		y = y + 40;
        		
			bt.paint(g, bt.id == selectedId);
        	}
	}
}
