package shoot;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



//����
public class Panel extends JPanel{
		public static boolean flag=true;
		public Image bgImage,bgImage1;
		public int timer=0;
		public int top=0;//ͼƬԭʼ�ĸ߶�
		public DrawableThread drawableThread; 
		public BaseFrame frame;
		public Hero hero;
		
		List<Air> airs=new ArrayList<Air>();
		
		List<Fire> fires=new ArrayList<Fire>();
		
		public Panel()
		{
			
			this.bgImage=Toolkit.getDefaultToolkit().getImage("image/123.jpg");//���뱳��ͼƬ
			this.bgImage1=Toolkit.getDefaultToolkit().getImage("image/gameover.png");
			this.hero=new Hero(this);
			
			
			this.drawableThread=new DrawableThread(this);//�����߳�
			this.drawableThread.start();//����ͼƬ
			
			MouseAdapter adapter=new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if(flag=true) {
						repaint();
					}
				}
				//��������ƶ���mouseCliked()������굥����
				public void mouseMoved(MouseEvent e) {
					int mx=e.getX();
					int my=e.getY();
					hero.moveToMouse(mx,my);
					repaint();//�ػ�
				}
			};
			
			//�����������
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
					}//���������ƶ�
					repaint();
				}
				
			};
			//�ӵ�������������
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
							this.currentThread().sleep(25);      //ÿһ���뻭һ��
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
				//�ж��ӵ��Ƿ���е���
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
			//�ж��ӵ��Ƿ���е���
			
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
		//�������
		public void paintComponent(Graphics g)
		{
			//��ͼƬ���ı�ͼƬ��С
			
			super.paintComponents(g);
			//���Ʊ���
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
			//ʵ��ͼƬ�����ƶ�
			if(timer%10==0) {
				top++;
				top++;
				
				if(top>=this.bgImage.getHeight(this))
					top=0;
				
			}
			
			//�������
			this.hero.drawhero(g);
			
			//���Ƶ���
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
