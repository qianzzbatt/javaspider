package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import db.MYSQLControl;
import main.MoneyMain;
import main.ShareMain;

public class MainView {
	private JFrame frame;
	private JButton getnamebtn;		//��ȡ����
	private JButton deletebtn;			//ɾ�����ݰ�ť
	private JButton getmoneybtn;		//��ȡ�ֺ찴ť
	private JTextField updatebtn;      //��ʾ������Ϣ
	private JButton showbtn;           //������
	private JButton exitbtn;				//�Ƴ�
	
	//������
	public MainView() {
		init();
	}
	
	//��ʼ��
	public void init() {
		 SwingUtilities.invokeLater(new Runnable() {

	           public void run() {
		frame = new JFrame();
		frame.setTitle("������");
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		//����
		getnamebtn = new JButton("getname");
		getnamebtn.setBounds(0,0, 300, 30);
		frame.getContentPane().add(getnamebtn);
		
		getmoneybtn = new JButton("getmoney");
		getmoneybtn.setBounds(0,30, 300, 30);
		frame.getContentPane().add(getmoneybtn);
		
		deletebtn = new JButton("delete");
		deletebtn.setBounds(0,60, 300, 30);
		frame.getContentPane().add(deletebtn);
		
		updatebtn = new JTextField("��ʾ��Ϣ");
		updatebtn.setBounds(0,270, 300, 30);
		frame.getContentPane().add(updatebtn);
		
		//������   ��������ť���ڴ����¼�ʱ�޷���ʾ����
		final JProgressBar progressBar=new JProgressBar();
		progressBar.setBounds(0,300, 300, 30);
		progressBar.setStringPainted(true);

//		new Thread(){
//    		public void run(){
//    			for(int i=0;i<=100;i++){
//    				try{
//    					Thread.sleep(100);
//    				}catch(InterruptedException e){
//    					e.printStackTrace();
//    				}
//    			      progressBar.setValue(i);
//    			}
//    			progressBar.setString("��ɣ�");
//    		}
//    	}.start();
    	frame.getContentPane().add(progressBar);
    	
		exitbtn = new JButton("�˳�");
		exitbtn.setBounds(0,330, 300, 30);
		frame.getContentPane().add(exitbtn);
		
		//�˳���ť
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		getnamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatebtn.setText("���ڻ�ȡ����");
					//�������߳������������Ч��
					new Thread(){
						   public void run() {
                               for (int i = 0; i <= 10; i++) {
                                   final int finalI = i;
                                   SwingUtilities.invokeLater(new Runnable() {
                                       public void run() {
                                           progressBar.setValue(finalI * 10);
                                       }
                                   });
                                   try {
                                       TimeUnit.SECONDS.sleep(1);
                                   } catch (InterruptedException e1) {
                                       e1.printStackTrace();
                                   }
                               }
                           }
			    	}.start();
					ShareMain.main(null);
					updatebtn.setText("��ȡ���");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//���getmoney��ť�����¼�,��ȡ�ֺ���Ϣ
		getmoneybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MoneyMain.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//���delete��ť�����¼���ɾ������
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatebtn.setText("����ɾ��shares���е�����");
					MYSQLControl.executeDelete();
					updatebtn.setText("����ɾ���ɹ�");
					updatebtn.setText("����ɾ��moneys���е�����");
					MYSQLControl.executeDelete1();
					updatebtn.setText("����ɾ���ɹ�");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	           }
	       });
	}
}
