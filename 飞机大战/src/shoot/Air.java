package shoot;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Air extends shoot{
	public Image image1;
	List<Air> airs=new ArrayList<Air>();
	//µ–Ω¢Œª÷√
	public int indext;
	
	public Air() {
		Panel panel=new Panel();
		this.panel=panel;
		Random rd=new Random();
		
		this.x=rd.nextInt(500-width);
		this.y=-50;
		indext=rd.nextInt(3)+1;
			image1=Toolkit.getDefaultToolkit().getImage("image/enemy"+indext+".png");
			
	}
	
	public void move1() {
		this.y+=15;
	}
	public void move2() {
		this.y+=20;
	}
	public void move3() {
		this.y+=25;
	}

	public boolean shootBy(Fire f) {

		boolean hit= this.x<=f.x+f.width&&this.x>=f.x-width&&this.y<=f.y+f.height&&this.y>=f.y-height;
		return hit;
		//return false;
	}
	public boolean shootBy(Hero h) {

		boolean hit= this.x<=h.x+h.width&&this.x>=h.x-width&&this.y<=h.y+h.height&&this.y>=h.y-height;
		return hit;
		//return false;
	}
}
