package shoot;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



//背景
public class Panel extends JPanel{
		public static boolean flag=true;
		public Image bgImage,bgImage1;
		public int timer=0;
		public int top=0;//图片原始的高度
		public DrawableThread drawableThread; 
		public BaseFrame frame;
		public Hero hero;
		
		List<Air> airs=new ArrayList<Air>();
		
		List<Fire> fires=new ArrayList<Fire>();
		
		public Panel()
		{
			
			this.bgImage=Toolkit.getDefaultToolkit().getImage("image/123.jpg");//导入背景图片
			this.bgImage1=Toolkit.getDefaultToolkit().getImage("image/gameover.png");
			this.hero=new Hero(this);
			
			
			this.drawableThread=new DrawableThread(this);//创建线程
			this.drawableThread.start();//启动图片
			
			MouseAdapter adapter=new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if(flag=true) {
						repaint();
					}
				}
				//监听鼠标移动，mouseCliked()监听鼠标单击，
				public void mouseMoved(MouseEvent e) {
					int mx=e.getX();
					int my=e.getY();
					hero.moveToMouse(mx,my);
					repaint();//重画
				}
			};
			
			//鼠标加入监听器
			addMouseListener(adapter);
			addMouseMotionListener(adapter);
			
			/*KeyAdapter key= new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					int keyCode=e.getKeyCode();
					
					if(keyCode==KeyEvent.VK_UP) {
						hero.y-=10;
					}
					else if(keyCode==KeyEvent.VK_DOWN) {
						hero.y+=10;
					}
					else if(keyCode==KeyEvent.VK_LEFT) {
						hero.x-=10;
					}
					else if(keyCode==KeyEvent.VK_RIGHT) {
						hero.x+=10;
					}//上下左右移动
					repaint();
				}
				
			};
			//加到窗体的面板里面
			frame.addKeyListener(key); */
		}
		public void action() {
			new Thread() {
				public void run() {
					while(true) {
						airenter();
						airmove();
						
						shoot();
						firemove();
						shootfire();
						herodie(); 
						try{
							this.currentThread().sleep(25);      //每一毫秒画一次
						}catch(InterruptedException e){
							e.printStackTrace();
						}
						
						repaint();
					}
				}

				
			}.start();
		}
		
		public void shootfire() {
				for(int i=0;i<fires.size();i++){
				Fire f=fires.get(i);
				//判断子弹是否击中敌人
				bang(f);
				}
		}
		public void herodie() {
			for(int i=0;i<airs.size();i++){
				Air e=airs.get(i);
				if(e.shootBy(hero)){
					airs.remove(e);
					flag = false;
				}
			}
			//判断子弹是否击中敌人
			
		}
		public void bang(Fire f) {
			for(int i=0;i<airs.size();i++){
				Air e=airs.get(i);
				if(e.shootBy(f)){
					airs.remove(e);
					fires.remove(f);
				}
			}
		}
		
		public void firemove(){
				for(int i=0;i<fires.size();i++){
				
				Fire f=fires.get(i);
				f.move();
				
				}
				
			
		}
		int findex=0;
		public void shoot(){
			findex++;
			if(findex>=10) {
			Fire f=new Fire(hero.x,hero.y);
			fires.add(f);
			findex=0;	
			}
		}

		public void airmove(){
			for(int i=0;i<airs.size();i++){
				Air e=airs.get(i);
				if(e.indext==1)
					e.move1();
				if(e.indext==2)
					e.move2();
				if(e.indext==3)
					e.move3();
			
			}
		}
		int index=0;
		
		public void airenter(){
			index++;
			if(index>=20) {
			Air e=new Air();
			airs.add(e);
			index=0;
			}	
		}
		//绘制组件
		public void paintComponent(Graphics g)
		{
			//画图片，改变图片大小
			
			super.paintComponents(g);
			//绘制背景
			g.drawImage(this.bgImage, 0,top-this.bgImage.getHeight(this),this.bgImage.getWidth(this),this.bgImage.getHeight(this),null);
			g.drawImage(this.bgImage, 0,top,this.bgImage.getWidth(this),this.bgImage.getHeight(this),null);
			if(flag==false) 
			{
				this.hero.drawhero1(g);
				
				g.drawImage(this.bgImage1, 100,370,290,40,null);
				return;
			}
			timer++;
			if(timer==10000)
				timer=0;
			//实现图片向下移动
			if(timer%10==0) {
				top++;
				top++;
				
				if(top>=this.bgImage.getHeight(this))
					top=0;
				
			}
			
			//绘制玩家
			this.hero.drawhero(g);
			
			//绘制敌人
			for(int i=0;i<airs.size();i++){
				
				Air e=airs.get(i);
				
				g.drawImage(e.image1, e.x,e.y,e.width,e.height,null);
			}
			
			for(int i=0;i<fires.size();i++){
				
				Fire f=fires.get(i);
				
				g.drawImage(f.image3, f.x,f.y,f.width,f.height,null);
		}
	
		}	
}
