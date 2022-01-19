package shoot;
public class DrawableThread extends Thread{
	
	public Panel panel;
	
	public DrawableThread(Panel panel) {
		this.panel=panel;
	}
	public void run() {
		while(true) {
			this.panel.repaint();//�ػ棬����paintComponent
	
			try{
				this.currentThread().sleep(1);      //ÿһ���뻭һ��
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
	
	
	

