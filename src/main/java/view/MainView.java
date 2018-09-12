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
	private JButton getnamebtn;		//获取名称
	private JButton deletebtn;			//删除数据按钮
	private JButton getmoneybtn;		//获取分红按钮
	private JTextField updatebtn;      //显示进度信息
	private JButton showbtn;           //进度条
	private JButton exitbtn;				//推出
	
	//构造器
	public MainView() {
		init();
	}
	
	//初始化
	public void init() {
		 SwingUtilities.invokeLater(new Runnable() {

	           public void run() {
		frame = new JFrame();
		frame.setTitle("主界面");
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		//布局
		getnamebtn = new JButton("getname");
		getnamebtn.setBounds(0,0, 300, 30);
		frame.getContentPane().add(getnamebtn);
		
		getmoneybtn = new JButton("getmoney");
		getmoneybtn.setBounds(0,30, 300, 30);
		frame.getContentPane().add(getmoneybtn);
		
		deletebtn = new JButton("delete");
		deletebtn.setBounds(0,60, 300, 30);
		frame.getContentPane().add(deletebtn);
		
		updatebtn = new JTextField("显示信息");
		updatebtn.setBounds(0,270, 300, 30);
		frame.getContentPane().add(updatebtn);
		
		//进度条   当其他按钮正在触发事件时无法显示进度
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
//    			progressBar.setString("完成！");
//    		}
//    	}.start();
    	frame.getContentPane().add(progressBar);
    	
		exitbtn = new JButton("退出");
		exitbtn.setBounds(0,330, 300, 30);
		frame.getContentPane().add(exitbtn);
		
		//退出按钮
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		getnamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatebtn.setText("正在获取数据");
					//创建多线程制造进度条的效果
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
					updatebtn.setText("获取完毕");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//点击getmoney按钮触发事件,获取分红信息
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
		
		//点击delete按钮触发事件，删除数据
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatebtn.setText("正在删除shares表中的数据");
					MYSQLControl.executeDelete();
					updatebtn.setText("数据删除成功");
					updatebtn.setText("正在删除moneys表中的数据");
					MYSQLControl.executeDelete1();
					updatebtn.setText("数据删除成功");
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
