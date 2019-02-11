package com.grupp2.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.grupp2.GetNews;
import com.grupp2.NewsItem;
import com.grupp2.utils.QueryUtils;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class NewsGui {

	public JFrame frame;
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	private JTable table;
	static ArrayList<NewsItem> newsArrayList;

	public NewsGui() throws IOException, SQLException, ClassNotFoundException {
		initialize();
		showTable();
		this.frame.setResizable(false);
	}

	public void showTable() throws IOException, ClassNotFoundException, SQLException {
		Vector cols = new Vector();
		cols.addElement("Source name");
		cols.addElement("News header");
		cols.addElement("News link");

		Vector data = new Vector();

		table.setModel(new DefaultTableModel(data, cols));

	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(400, 100, 604, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton update_all_button = new JButton("Update All");
		update_all_button.setForeground(Color.blue);
		update_all_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		update_all_button.setBounds(20, 100, 106, 60);
		frame.getContentPane().add(update_all_button);

		JButton update_dn_button = new JButton("Update DN");
		update_dn_button.setForeground(Color.blue);
		update_dn_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Vector cols = new Vector();
				cols.addElement("Source name");
				cols.addElement("News header");
				cols.addElement("News link");
				
				Vector data = new Vector();
				String database = "use news;";
				String insertSqlQuery = " INSERT INTO datasource (name, starturl) "
						+ "SELECT * FROM (SELECT ?, ?) AS tmp " + "WHERE NOT EXISTS ( "
						+ "    SELECT name FROM datasource WHERE name = ? " + ") LIMIT 1;" + "insert into scandata "
						+ "(id, sourcetext, sourcelink, date) "
						+ "values ((select id from datasource where name = ?), ?, ?, now());";

				try {
					preparedStatement = connection.prepareStatement(database + insertSqlQuery);
					for (NewsItem newsItem : newsArrayList) {
						Vector login = new Vector();
						preparedStatement.setString(1, newsItem.getSourceName());
						preparedStatement.setString(2, newsItem.getSourceName());
						preparedStatement.setString(3, newsItem.getNewsLink());
						preparedStatement.execute();
						login.add(preparedStatement);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						preparedStatement.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table.setModel(new DefaultTableModel(data, cols));
					newsArrayList.clear();
				}

			}
		});
		update_dn_button.setBounds(122, 100, 106, 60);
		frame.getContentPane().add(update_dn_button);

		JButton update_gp_button = new JButton("Update GP");
		update_gp_button.setForeground(Color.blue);
		update_gp_button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});
		update_gp_button.setBounds(222, 100, 106, 60);
		frame.getContentPane().add(update_gp_button);

		JButton delete_all_button = new JButton("Delete All");
		delete_all_button.setForeground(Color.blue);
		delete_all_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		delete_all_button.setBounds(322, 100, 106, 60);
		frame.getContentPane().add(delete_all_button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 185, 346, 146);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		scrollPane.setViewportView(table);

		JLabel title = new JLabel("News Parser");
		title.setFont(new Font("Serif", Font.BOLD, 20));
		title.setForeground(Color.white);
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.blue);
		titlePanel.add(title);
		titlePanel.setBounds(30, 50, 120, 35);
		frame.getContentPane().add(titlePanel);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewsGui window = new NewsGui();
					window.frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
