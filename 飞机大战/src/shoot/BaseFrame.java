package shoot;
//������
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class BaseFrame extends JFrame {
		public static int frameWidth=512;
		public static int frameHeight=768;
		public Panel panel;
		
		public BaseFrame()
		{
			super("ս����Ϸ");
			//�����Ļ�ֱ���
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			//���ô��ڵĴ�Сλ��
			setBounds(((int)screenSize.getWidth()-frameWidth)/2,100,frameWidth,frameHeight);
			//���ò��ַ�ʽ
			setLayout(null);
			//����һ���µ�Panel����
			this.panel=new Panel();
			//Panel��Сλ��
			this.panel.setBounds(0,0, frameWidth, frameHeight);
			//�ɴ��ж�
			this.panel.action();
			//�����ӵ�������
			this.add(this.panel);
			//���ڿɼ�
			setVisible(true);
			//���ڴ�С���ɱ�
			setResizable(false);
			//���ô��ڹر�
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
		
}
