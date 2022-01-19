package shoot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
public class Hero extends shoot{	
	public Image image0,image1;
		
	public Hero(Panel panel) {
		this.panel=panel;
		
		this.x=(BaseFrame.frameWidth-this.width)/2;
		this.y=BaseFrame.frameWidth-this.height/2;
		
	}
	public void moveToMouse(int mx,int my) {
		x=mx-width/3;
		y=my-2*height/3;
	}
	//»æÖÆ·É»ú
	public void drawhero(Graphics g){
		 image0=Toolkit.getDefaultToolkit().getImage("image/hero0.png");
		g.drawImage(this.image0, x,y,width-10,height+10,null);
		
	}
	public void drawhero1(Graphics g){
		 image1=Toolkit.getDefaultToolkit().getImage("image/hero1.png");
		g.drawImage(this.image1, x,y,width,height,null);
		
	}
	
	
		
}
	

