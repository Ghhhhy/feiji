package shoot;

import java.awt.Image;
import java.awt.Toolkit;

public class Fire extends shoot{
	public Panel panel;
	public int width=15;
	public int height=15;
	public int x;
	public int y;
	public Hero hero;
	public Image image3;
	
	public Fire(int hx,int hy) {
		//子弹的位置在Hero上
		
		x=hx+22;
		y=hy;
		image3=Toolkit.getDefaultToolkit().getImage("image/fire.png");
	}
	public void move() {
		y-=10;
	}
}
