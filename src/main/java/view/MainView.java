package view;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import db.MYSQLControl;
import main.MoneyMain;
import main.ShareMain;

public class MainView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;	//���л���
	
	private JPanel p1;
	private JPanel p2;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton buttonquit;
	private JTextField textfield;
	private JProgressBar progressbar;
	
	public getinfo mygetinfo;
	
	public MainView(String title) {
		super(title);

		//��ʼ�����
		button1 = new JButton("getname");
		button2 = new JButton("getmoney");
		button3 = new JButton("delete");
		buttonquit = new JButton("quit");
		
		textfield = new JTextField();
		
		p1 = new JPanel(new GridLayout(8,2));
		p2 = new JPanel(new GridLayout(3,1));
		
		progressbar = new JProgressBar(0,100);
		
		mygetinfo = new getinfo(progressbar,textfield);
		
		//�������
		p1.add(button1);
		p1.add(button2);
		p1.add(button3);
		
		p2.add(textfield);
		p2.add(progressbar);
		p2.add(buttonquit);
		
		//������
		getContentPane().add("North",p1);
		getContentPane().add("South",p2);
				
		//ע���¼�
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		buttonquit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
	    String selected =e.getActionCommand();
	    
	    //��ȡ��Ʊ����
	    if (selected.equals("getname")){
	    	mygetinfo.setkind(1);
	    	Thread t1 = new Thread(mygetinfo,"11");
	    	t1.start();
	    }
	    
	    //��ȡ�ֺ�����
	    if(selected.equals("getmoney")){
	    	mygetinfo.setkind(2);
	    	Thread t1 = new Thread(mygetinfo,"22");
	    	t1.start();
	    }
	    
	    //ɾ���ֺ�����
	    if(selected.equals("delete")){
	    	try {
				MYSQLControl.executeDelete();
				MYSQLControl.executeDelete1();
				textfield.setText("���ݿ��е������ѱ����");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    	    	    
	    if (selected.equals("quit")){
	    	this.dispose();	
	    }
	}

	public static void main(String[] args){
		MainView me = new MainView("myinfo!");
		me.pack();
		me.setSize(500,500);
		me.setVisible(true);
	}	
}

//���ڴ��������Ĵ���ר����һ���߳�
class getinfo implements Runnable{
	public JProgressBar myprogressbar;
	public JTextField mytextfield;
	public int mykind;
	
	public getinfo(JProgressBar tempprogressbar,JTextField temptextfield){
		this.myprogressbar = tempprogressbar;
		this.mytextfield = temptextfield;
	}
	
	public void setkind(int x){
		this.mykind = x;
	}
	
	public void run(){
		
		//1��ʾ��ȡ��Ʊ���ƣ�!!!!!
		if(mykind == 1){
			try {
				mytextfield.setText("���ڶ�ȡ��Ʊ����");
				ShareMain.main(null);
				mytextfield.setText("���������ݿ��д洢����");
				for(int i=0;i<=100;i++){
					try{
						//���ý������ٶ�
						Thread.sleep(60);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					myprogressbar.setValue(i);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//2��ʾ��ȡ��Ʊ�ֺ졢��ɡ��͹ɣ�!!!!!
		if(mykind == 2){
			try {
				mytextfield.setText("���ڶ�ȡ�ֺ�����");
				MoneyMain.main(null);
				mytextfield.setText("���������ݿ��д洢����");
				for(int i=0;i<=100;i++){
					try{
						Thread.sleep(10);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					myprogressbar.setValue(i);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
		try{
			Thread.sleep(1000);	
		}catch(Exception e){}
    	mytextfield.setText("�����Ѵ������ݿ���");
	}
}