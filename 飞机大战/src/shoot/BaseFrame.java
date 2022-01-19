package shoot;
//主窗口
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class BaseFrame extends JFrame {
		public static int frameWidth=512;
		public static int frameHeight=768;
		public Panel panel;
		
		public BaseFrame()
		{
			super("战鸡游戏");
			//获得屏幕分辨率
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			//设置窗口的大小位置
			setBounds(((int)screenSize.getWidth()-frameWidth)/2,100,frameWidth,frameHeight);
			//设置布局方式
			setLayout(null);
			//建立一个新的Panel对象
			this.panel=new Panel();
			//Panel大小位置
			this.panel.setBounds(0,0, frameWidth, frameHeight);
			//飞船行动
			this.panel.action();
			//组件添加到窗口中
			this.add(this.panel);
			//窗口可见
			setVisible(true);
			//窗口大小不可变
			setResizable(false);
			//设置窗口关闭
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
		
}
