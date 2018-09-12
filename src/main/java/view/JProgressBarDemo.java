package view;

import javax.swing.*;

import javax.swing.plaf.metal.MetalProgressBarUI;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.concurrent.TimeUnit;

/**

* Created by MyWorld on 2016/3/24.

*/

public class JProgressBarDemo {

   public static void main(String[] args) throws UnsupportedLookAndFeelException {


//        JFrame.setDefaultLookAndFeelDecorated(true);

       SwingUtilities.invokeLater(new Runnable() {

           public void run() {

               final JFrame frame = new JFrame("JProgressBarDemo");

               frame.setSize(400, 200);

               frame.setLocationRelativeTo(null);

               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               frame.setLayout(new FlowLayout());

               final JProgressBar progressBar = new JProgressBar();

               progressBar.setOrientation(JProgressBar.HORIZONTAL);

               progressBar.setSize(200, 100);

               progressBar.setMinimum(0);

               progressBar.setMaximum(100);

               progressBar.setBorderPainted(true);

               progressBar.setUI(new MetalProgressBarUI());

               progressBar.setBackground(Color.green);

               progressBar.setForeground(Color.BLUE);

               progressBar.setStringPainted(true);

               frame.add(progressBar);

               JButton btn = new JButton("Start ProgressBar");
               btn.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       new Thread((new Runnable() {
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
                       })).start();
                   }
               });                frame.add(btn);
               frame.setVisible(true);
           }
       });
   }

}