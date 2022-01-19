package shoot;
public class DrawableThread extends Thread{
	
	public Panel panel;
	
	public DrawableThread(Panel panel) {
		this.panel=panel;
	}
	public void run() {
		while(true) {
			this.panel.repaint();//重绘，调用paintComponent
	
			try{
				this.currentThread().sleep(1);      //每一毫秒画一次
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
	
	
	

