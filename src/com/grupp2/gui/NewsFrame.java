package com.grupp2.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class NewsFrame extends JFrame {
	
	private JPanel contentPanel;
	private JTable table;
	
	
	public NewsFrame() {
		
		setBounds(400, 100, 604, 428);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel = new JPanel();
		getContentPane().setLayout(null);
		
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewsFrame frame = new NewsFrame();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
