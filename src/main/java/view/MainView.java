package view;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import db.MYSQLControl;
import main.MoneyMain;
import main.ShareMain;

public class MainView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;	//串行化用
	
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

		//初始化组件
		button1 = new JButton("getname");
		button2 = new JButton("getmoney");
		button3 = new JButton("delete");
		buttonquit = new JButton("quit");
		
		textfield = new JTextField();
		
		p1 = new JPanel(new GridLayout(8,2));
		p2 = new JPanel(new GridLayout(3,1));
		
		progressbar = new JProgressBar(0,100);
		
		mygetinfo = new getinfo(progressbar,textfield);
		
		//构造组件
		p1.add(button1);
		p1.add(button2);
		p1.add(button3);
		
		p2.add(textfield);
		p2.add(progressbar);
		p2.add(buttonquit);
		
		//添加组件
		getContentPane().add("North",p1);
		getContentPane().add("South",p2);
				
		//注册事件
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		buttonquit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
	    String selected =e.getActionCommand();
	    
	    //获取股票名称
	    if (selected.equals("getname")){
	    	mygetinfo.setkind(1);
	    	Thread t1 = new Thread(mygetinfo,"11");
	    	t1.start();
	    }
	    
	    //获取分红数据
	    if(selected.equals("getmoney")){
	    	mygetinfo.setkind(2);
	    	Thread t1 = new Thread(mygetinfo,"22");
	    	t1.start();
	    }
	    
	    //删除分红数据
	    if(selected.equals("delete")){
	    	try {
				MYSQLControl.executeDelete();
				MYSQLControl.executeDelete1();
				textfield.setText("数据库中的数据已被清空");
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

//对于大数据量的处理专门用一个线程
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
		
		//1表示获取股票名称，!!!!!
		if(mykind == 1){
			try {
				mytextfield.setText("正在读取股票数据");
				ShareMain.main(null);
				mytextfield.setText("正在向数据库中存储数据");
				for(int i=0;i<=100;i++){
					try{
						//设置进度条速度
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
		
		//2表示获取股票分红、配股、送股，!!!!!
		if(mykind == 2){
			try {
				mytextfield.setText("正在读取分红数据");
				MoneyMain.main(null);
				mytextfield.setText("正在向数据库中存储数据");
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
    	mytextfield.setText("数据已存入数据库中");
	}
}