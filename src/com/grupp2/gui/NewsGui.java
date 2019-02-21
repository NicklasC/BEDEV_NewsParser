package com.grupp2.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.grupp2.GetNews;
import com.grupp2.NewsItem;
import com.grupp2.utils.QueryUtils;
import com.mysql.cj.protocol.Resultset;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class NewsGui {

	public JFrame frame;
	private static DefaultTableModel tableModel = new DefaultTableModel();
	private JTable resultTable = new JTable(tableModel);
	static ArrayList<NewsItem> newsArrayList;
    private static Resultset resultSet;
    private static ResultSetMetaData resultSetMetaData;

	public NewsGui() throws IOException, SQLException, ClassNotFoundException {
		initialize();
		this.frame.setResizable(false);
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(400, 100, 604, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
		public void windowClosing(WindowEvent e) {
				try {
					if(QueryUtils.checkIfDbConnected()) {
						QueryUtils.closeConnection(); // Close db connection when close window
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
						    e1.getMessage());
				}
				System.exit(0);
			}
		}); 

		JButton update_all_button = new JButton("Update All");
		update_all_button.setForeground(Color.blue);
		update_all_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					newsArrayList=GetNews.getNewsFromDN();
					if (QueryUtils.checkIfDbConnected()) {
						QueryUtils.saveNewsInDB(newsArrayList);
					}
					newsArrayList=GetNews.getNewsFromGP();
					if (QueryUtils.checkIfDbConnected()) {
						QueryUtils.saveNewsInDB(newsArrayList);
					}
					poputateDataInGrid(QueryUtils.getDataFromDB(resultSet));
				} catch (IOException | SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		update_all_button.setBounds(20, 100, 106, 60);
		frame.getContentPane().add(update_all_button);

		JButton update_dn_button = new JButton("Update DN");
		update_dn_button.setForeground(Color.blue);
		update_dn_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newsArrayList=GetNews.getNewsFromDN();
					if (QueryUtils.checkIfDbConnected()) {
						QueryUtils.saveNewsInDB(newsArrayList);
					}
					poputateDataInGrid(QueryUtils.getDataFromDB(resultSet));
				} catch (IOException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		update_dn_button.setBounds(122, 100, 106, 60);
		frame.getContentPane().add(update_dn_button);

		JButton update_gp_button = new JButton("Update GP");
		update_gp_button.setForeground(Color.blue);
		update_gp_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newsArrayList=GetNews.getNewsFromGP();
					if (QueryUtils.checkIfDbConnected()) {
						QueryUtils.saveNewsInDB(newsArrayList);
					}
					poputateDataInGrid(QueryUtils.getDataFromDB(resultSet));
				} catch (IOException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		update_gp_button.setBounds(222, 100, 106, 60);
		frame.getContentPane().add(update_gp_button);

		JButton delete_all_button = new JButton("Delete All");
		delete_all_button.setForeground(Color.blue);
		delete_all_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (QueryUtils.checkIfDbConnected()) {
						QueryUtils.deleteContentDB();
					}
					poputateDataInGrid(QueryUtils.getDataFromDB(resultSet));
				} catch (SQLException | ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		delete_all_button.setBounds(322, 100, 106, 60);
		frame.getContentPane().add(delete_all_button);

		JScrollPane scrollPane = new JScrollPane(resultTable);
		scrollPane.setBounds(20, 185, 550, 180);
		frame.getContentPane().add(scrollPane);

		resultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		scrollPane.setViewportView(resultTable);

		JLabel title = new JLabel("News Parser");
		title.setFont(new Font("Serif", Font.BOLD, 20));
		title.setForeground(Color.white);
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.blue);
		titlePanel.add(title);
		titlePanel.setBounds(30, 50, 120, 35);
		frame.getContentPane().add(titlePanel);
	}
	
	public static void poputateDataInGrid(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException {
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		Vector<Object> row = null;
		resultSetMetaData = resultSet.getMetaData();
		int numberOfColumns = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= numberOfColumns; i++) {
			tableModel.addColumn(resultSetMetaData.getColumnName(i));
		}
		while (resultSet.next()) {
		       row = new Vector<Object>(numberOfColumns);
		       for (int i = 1; i <= numberOfColumns; i++) {
		        row.addElement(resultSet.getObject(i));
		       }
		       tableModel.addRow(row);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewsGui window = new NewsGui();
					window.frame.setVisible(true);
					QueryUtils.connect(); //Connect to MySQL db
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
